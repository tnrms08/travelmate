import { useState, useEffect } from 'react';

function TravelListPage(){
    const [travels, setTravels] = useState([])
    
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
        </main>
    )
}

export default TravelListPage