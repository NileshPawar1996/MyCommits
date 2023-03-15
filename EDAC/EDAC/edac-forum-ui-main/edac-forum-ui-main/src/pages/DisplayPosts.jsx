import { useParams } from "react-router-dom";
import axios from "axios";
import { FORUM_SERVICE_URL } from "../config"
import { useEffect, useState } from "react";
import Post from "../components/Post";
import AddPost from "../components/AddPost"
import PagingControl from "../components/PagingControl";

export default function DisplayPosts() {
  const limit = 5;
  const { userid, role } = useParams();
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [updated, setUpdated] = useState(true);
  const [hasMore, setHasMore] = useState(false);
  const [pageNo, setPageNo] = useState(0); // first page : 0

  const apiURL = `${FORUM_SERVICE_URL}/post/all?skip=${limit * pageNo}&limit=${limit}&${(userid != undefined && role != undefined)
    ? `userId=${userid}&role=${role}`
    : ""
    }`

  useEffect(() => {
    setUpdated(true);
  }, [apiURL, pageNo, userid, role])

  useEffect(() => {

    const controller = new AbortController();
    if (!updated) return
    setLoading(true);
    axios.get(apiURL, { signal: controller.signal })
      .then(res => {
        console.log(res);
        setPosts(res.data.data.posts);
        setHasMore(res.data.data.hasMore);
        setUpdated(false);
        setLoading(false);
      })
      .catch(err => {
        console.log(err);
        setPosts([]);
        setLoading(false);
        setUpdated(false);
      })

    return () => { controller.abort(); }
  }, [updated, apiURL])

  return <>
    <AddPost setUpdated={setUpdated} />
    <PagingControl hasMore={hasMore} setPageNo={setPageNo} pageNo={pageNo} />
    {posts.length > 0
      ? <>
        {/* <p><strong>user {userid} role {role}</strong></p> */}
        <div className="d-flex gap-3 flex-column mt-3">
          {
            posts?.map(post => <Post key={post._id} post={post} currUser={sessionStorage.getItem('curr_user')} setUpdated={setUpdated} />)
          }
        </div>
      </>
      : loading ? <>loading...</> : <>no posts yet</>}
  </>
}