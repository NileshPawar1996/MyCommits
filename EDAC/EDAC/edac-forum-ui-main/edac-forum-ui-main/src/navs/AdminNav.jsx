// import { sessionStorage.getItem('role'), sessionStorage.getItem('curr_user') } from "../config";
import SideNavbar from "./SideNavbar";

export default function AdminNav() {
  const paths = [
    {
      type: "nested",
      to: "/manage",
      text: "Manage",
      icon: "bi-gear",
      paths: [
        {
          to: "/course",
          text: "Courses",
          icon: "",
        },
        {
          to: "/batch",
          text: "Batches",
          icon: "",
        },
        {
          to: "/student",
          text: "Students",
          icon: "",
        },
        {
          to: "/staff",
          text: "Staff",
          icon: "",
        },
        {
          to: "/session",
          text: "Sessions",
          icon: "",
        },
        {
          to: "/module",
          text: "Modules",
          icon: "",
        },
        {
          to: "/admin",
          text: "Admins",
          icon: "",
        }
      ]
    },
    {
      type: "nested",
      to: "/exam",
      text: "Exam",
      icon: "bi-pad",
      paths: [{
        to: "/",
        text: "Create Exam",
        icon: "bi-clipboard-plus",
      },
      {
        to: "/addquestion",
        text: "Add Questions To Exam",
        icon: "bi-plus",
      },
      {
        to: "/createquestion",
        text: "Create Question",
        icon: "bi-plus",
      }],
    },
    {
      type: "simple",
      to: "/feedback/comments",
      text: "Feedbacks",
      icon: "bi-chart"
    },
    {
      type: "simple",
      to: "/attendance/upload",
      text: "Upload Attendance",
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