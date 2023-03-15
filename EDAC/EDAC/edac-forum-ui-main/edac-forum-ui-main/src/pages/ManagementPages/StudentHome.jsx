import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateStudentComponent from '../../components/ManageComps/CreateStudentComponent'

export default function StudentHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Student</h2>
        {/* <GenerateTable getUrl={STUDENT_URL+GET} /> */}
        {/* <GenerateTable getUrl={MANAGEMENT_URL+} editComponent={EditStudentComponent} addComponent={CreateStudentComponent} /> */}
        <AddAndEditModalComponent value={"Add Student"} component={CreateStudentComponent} />
        {/* <AddAndEditModalComponent value={"Edit Student"} component={EditStudentComponent} /> */}
      </div>
    </>
  )
}
