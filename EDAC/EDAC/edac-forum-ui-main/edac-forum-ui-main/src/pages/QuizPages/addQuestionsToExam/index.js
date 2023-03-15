import './index.css'
import { MDBBadge } from "mdb-react-ui-kit";
import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { MANAGEMENT_SERVICE_URL, QUIZ_SERVICE_URL } from "../../../config";
import CreatedExamRow from "../../../components/QuizComps/createdExamRow";
import QuestionsRow from "../../../components/QuizComps/questionsRow";
import ModuleInfo from '../../../components/QuizComps/moduleInfo'
import BatchInfo from '../../../components/QuizComps/batchInfo'
import ExamInfo from '../../../components/QuizComps/examInfo'
import {
  MDBTable,
  MDBTableHead,
  MDBTableBody,
} from "mdb-react-ui-kit";
const styles = {
  spanButton: {
    cursor: "pointer",
    color: "blue"
  },
};
const AddQuestionsToExams = () => {
  const [exams, setExams] = useState([])
  const [selectExam, setSelectExam] = useState("")//selected exams id
  const [questions, setQuestions] = useState([])
  const [examQuestions, setExamQuestions] = useState([])
  const [selectedExam, setSelectedExam] = useState()
  const [selectedExamsModule, setSelectedExamsModule] = useState()
  const [selectedExamsBatch, setSelectedExamsBatch] = useState()

  const handleSelectExamChange = (event) => {
    setSelectExam(event.target.value)
    // loadQuestions(getSelectedExamsModuleId(examId))
    loadExamQuestions(event.target.value)
  }
  useEffect(() => {
    // console.log("fn called")
    getSelectedExam(selectExam)
  }, [selectExam])

  const getSelectedExam = (selectExam) => {
    exams.map((e) => {
      if (e.id == selectExam) {
        setSelectedExam(e)
      }
    })
  }
  useEffect(() => {
    loadQuestions(selectedExam?.moduleId)
    fetchSelectedExamsModule(selectedExam?.moduleId)
    fetchSelectedExamsBatch(selectedExam?.batchId)
    console.log(selectedExam)
  }, [selectedExam])

  useEffect(() => {

  }, [])

  const fetchSelectedExamsModule = (id) => {
    if (id != undefined) {
      const url = `${MANAGEMENT_SERVICE_URL}/module/${id}`
      axios.get(url).then((response) => {
        const result = response.data
        if (result["status"] == "success") {
          setSelectedExamsModule(response.data)
        } else {
          toast.error(result['error'])
        }
      })
    }
  }
  const fetchSelectedExamsBatch = (id) => {
    if (id != undefined) {
      const url = `${MANAGEMENT_SERVICE_URL}/batch/${id}`
      axios.get(url).then((response) => {
        const result = response.data
        if (result['status'] == 'success') {
          setSelectedExamsBatch(response['data'])
        } else {
          toast.error(result['error'])
        }
      })
    }
  }
  const loadExams = () => {
    const url = `${QUIZ_SERVICE_URL}/quiz/exam/get/beforeStart`;//exams whose start date time < current date time should not be editable
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setExams(result["data"]);
      } else {
        toast.error(result["error"]);
      }
    });
  };
  const loadQuestions = (moduleId) => {
    if (moduleId != null) {
      const url = `${QUIZ_SERVICE_URL}/quiz/question/module/${moduleId}`;
      axios.get(url).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          setQuestions(result["data"]);
        } else {
          toast.error(result["error"]);
        }
      });
    }
  }

  const loadExamQuestions = (examId) => {
    if (examId != undefined) {
      const url = `${QUIZ_SERVICE_URL}/quiz/examQuestion/getQuestionsByExamId/${examId}`
      axios.get(url).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          setExamQuestions(result["data"]);
        } else {
          toast.error(result["error"]);
        }
      });
    }
  }

  useEffect(() => {
    loadExams();
  }, []);
  return (
    <div className='container'>
      <div className="title">
        <h1>Add Questions to exams</h1>
      </div>
      <div className='selectExam'>
        <h3>
          <MDBBadge className="ms-2" color='secondary'>Select Exam</MDBBadge>
          <span>Exams created after : { }moment</span>
        </h3>

        <select
          className="form-select"
          value={selectExam}
          onChange={handleSelectExamChange}
        >
          <option key={-1} value={-1}>
            Select Exam
          </option>
          {exams.map((exam) => (
            <option key={exam.id} value={exam.id}>
              <CreatedExamRow exam={exam} loadExams={loadExams} isSelect={true} />
            </option>
          ))}
        </select>
        <hr />
      </div>
      <br />
      <div className="questionsTable">
        {selectedExamsBatch != null && selectedExamsModule != null && (
          <h4>
            <MDBBadge className="ms-0" color='dark'>
              Questions in <span><ModuleInfo module={selectedExamsModule?.data} /></span>|<span><BatchInfo batch={selectedExamsBatch?.data} /></span>
            </MDBBadge>
          </h4>
        )}
        <br />
        <MDBTable>
          <MDBTableHead dark>
            <tr>
              <th scope="col">#Question Id</th>
              <th scope="col">Created</th>
              <th scope="col">Question</th>
              <th scope="col">Details</th>
              <th scope="col">Actions</th>
            </tr>
          </MDBTableHead>
          <MDBTableBody>
            {questions.map((question) => {

              return <QuestionsRow question={question} loadExamsQuestions={loadExamQuestions} isSelect={false} examId={selectExam} isExam={false} />

            })}
          </MDBTableBody>
        </MDBTable>
        <hr />
      </div>

      <div className="existingQuestionInExamTable">
        <h4>
          <MDBBadge className="ms-0" color='dark'>
            Questions in <span><ExamInfo module={selectedExamsModule?.data} exam={selectedExam} batch={selectedExamsBatch?.data} /></span>
          </MDBBadge>
        </h4>
        <MDBTable>
          <MDBTableHead dark>
            <tr>
              <th scope="col">#Question Id</th>
              <th scope="col">Added</th>
              <th scope="col">Question</th>
              <th scope="col">Details</th>
              <th scope="col">Actions</th>
            </tr>
          </MDBTableHead>
          <MDBTableBody>
            {examQuestions.map((question) => {
              return <QuestionsRow question={question.question} loadExamsQuestions={loadExamQuestions} isSelect={false} examId={selectExam} isExam={true} />;
            })}
          </MDBTableBody>
        </MDBTable>
      </div>
    </div>
  )
}
export default AddQuestionsToExams


