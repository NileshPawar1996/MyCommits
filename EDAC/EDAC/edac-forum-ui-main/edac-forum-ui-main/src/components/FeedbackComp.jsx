import axios from "axios";
import { useEffect, useMemo, useState } from "react";
import CustomModal from "./CustomModal";
import { ADD_FULL_FEEDBACK_URL, CHECK_FEEDBACK_SUBMITTED_URL, USER_BATCH, } from "../config"
import FeedbackForm from "./FeedbackForm";

export default function FeedbackComp({ data }) {
  const [submitted, setSubmitted] = useState(false);
  const [toggle, setToggle] = useState(false);

  useEffect(() => {
    axios.get(`${CHECK_FEEDBACK_SUBMITTED_URL}/${sessionStorage.getItem('curr_user')}/${data.moduleId}`)
      .then(res => {
        console.log(res.data.data);
        setSubmitted(res.data.data.submitted)
      })
  }, [toggle, data.moduleId])

  const handleSubmit = () => {
    clearState();
    if (submitted) return
    axios.post(ADD_FULL_FEEDBACK_URL, fullRating)
      .then(res => {
        setToggle(!toggle);
        console.log(res);
      })
  }

  const clearState = () => {
    console.log("feedback cleared");
    setCriteriaRating({});
    setStudentFeedback({
      batchId: USER_BATCH,
      studentId: sessionStorage.getItem('curr_user'),
      moduleId: data.moduleId,
      comment: "",
    });
  }

  const [studentFeedback, setStudentFeedback] = useState({
    batchId: USER_BATCH,
    studentId: sessionStorage.getItem('curr_user'),
    moduleId: data.moduleId,
    comment: "",
  })

  const [criteriaRating, setCriteriaRating] = useState({})

  const fullRating = useMemo(() => {
    const ratings = Object.keys(criteriaRating).map(id => {
      return { criteria: { id }, rating: criteriaRating[id] }
    })
    return { studentFeedback, criteriaRating: ratings }
  }, [criteriaRating, studentFeedback])

  const handleComment = (e) => {
    setStudentFeedback({ ...studentFeedback, comment: e.target.value });
  }

  const handleRating = (e) => {
    criteriaRating[e.target.name] = e.target.value;
    setCriteriaRating({ ...criteriaRating });
  }
  console.log(fullRating);

  return <CustomModal
    btnText={
      <>
        <div>
          <h5 className="m-0">{data.moduleName}</h5>
          <small>{data.teacherName}</small>
        </div>
        <span className="text-muted">{submitted ? "Submitted" : ""}</span>
      </>
    }
    modalAttr={{
      // title: data.moduleName + " Feedback" + "<small>" + data.teacherName + "</small>",
      title: <>{data.moduleName} - <small>{data.teacherName}</small></>,
      btnClass: "feedback-card",
      body: submitted
        ? "submitted"
        : <FeedbackForm
          handleRating={handleRating}
          handleComment={handleComment}
          criteriaRating={criteriaRating}
        />
    }}
    handleSubmit={handleSubmit}
    clearState={clearState}
    submitBtnText={"Submit Feedback"} />

}

