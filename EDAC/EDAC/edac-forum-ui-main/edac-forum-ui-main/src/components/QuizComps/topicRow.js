import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL, MANAGEMENT_SERVICE_URL } from "../../config";

const TopicRow = (props) => {
    const { topic, loadTopics } = props
    const [module, setModule] = useState("")

    const deleteTopic = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/topic/delete/${topic.id}`
        axios.delete(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                toast.success(result['data'])
                loadTopics(topic.moduleId)
            } else {
                toast.error(result['error'])
            }
        })
    }
    const loadModule = () => {
        const url = `${MANAGEMENT_SERVICE_URL}/manage/module/${topic.moduleId}`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setModule(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    };
    useEffect(() => {
        loadModule()
    }, [])
    return (
        <tr key={topic.id}>
            <td>{module.moduleName}</td>
            <td>{topic.topicName}</td>
            <td>
                <button className="btn btn-danger" onClick={deleteTopic}>Delete</button>
            </td>
        </tr>
    )
}
export default TopicRow