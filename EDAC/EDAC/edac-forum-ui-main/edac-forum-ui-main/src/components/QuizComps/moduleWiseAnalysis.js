import { useEffect, useState } from "react"
import axios from "axios";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from '../../config'
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
            text: 'MODULE-WISE ANALYSIS'
        }
    }
}
const ModuleWiseAnalysis = () => {
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
            const modules = []
            const percentage = []
            const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/moduleAnalysis/student/${student.id}`
            const response = await axios.get(url)
            const result = response.data
            const modulesArray = result.data
            if (result['status'] == 'success') {
                for (const module of modulesArray) {
                    modules.push(module.moduleName)
                    percentage.push(module.percentage)
                }
                setExamData({
                    labels: [...modules],
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
    }, [])
    return (
        <Bar data={examData} options={options} />
    );

}
export default ModuleWiseAnalysis;