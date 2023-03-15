import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateCourseComponent from '../../components/ManageComps/CreateCourseComponent'
import EditCourseComponent from '../../components/ManageComps/EditCourseComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_COURSE, GET_ALL_COURSES, MANAGEMENT_URL } from '../../config'

export default function CourseHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Courses</h2>
        <AddAndEditModalComponent value={"Add Course"} component={CreateCourseComponent} />
        <br></br>
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_COURSES} deleteUrl={MANAGEMENT_URL + DELETE_COURSE} editComponent={EditCourseComponent} addComponent={CreateCourseComponent} />

        {/* <AddAndEditModalComponent value={"Edit Course"} component={Edi} /> */}
      </div>
    </>
  )
}
