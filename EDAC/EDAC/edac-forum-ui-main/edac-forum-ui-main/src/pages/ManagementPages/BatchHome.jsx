import React from 'react'
import AddAndEditModalComponent from '../../components/ManageComps/AddAndEditModalComponent'
import CreateBatchComponent from '../../components/ManageComps/CreateBatchComponent'
import GenerateTable from '../../components/ManageComps/GenerateTable'
import { DELETE_BATCH, GET_ALL_BATCHES, MANAGEMENT_URL } from '../../config'

export default function BatchHome() {
  return (
    <>
      <div className='container-md'>
        <h2>Manage Batches</h2>
        <AddAndEditModalComponent value={"Add Batch"} component={CreateBatchComponent} />
        <br></br>
        <GenerateTable getUrl={MANAGEMENT_URL + GET_ALL_BATCHES} deleteUrl={MANAGEMENT_URL + DELETE_BATCH} editComponent={CreateBatchComponent} addComponent={CreateBatchComponent} />

        {/* <AddAndEditModalComponent value={"Edit Batch"} component={Ed} /> */}
      </div>
    </>
  )
}
