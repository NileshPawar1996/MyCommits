import { Bubble } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import { GET_CRITERIA_URL, GET_BATCHES_BY_TEACHER, GET_FEEDBACKS, GET_BATCHNAME, GET_MODULENAME, GET_MODULES_BY_TEACHER, GET_RATINGS } from '../config';
import axios from 'axios';
import {
  Chart as ChartJS,
  LinearScale,
  PointElement,
  Tooltip,
  Legend,
} from 'chart.js';

export default function ViewFeedbackComments() {
  ChartJS.register(LinearScale, PointElement, Tooltip, Legend);
  const staffId = sessionStorage.getItem('curr_user');

  const [selected, setSelected] = useState({ batchId: -1, moduleId: -1, });
  const [batches, setBatches] = useState([]);
  const [modules, setModules] = useState([]);
  const [feedbackIds, setFeedbackIds] = useState("");
  const [feedbacks, setFeedbacks] = useState([]);
  const [criterias, setCriterias] = useState([]);
  const [ratings, setRatings] = useState({
    datasets: [
      {
        label: 'Punctuality',
        data: Array.from({ length: 20 }, () => ({
          x: Math.random() * 5,
          y: 1,
          r: 5,
        })),
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      }
    ]
  });

  const getCriteriaName = (criteriaId) => {
    let critName;
    criterias.map(crit => {
      if (crit.id === criteriaId) {
        critName = crit.criteriaName;
      }
      return null;
    })
    return critName
  }

  useEffect(() => {
    axios.get(GET_CRITERIA_URL)
      .then(res => {
        // console.log(res);
        setCriterias(res.data.data);
      })

    axios.get(`${GET_BATCHES_BY_TEACHER}/${staffId}`)
      .then(res => {
        const batchIds = res.data.data;
        const fetchedBatches = [];
        batchIds.forEach(id => {
          axios.get(`${GET_BATCHNAME}/${id}`)
            .then(res => {
              fetchedBatches.push(res.data.data);
              setBatches([...fetchedBatches])
            })
        })
      })
  }, [])

  useEffect(() => {
    if (selected.batchId != -1) {
      setFeedbacks([]);
      axios.get(`${GET_MODULES_BY_TEACHER}/${selected.batchId}/${staffId}`)
        .then(res => {
          const moduleIds = res.data.data;
          const fetchedModules = [];
          moduleIds.forEach(id => {
            console.log(id)
            axios.get(`${GET_MODULENAME}/${id}`)
              .then(res => {
                fetchedModules.push(res.data.data);
                setModules([...fetchedModules])
              })
          })
        })
    }
  }, [selected.batchId]);

  useEffect(() => {
    if (selected.moduleId != -1) {
      setFeedbacks([]);
      axios.get(`${GET_FEEDBACKS}/${selected.moduleId}/${selected.batchId}`)
        .then(res => {
          setFeedbacks(res.data.data)
          let feedbackIdString = "";
          res.data.data.map(f => {
            feedbackIdString += f.id + ",";
            return null;
          })
          setFeedbackIds(feedbackIdString.substring(0, feedbackIdString.length - 1));
        })
    }
  }, [selected.moduleId, selected.batchId]);

  // console.log(batches);

  const handleSelect = (e) => {
    setFeedbacks([]);
    if (e.target.name == 'batchId') {
      selected.moduleId = -1;
      setModules([]);
    }
    setSelected({ ...selected, [e.target.name]: e.target.value })
  }

  // select feedback,criteria, count(rating),rating  from student_feedback_ratings where feedback in (31,32,33) group by rating,criteria order by criteria;
  const [criteriaWiseData, setCriteriaWiseData] = useState({})

  useEffect(() => {
    axios.get(`${GET_RATINGS}?ids=${feedbackIds}`)
      .then(res => {
        const fetchedRatings = res.data.data;
        // console.log(fetchedRatings)
        const ratingCount = {};
        fetchedRatings.map((rating) => {
          if (ratingCount[rating.criteria.id] == undefined)
            ratingCount[rating.criteria.id] = {}
          else
            ratingCount[rating.criteria.id][rating.rating] = rating.count
          return null;
        })
        console.log(ratingCount);
        setCriteriaWiseData(ratingCount);
      })
  }, [feedbackIds])

  useEffect(() => {
    const newRatings = {
      datasets: criterias.map((criteria, i) => {
        return {
          label: criteria.criteriaName,
          data: criteriaWiseData[criteria.id] != undefined
            ? Object.keys(criteriaWiseData[criteria.id]).map(ratingVal => {
              return {
                x: criteria.id,
                y: parseInt(ratingVal),
                r: criteriaWiseData[criteria.id][ratingVal] * 3,
              }
            })
            : [],
          backgroundColor: `rgba(${Math.random() * 150 + 50},${Math.random() * 50 + 10}, ${Math.random() * 150 + 50}, 0.5)`,
        }
      })
    }
    setRatings(newRatings);
  }, [criterias, criteriaWiseData, modules, batches])

  // console.table(data.datasets[0].data);
  // console.log(data);

  const options = {
    scales: {
      y: {
        title: {
          text: 'Rating',
          display: true
        },
        beginAtZero: true,
        ticks: {
          padding: 25,
          callback: function (val, index) {
            return val % 1 == 0
              ? val : ''
          },
        }
      },
      x: {
        title: {
          text: 'Criteria',
          display: true
        },
        grid: {
          display: false
        },
        ticks: {
          padding: 15,
          callback: function (val) { return getCriteriaName(val) }
        },
      },
    },
    interaction: {
      intersect: false,
      mode: 'nearest',
      axis: 'xy'
    },
    aspectRatio: 2 / 1,
    plugins: {
      legend: false,
      tooltip: {
        callbacks: {
          title: (tooltipItems) => {
            console.log(tooltipItems);
            return (parseInt(tooltipItems[0].raw.r) - 0) / 3 + " students\nrated you " + tooltipItems[0].raw.x + "\nin " + tooltipItems[0].dataset.label;
          },
          label: () => '',
        }
      }
    }
  }

  return <>
    <div className='row' >
      <div className="mb-3 col-6">
        <label className="form-label">Select Batch</label>
        <select className="form-select" name="batchId" onChange={e => { handleSelect(e) }}>
          <option defaultValue>Select Batch</option>
          {batches.map(batch => {
            return <option key={batch.id}
              value={batch.id}>
              {batch.id} - {batch.batchId} - {batch.course.id}
            </option>
          })}
        </select>
      </div>
      <div className="mb-3 col-6">
        <label className="form-label">Select Module</label>
        <select className="form-select" name="moduleId" onChange={e => { handleSelect(e) }}>
          <option defaultValue value={-1}>Select Module</option>
          {modules.map(module => {
            return <option key={module.id}
              value={module.id}>
              {module.id} - {module.moduleName} - {module.course.id}
            </option>
          })}
        </select>
      </div>
    </div>
    <h4 className='my-3'>Ratings</h4>
    {/* <Bubble options={options} data={data} /> */}
    {feedbacks.length > 0 && <Bubble options={options} data={ratings} />}
    <h4 className='my-3'>Comments</h4>
    <div className='feedback-comment-wrap'>
      {
        feedbacks?.map(f => {
          return <p className='user-card mb-1'>{f.comment}</p>
        })
      }
    </div>
  </>;


}