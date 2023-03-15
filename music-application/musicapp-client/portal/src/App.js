import './App.css'
import { BrowserRouter, Link, Route, Switch } from 'react-router-dom'
import Home from './pages/Home'
import Discover from './pages/Discover'
import Browse from './pages/Browse'
import MyMusic from './pages/MyMusic'
import Signin from './pages/Signin'
import Signup from './pages/Signup'
import SongsList from './pages/SongsList'

function App() {
  return (
    <div>
      <BrowserRouter>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              Gaana
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
                  <Link className="nav-link" to="/home">
                    Home
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/browse">
                    Browse
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/discover">
                    Discover
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/mymusic">
                    My Music
                  </Link>
                </li>
                <li>
                  <Link className="nav-link" to="/signin">
                    Signin
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container">
          <Switch>
            <Route path="/home" component={Home} />
            <Route path="/discover" component={Discover} />
            <Route path="/browse" component={Browse} />
            <Route path="/mymusic" component={MyMusic} />
            <Route path="/signin" component={Signin} />
            <Route path="/signup" component={Signup} />
            <Route path="/songs-list" component={SongsList} />
          </Switch>
        </div>
      </BrowserRouter>
    </div>
  )
}

export default App
