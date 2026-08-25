import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function TravelListPage(){
    const [travels, setTravels] = useState([])
    const navigate = useNavigate()
    
    const getTravels = async () => {
    const token = sessionStorage.getItem('token');
    const response = await fetch('http://localhost:8080/travels', 
        {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
        }
    );
    console.log(response);

    const data = await response.json();
    console.log("여행 목록: ", data);
    setTravels(data);
    };

    useEffect(()=>{
        getTravels()
    },[])

    const handleLogout = () => {
        sessionStorage.removeItem('token')
        navigate("/login")
    }

    return(
        <main className='travel-list'>
            <div className="travel-list">
                <h2>여행 목록</h2>
                <ul>
                {travels.map((travel) => (
                    <li key={travel.id}>{travel.title}</li>
                ))}
                </ul>
            </div>
            <button onClick={handleLogout}>로그아웃</button>
        </main>
    )
}

export default TravelListPage