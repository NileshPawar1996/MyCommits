import ROLES from "./roles"

// export const sessionStorage.getItem('curr_user') = sessionStorage.getItem('sessionStorage.getItem('curr_user')');
// export const sessionStorage.getItem('role') = ROLES.ROLE_STUDENT
export const USER_COURSE = "DAC"
export const USER_BATCH = 2

export const FORUM_SERVICE_URL = "http://13.235.17.37:9999"
// export const FORUM_SERVICE_URL = "http://edac.netlify.app"

export const MANAGEMENT_SERVICE_URL = 'http://13.233.107.158:8080/manage'
// export const MANAGEMENT_SERVICE_URL = 'http://edac.netlify.app/manage'


export const GET_BATCHES_BY_TEACHER = MANAGEMENT_SERVICE_URL + "/teacher/batches_by_teacher"
export const GET_BATCHNAME = MANAGEMENT_SERVICE_URL + "/batch"
export const GET_MODULENAME = MANAGEMENT_SERVICE_URL + "/module"
export const MODULE_COMPLETED_URL = MANAGEMENT_SERVICE_URL + "/course/modules/status"
export const GET_MODULES_BY_TEACHER = MANAGEMENT_SERVICE_URL + "/teacher/modules_by_teacher"

export const FEEDBACK_SERVICE_URL = "http://35.154.224.161:8080/feedback"

// export const FEEDBACK_SERVICE_URL = 'http://edac.netlify.app/feedback'

export const CHECK_FEEDBACK_SUBMITTED_URL = FEEDBACK_SERVICE_URL + "/submitted"
export const GET_CRITERIA_URL = FEEDBACK_SERVICE_URL + "/criteria/all"
export const ADD_FULL_FEEDBACK_URL = FEEDBACK_SERVICE_URL + "/addfull"
export const GET_FEEDBACKS = FEEDBACK_SERVICE_URL + "/module"
export const GET_RATINGS = FEEDBACK_SERVICE_URL + "/ratings"

export const QUIZ_SERVICE_URL = 'http://localhost:8081'
// export const MANAGEMENT_AWS = `http://192.168.128.209:8080/manage`


// export const URL = 'http://localhost:8080';
export const getAttendanceByStudentId = 'http://43.204.110.133:8080/attendance/studentId';
export const getSessionBySessionId = 'http://13.233.107.158:8080/manage/session';
export const getAttendanceBySessionIdStudentId = 'http://43.204.110.133:8080/attendance/studentIdSessionId';
export const uploadAttendanceFile = 'http://43.204.110.133:8080/attendance/uploadFile';
export const getAllModules = 'http://13.233.107.158:8080/manage/module/all';
export const getAllBatches = 'http://13.233.107.158:8080/manage/batch/all';
export const getAllCourses = 'http://13.233.107.158:8080/manage/course/all';
export const getAllBatchesByCourseId = 'http://13.233.107.158:8080/manage/batch/course/';
export const getAllModulesByCourseId = 'http://13.233.107.158:8080/manage/module/course/';
export const getAllSessionByModuleId = 'http://13.233.107.158:8080/manage/session/module/';
export const getSessionByModuleId = 'http://13.233.107.158:8080/manage/module/course/';

export const getAllSessionByBatchIdModuleId = 'http://13.233.107.158:8080/manage/session/session_by_batch_module/';


// -------------------------------- SERVICE URL'S -----------------------------------------------------

export const QUIZ_URL = 'http://192.168.128.209:8081'
export const MANAGEMENT_URL = 'http://13.233.107.158:8080/manage'
export const STUDENT_URL = 'http://43.204.110.133:8080'

export const MANAGEMENT_URL_FRONTEND = 'http://192.168.128.209:3000/manage'


// -------------------------------------------- ADMIN API'S ---------------------------------------------

export const CREATE_ADMIN = '/admin/add/'
export const GET_ADMIN_DETAILS = '/admin/get/'
export const EDIT_ADMIN = '/admin/edit/'
export const GET_ALL_ADMINS = '/admin/all/'
export const DELETE_ADMIN = '/admin/delete/'

// -------------------------------------------- TEACHER API'S -------------------------------------------

export const CREATE_TEACHER = '/teacher/add/'
export const GET_TEACHER_DETAILS = '/teacher/get/'
export const EDIT_TEACHER = '/teacher/edit/'
export const GET_ALL_TEACHERS = '/teacher/all/'
export const DELETE_TEACHER = '/teacher/delete/'

// -------------------------------------------- STUDENTS API'S ------------------------------------------

export const CREATE_STUDENT = '/student/add/'
export const GET_STUDENT_DETAILS = '/student/'

// -------------------------------------------- COURSE API'S --------------------------------------------

export const GET_ALL_COURSES = '/course/all/'
export const CREATE_COURSE = '/course/add/'
export const GET_COURSE = '/course/'
export const DELETE_COURSE = '/course/'


// ------------------------------------------- BATCH API'S ---------------------------------------------

export const GET_ALL_BATCHES_BY_COURSE = '/batch/course/'
export const GET_BATCH_DETAILS = '/batch/'
export const CREATE_BATCH = '/batch/add/'
export const GET_ALL_BATCHES = '/batch/all/'
export const DELETE_BATCH = '/batch/'

// ------------------------------------------- SESSION API'S -------------------------------------------

export const ADD_SESSION = '/session/add/'
export const GET_ALL_LATEST_SESSIONS = '/session/desc_order/10'
export const GET_CURRENT_SESSION = '/session/'
export const EDIT_SESSION = '/session/'
export const DELETE_SESSION = '/session/'

// ------------------------------------------- MODULE API'S --------------------------------------------

export const GET_MODULES_BY_COURSE = '/module/course/'
export const CREATE_MODULE = '/module/add/'
export const DELETE_MODULE = '/module/'
export const GET_ALL_MODULES = '/module/all/'

// ------------------------------------------ USER'S SERVICE ------------------------------------

export const CREATE_USER = '/add'
export const AUTH_SERVICE = 'http://192.168.113.209:8085/auth'