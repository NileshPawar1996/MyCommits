import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { url } from '../common/constants'
import AlbumRow from '../comopnents/AlbumRow'

const Albums = () => {
  // maintain the state
  const [albums, setAlbums] = useState([])

  // do something automatically
  // []:
  // - accepts a variable and keeps watching the change
  // - when the variable state changes, the function (1st param) gets called
  // - keep the second param empty to execute something when the component gets loaded
  useEffect(() => {
    console.log(`Albums component got loaded`)
    getAlbums()
  }, [])

  const getAlbums = () => {
    axios.get(url + '/albums').then((response) => {
      const result = response.data
      if (result.status === 'success') {
        setAlbums(result.data)
      } else {
        alert('error while loading list of albums')
      }
    })
  }

  return (
    <div>
      <h1 className="page-title">Albums</h1>

      <Link to="/add-album">
        <a className="btn btn-success">Add Album</a>
      </Link>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>id</th>
            <th></th>
            <th>Name</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {albums.map((album) => {
            return <AlbumRow album={album} />
          })}
        </tbody>
      </table>
    </div>
  )
}

export default Albums
