import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useHistory, useLocation } from 'react-router-dom'
import { url } from '../common/constants'
import './AddSongToAlbum.css'


const AddSongsToAlbum = () => {
  const [title, setTitle] = useState('')
  const [duration, setDuration] = useState('')
  const [songFile, setSongFile] = useState(undefined)
  const [artist, setArtist] = useState(-1)
  const [artists, setArtists] = useState([])

  // used to go to another component
  const history = useHistory()

  // used to get the data passed by previous component
  const location = useLocation()

  // selected album in the previous page
  const album = location.state.album

  useEffect(() => {
    getArtists()
  }, [])
  
  const getArtists = () => {
    axios.get(url + '/artists').then((response) => {
      const result = response.data
      if (result.status === 'success') {
        if (result.data.length > 0) {
          // select the first artist from the list
          // select the default artist
          setArtists(result.data)
          setArtist(result.data[0].id)
        }
      } else {
        alert('error while loading list of artists')
      }
    })
  }
  
  const addSongToDB = () => {
    if (title.length === 0) {
      alert('enter title')
    } else if (duration.length === 0) {
      alert('enter duration')
    } else if (!songFile) {
      alert('select song File')
    } else {
      const data = new FormData()
      data.append('title', title)
      data.append('file', songFile)
      data.append('duration', duration)
      data.append('album.id', album.id)
      data.append('artistList[0].id', artist)

      // send the song info to the API
      axios.post(url + '/songs', data).then((response) => {
        const result = response.data
        if (result.status === 'success') {
          alert('successfully added new track to the album')
          history.push('/albums')
        } else {
          console.log(result.error)
          alert('error while adding the song to the album')
        }
      })
    }
  }

  return (
    <div>
      <h1 className="page-title">{album.title}</h1>
      <div className="row">
        <div className="col">
          <img
            src={url + '/images/' + album.thumbnail}
            alt=""
            className="thumbnail-large"
          />
        </div>
        <div className="col-10">
          <div className="mb-3">
            <label htmlFor="">Title</label>
            <input
              onChange={(e) => {
                setTitle(e.target.value)
              }}
              type="text"
              className="form-control"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="">Duration</label>
            <input
              onChange={(e) => {
                setDuration(e.target.value)
              }}
              type="text"
              className="form-control"
            />
          </div>
          <div className="mb-3">
        <label htmlFor="">Artist</label>
        <select
          onChange={(e) => {
            setArtist(e.target.value)
          }}
          className="form-control">
          {artists.map((artist) => {
            return (
              <option value={artist.id}>
                {artist.firstName} {artist.lastName}
              </option>
            )
          })}
        </select>
      </div>

          <div className="mb-3">
            <label htmlFor="">Track File</label>
            <input
              onChange={(e) => {
                setSongFile(e.target.files[0])
              }}
              accept="audio/*"
              type="file"
              className="form-control"
            />
          </div>

          <div className="mb-3">
            <button onClick={addSongToDB} className="btn btn-success">
              Add
            </button>

            <Link to="/albums">
              <a className="btn btn-warning">Back</a>
            </Link>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AddSongsToAlbum
