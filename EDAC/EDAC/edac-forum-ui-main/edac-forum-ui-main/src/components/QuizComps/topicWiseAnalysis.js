import { useEffect, useState } from "react"
import axios from "axios";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from "../../config"
import { Bar } from 'react-chartjs-2'
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js/auto'
ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);
const options = {
    responsive: true,
    plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'TOPIC-WISE ANALYSIS'
        }
    }
}
const TopicWiseAnalysis = (props) => {
    const { moduleId } = props
    const [student, setStudent] = useState({
        id: 1,
        firstName: "Vaibhav",
        lastName: "Chavan",
        batchId: 4
    })
    const [examData, setExamData] = useState({
        labels: [],
        datasets: [
            {
                label: "Exam Marks",
                data: []
            }
        ]
    })

    useEffect(() => {
        if (props.moduleId) {
            const loadExamDetails = async () => {
                const topics = []
                const percentage = []
                const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/topicAnalysis/student/${student.id}/module/${moduleId}`
                const response = await axios.get(url)
                const result = response.data
                const topicsArray = result.data
                console.log(topicsArray)
                if (result['status'] == 'success') {
                    for (const topic of topicsArray) {
                        topics.push(topic.TopicName)
                        percentage.push(topic.percentage)
                    }
                    setExamData({
                        labels: [...topics],
                        datasets: [
                            {
                                label: "PERCENTAGE",
                                data: [...percentage],
                                borderColor: 'rgb(255, 99, 132)',
                                backgroundColor: ['rgba(2142, 224, 34, 0.5)', 'rgba(75,192,192,0.2)', 'rgb(255, 99, 132)', '#742774',],
                            }
                        ]
                    })
                } else {
                    toast.error(result['error'])
                }
            }
            loadExamDetails()
        }
    }, [module])
    return (
        <div className="myshadow p-5 mb-3 col-8">

            <Bar data={examData} options={options} />
        </div>
    );

}
export default TopicWiseAnalysis;