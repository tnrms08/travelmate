import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage';
import TravelListPage from './TravelListPage';
import './App.css'
import ProtectedRoute from './ProtectEDRoute';
import TravelCreatePage from './TravelCreatePage';
import TravelDetailPage from './TravelDetailPage';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<LoginPage />}/>
        <Route path='/travels'
               element={
               <ProtectedRoute>
                <TravelListPage />
               </ProtectedRoute>
              }/>
        <Route path='/travels/new'
               element={
               <ProtectedRoute>
                <TravelCreatePage />
               </ProtectedRoute>
              }/>
        <Route path='/travels/:id'
               element={
               <ProtectedRoute>
                <TravelDetailPage />
               </ProtectedRoute>
               }/>
      </Routes>
    </BrowserRouter>
  )
}

export default App