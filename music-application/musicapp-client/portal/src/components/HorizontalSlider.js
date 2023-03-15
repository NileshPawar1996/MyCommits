import './HorizontalSlider.css'
import { url } from '../common/constants'

const HorizontalSlider = ({ items, title, onItemSelect }) => {

  return (
    <div className="slider-container">
      <div className="title">{title}</div>

      {items.map((item) => {
        return (
          <div
            className="item-container"
            onClick={() => {
              onItemSelect(item)
            }}>
            <img src={url + '/images/' + item.thumbnail} className="image" />
            <div className="item-title">{item.title}</div>
          </div>
        )
      })}
    </div>
  )
}

export default HorizontalSlider
