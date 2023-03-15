import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateModuleComponent from '../../components/ManageComps/CreateModuleComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_MODULE, GET_ALL_MODULES, MANAGEMENT_URL } from '../../config'

export default function ModuleHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Module</h2>
        {/* <GenerateTable getUrl={MANAGEMENT_URL+GET_MODULES_BY_COURSE+'/DAC'}  editComponent={0}  addComponent={} /> */}
        <AddAndEditModalComponent value={"Add Module"} component={CreateModuleComponent} />
        <br />
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_MODULES} deleteUrl={MANAGEMENT_URL + DELETE_MODULE} editComponent={CreateModuleComponent} addComponent={CreateModuleComponent} />
      </div>
    </>
  )
}
