import './App.css'
import { BrowserRouter, Link, Route, Switch } from 'react-router-dom'
import Artists from './pages/Artists'
import Albums from './pages/Albums'
import Users from './pages/Users'
import AddArtist from './pages/AddArtist'
import AddAlbum from './pages/AddAlbum'
import AddSongsToAlbum from './pages/AddSongToAlbum'

function App() {
  return (
    <div>
      <BrowserRouter>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              Admin Panel
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to="/artists">
                    Artists
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/albums">
                    Albums
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/users">
                    Users
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container">
          <Switch>
            <Route path="/artists" component={Artists} />
            <Route path="/albums" component={Albums} />
            <Route path="/users" component={Users} />
            <Route path="/add-artist" component={AddArtist} />
            <Route path="/add-album" component={AddAlbum} />
            <Route path="/add-songs-to-album" component={AddSongsToAlbum} />
          </Switch>
        </div>
      </BrowserRouter>
    </div>
  )
}

export default App
