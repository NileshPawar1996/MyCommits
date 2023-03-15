import { url } from '../common/constants'
import { useHistory } from 'react-router-dom'
import { useState } from 'react'
import './SongRow.css'

const SongRow = ({ song, index }) => {
  const [isPlaying, setIsPlaying] = useState(false)
  const history = useHistory()

  // create an object of audio with the song file
  const audio = new Audio(url + '/song/' + song.file)

  const togglePlaying = () => {
    if (isPlaying) {
      console.log('pause the music')
      audio.pause()
      setIsPlaying(false)
    } else {
      console.log('play the music')
      audio.play()
      setIsPlaying(true)
    }
  }

  return (
    <tr onClick={togglePlaying}>
      <td>{index + 1}</td>
      <td></td>
      <td>
        <img src={url + '/images/' + song.album.thumbnail} alt="" className="thumbnail-sm" />
        {song.title}
      </td>
      <td>
        {song.artistList.map((artist)=>{
            return artist.firstName + ' ' + artist.lastName + ', ';
        })}
      </td>
      <td>{song.duration}</td>
      <td>
        {isPlaying === true && (
          <img
            onClick={togglePlaying}
            className="btn-media"
            src="images/pause.png"
          />
        )}

        {isPlaying === false && (
          <img
            onClick={togglePlaying}
            className="btn-media"
            src="images/play.png"
          />
        )}
      </td>
    </tr>
  )
}

export default SongRow
