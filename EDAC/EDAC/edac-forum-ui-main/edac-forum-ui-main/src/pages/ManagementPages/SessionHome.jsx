import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateSessionComponent from '../../components/ManageComps/CreateSessionComponent'
import EditSessionComponent from '../../components/ManageComps/EditSessionComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_SESSION, GET_ALL_LATEST_SESSIONS, MANAGEMENT_URL } from '../../config'

export default function SessionHome() {
  var role = sessionStorage.getItem("role");
  return (
    <>
      <div className='container-md'>
        <h2>Manage Session</h2>
        
        {role == "ROLE_STUDENT" ? null : <AddAndEditModalComponent value={"Add Session"} component={CreateSessionComponent} />}
        <br />
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_LATEST_SESSIONS} deleteUrl={MANAGEMENT_URL + DELETE_SESSION} editComponent={EditSessionComponent} addComponent={CreateSessionComponent} />

        {/* <AddAndEditModalComponent value={"Edit Admin"} component={EditAdminComponent} /> */}
      </div>
    </>
  )
}
