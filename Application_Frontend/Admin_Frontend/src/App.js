import logo from './logo.svg';
import axios from 'axios';
import './App.css';
import Login from './components/Login';
import { useNavigate } from 'react-router-dom';
import { Route, BrowserRouter,Routes } from 'react-router-dom';
import { useEffect } from 'react';
// import Home from './components/Home';
import Admin from './components/Admin';
// import Register from './components/Register';
// import WelcomeComponent from './components/WelcomeComponent';

function App() {

  useEffect(()=>{
    // axios.interceptors.request.use(
    //   (config) => {
    //       // if (this.isUserLoggedIn()) {
    //           config.headers.authorization = "token"
    //       // }
    //       return config
    //   }
    // )
  })

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          {/* <Route path="/register" element={<Register />} /> */}
          {/* <Route path="/home" element={<Home />} /> */}
          <Route path="/admin" element={<Admin />} />
        </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
