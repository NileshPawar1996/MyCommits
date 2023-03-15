import { useEffect, useState } from "react"
import axios from "axios";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from "../../config"
import { Line } from 'react-chartjs-2'
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
            text: 'EXAM-WISE ANALYSIS'
        }
    }
}
const ExamAnalysis = () => {
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
        const loadExamDetails = async () => {
            const exams = []
            const percentage = []
            const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/getDetails/student/${student.id}`
            const response = await axios.get(url)
            const result = response.data
            const examsArray = result.data
            if (result['status'] == 'success') {
                for (const exam of examsArray) {
                    const moduleName = exam.module.moduleName + " " + exam.type
                    exams.push(moduleName)
                    percentage.push(exam.percentage)
                }
                setExamData({
                    labels: [...exams],
                    datasets: [
                        {
                            label: "PERCENTAGE",
                            data: [...percentage],
                            borderColor: 'rgb(255, 99, 132)',
                            backgroundColor: 'rgb(255, 99, 132))',
                        },
                    ]
                })
            } else {
                toast.error(result['error'])
            }
        }
        loadExamDetails()
    }, [])
    return (
        <Line data={examData} options={options} />
    );

}
export default ExamAnalysis;