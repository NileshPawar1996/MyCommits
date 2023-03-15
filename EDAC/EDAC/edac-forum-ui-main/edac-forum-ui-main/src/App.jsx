import { Routes, Route } from "react-router-dom";
import DisplayPosts from "./pages/DisplayPosts";
import FullPost from "./components/FullPost";
import Main from "./pages/Main"
// import { sessionStorage.getItem('curr_user') } from "./config";
import AllFeedbacks from "./pages/AllFeedbacks";
import ProtectedRoute from "./navs/ProtectedRoute"
import Login from "./pages/ManagementPages/LoginPage"
import ViewFeedbackComments from "./components/ViewFeedbackComments";
import ShowQuizzes from './pages/QuizPages/showQuizzes'
import Exam from './pages/QuizPages/exam'
import ExamResults from './pages/QuizPages/examResults'
import CreateExam from './pages/QuizPages/createExam'
import AddQuestionsToExam from './pages/QuizPages/addQuestionsToExam'
import CreateQuestion from './pages/QuizPages/createQuestion'
import InsertAttendence from './pages/StudentPages/InsertAttendanceFile'
import AttendanceTest from './pages/StudentPages/attendanceTest'
import ModuleHome from './pages/ManagementPages/ModuleHome'
import AdminHome from './pages/ManagementPages/AdminHome'
import StudentHome from './pages/ManagementPages/StudentHome'
import TeacherHome from './pages/ManagementPages/TeacherHome'
import SessionHome from './pages/ManagementPages/SessionHome'
import CourseHome from './pages/ManagementPages/CourseHome'
import BatchHome from './pages/ManagementPages/BatchHome'

function App() {
  return <>
    <Routes>
      <Route path="/" element={<Main title="Home" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/forum" element={<Main title="Forum" />} >
        <Route index element={<ProtectedRoute el={<DisplayPosts />} />} />
        {/* <Route path="add" element={<AddPost />} /> */}
        <Route path=":userid/:role" element={<DisplayPosts />} />
        <Route path="post/:postid" element={<FullPost currUser={sessionStorage.getItem('curr_user')} />} />
      </Route>
      <Route path="/quizzes" element={<Main title="Quiz" />} >
        <Route index element={<ShowQuizzes />} />
        <Route path="performance" element={<>analytics page</>} />
      </Route>
      <Route path="/feedback" element={<Main title={"Feedbacks"} />}>
        <Route index element={<AllFeedbacks />} />
        <Route path="comments" element={<ViewFeedbackComments />} />
      </Route>
      <Route path="/giveexam" element={<Exam />} />
      <Route path="/examResults" element={<ExamResults />} />
      <Route path="/exam" element={<Main />}>
        <Route path="/exam" element={<CreateExam />} />
        <Route path="addquestion" element={<AddQuestionsToExam />} />
        <Route path="createquestion" element={<CreateQuestion />} />
      </Route>
      <Route path="/attendance" element={<Main />}>
        <Route path="upload" element={<InsertAttendence />} />
        <Route path="view" element={<AttendanceTest />} />
      </Route>
      <Route path="/manage" element={<Main />}>
        <Route index element={<CourseHome />} />
        <Route path="admin" element={<AdminHome />} />
        <Route path="staff" element={<TeacherHome />} />
        <Route path="student" element={<StudentHome />} />
        <Route path="session" element={<SessionHome />} />
        <Route path="course" element={<CourseHome />} />
        <Route path="batch" element={<BatchHome />} />
        <Route path="module" element={<ModuleHome />} />
      </Route>
      <Route path="*" element={<Main title="404 Not found :(" />} />
    </Routes>
  </>
}

export default App;