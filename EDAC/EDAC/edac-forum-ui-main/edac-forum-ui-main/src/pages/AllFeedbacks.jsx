import axios from "axios";
import { useEffect, useState } from "react";
import FeedbackComp from "../components/FeedbackComp";
import { MODULE_COMPLETED_URL, USER_COURSE, USER_BATCH } from "../config";

export default function AllFeedbacks() {

  const [feedbacks, setFeedbacks] = useState([]);

  useEffect(() => {
    axios.get(`${MODULE_COMPLETED_URL}/${USER_COURSE}/${USER_BATCH}`)
      .then(res => {
        console.table(res.data.data);
        setFeedbacks(res.data.data);
      })
  }, [])

  return <div className="d-flex flex-wrap gap-2">
    {
      feedbacks.map(feedback => {
        return feedback.isCompleted && <FeedbackComp data={feedback} />
      })
    }
  </div>
}