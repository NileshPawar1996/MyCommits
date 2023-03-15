import { useLocation } from 'react-router'
import { url } from '../common/constants'
import SongRow from '../components/SongRow'
import './SongsList.css'

const SongsList = () => {
  const location = useLocation()
  const songs = location.state.songs

  return (
    <div className="songs-list-container">
      <div className="row">
        <div className="col-2">
          <img
            className="thumbnail"
            src={url + '/images/' + location.state.thumbnail}
            alt=""
          />
        </div>
        <div className="col">
          <h3 className="title">{location.state.title}</h3>
          <div>Created by Gaana</div>
        </div>
      </div>

      <div className="table-container">
        <table className="table table-hover">
          <thead>
            <tr>
              <th>#</th>
              <th></th>
              <th>title</th>
              <th>artist</th>
              <th>duration</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {songs.map((song, index) => {
              return <SongRow song={song} index={index} />
            })}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default SongsList
