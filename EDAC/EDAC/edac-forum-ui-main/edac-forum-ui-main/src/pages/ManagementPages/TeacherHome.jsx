import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateTeacherComponent from '../../components/ManageComps/CreateTeacherComponent'
import EditTeacherComponent from '../../components/ManageComps/EditTeacherComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_TEACHER, GET_ALL_TEACHERS, MANAGEMENT_URL } from '../../config'

export default function TeacherHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Teacher</h2>
        <AddAndEditModalComponent value={"Create Teacher"} component={CreateTeacherComponent} />
        <br />
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_TEACHERS} deleteUrl={MANAGEMENT_URL + DELETE_TEACHER} editComponent={EditTeacherComponent} AddComponent={CreateTeacherComponent} />
        {/* <AddAndEditModalComponent value={"Add Teacher"} component={CreateTeacherComponent} /> */}

      </div>
    </>
  )
}
