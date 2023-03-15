import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { url } from '../common/constants'
import ArtistRow from '../comopnents/ArtistRow'

const Artists = () => {
  // maintain the state
  const [artists, setArtists] = useState([])

  // do something automatically
  // []:
  // - accepts a variable and keeps watching the change
  // - when the variable state changes, the function (1st param) gets called
  // - keep the second param empty to execute something when the component gets loaded
  useEffect(() => {
    console.log(`Artists component got loaded`)
    getArtists()
  }, [])

  const getArtists = () => {
    axios.get(url + '/artists').then((response) => {
      const result = response.data
      if (result.status === 'success') {
        setArtists(result.data)
      } else {
        alert('error while loading list of artists')
      }
    })
  }

  return (
    <div>
      <h1 className="page-title">Artists</h1>

      <Link to="/add-artist">
        <a className="btn btn-success">Add Artist</a>
      </Link>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>id</th>
            <th></th>
            <th>Name</th>
            <th>Type</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {artists.map((artist) => {
            return <ArtistRow artist={artist} />
          })}
        </tbody>
      </table>
    </div>
  )
}

export default Artists
