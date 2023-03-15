import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateAdminComponent from '../../components/ManageComps/CreateAdminComponent'
import EditAdminComponent from '../../components/ManageComps/EditAdminComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_ADMIN, GET_ALL_ADMINS, MANAGEMENT_URL } from '../../config'


export default function AdminHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Admin</h2>
        <AddAndEditModalComponent value={"Add Admin"} component={CreateAdminComponent} />
        <br />
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_ADMINS} deleteUrl={MANAGEMENT_URL + DELETE_ADMIN} editComponent={EditAdminComponent} addComponent={CreateAdminComponent} />


        {/* <AddAndEditModalComponent value={"Edit Admin"} component={EditAdminComponent} /> */}
      </div>
    </>
  )
}
