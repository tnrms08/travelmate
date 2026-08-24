import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage';
import TravelListPage from './TravelListPage';
import './App.css'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<LoginPage />}/>
        <Route path='/travels' element={<TravelListPage />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App