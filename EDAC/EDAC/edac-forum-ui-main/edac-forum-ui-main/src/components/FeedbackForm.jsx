import axios from "axios";
import { useEffect, useState } from "react"
import { GET_CRITERIA_URL } from "../config";

export default function FeedbackForm({ handleComment, handleRating, criteriaRating }) {
  const [criterias, setCriterias] = useState([]);

  useEffect(() => {
    axios.get(GET_CRITERIA_URL)
      .then(res => {
        // console.log(res);
        setCriterias(res.data.data);
      })
  }, [])

  return <>
    {
      criterias.map(c => {
        return <div class="mb-3" key={c.id}>
          <label htmlFor={"rating-" + c.id} class="form-label">{c.criteriaName}</label>
          <div class="col-sm-10">
            <input type="range" class="form-range" min="0" max="5" name={c.id}
              onChange={(e) => { handleRating(e) }} id={"rating-" + c.id}
              value={criteriaRating[c.id] || 0}
            />
          </div>
        </div>
      })
    }
    <div class="mb-3">
      <label htmlFor="comment" class="form-label">Comment</label>
      <div>
        <textarea class="form-control" id="comment" rows="3" name="comment" onChange={(e) => { handleComment(e) }} />
      </div>
    </div>
  </>
}