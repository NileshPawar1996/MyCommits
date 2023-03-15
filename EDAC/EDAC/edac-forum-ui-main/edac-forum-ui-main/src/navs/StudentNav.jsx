// import { sessionStorage.getItem('curr_user'), sessionStorage.getItem('role') } from "../config";
import SideNavbar from "./SideNavbar";

export default function StudentNav() {
  const paths = [
    {
      type: "simple",
      to: "/manage/session",
      text: "Schedule",
      icon: "bi-calendar2-week"
    },
    {
      type: "nested",
      to: "/quizzes",
      text: "Quizzes",
      icon: "bi-patch-question",
      paths: [{
        to: "/performance",
        text: "Performance",
        icon: "bi-chart",
      }]
    },
    {
      type: "simple",
      to: "/feedback",
      text: "Feedbacks",
      icon: "bi-pen"
    },
    // {
    //   type: "simple",
    //   to: "/feedback/comments",
    //   text: "Feedback Comments",
    //   icon: "bi-pen"
    // },
    {
      type: "simple",
      to: "/attendance/view",
      text: "View Attendance",
      icon: "bi-attendance"
    },
    {
      type: "nested",
      to: "/forum",
      text: "Forum",
      icon: "bi-people",
      paths: [{
        to: `/${sessionStorage.getItem('curr_user')}/${sessionStorage.getItem('role')}`,
        text: "My posts",
        icon: "bi-chat-quote"
      }]
    },
  ];

  return <SideNavbar paths={paths} />
}