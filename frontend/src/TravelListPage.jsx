import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function TravelListPage(){
    const [travels, setTravels] = useState([])
    const navigate = useNavigate()
    
    //여행 목록 가져오기
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

    //로그아웃
    const handleLogout = () => {
        sessionStorage.removeItem('token')
        navigate("/login")
    }

    //여행상태 텍스트 변경
    const getStatusText = (status) =>{
        if (status === 'PLANNED') {
            return '계획'
        }

        if (status === 'ONGOING') {
            return '진행'
        }

        if (status === 'COMPLETED') {
            return '완료'
        }
    }

    //여행 추가
    const handleAddTravel = () => {
        navigate("/travels/new")
    }

    // 여행 상세 페이지로 이동
    const handleTravelClick = (id) => {
        navigate(`/travels/${id}`)
    }

    return (
        <main className='travel-page'>
            <header className="travel-header">
                <div>
                    <h1>TravelMate</h1>
                    <p>나의 여행을 관리해보세요.</p>
                </div>

                <button
                    className="logout-button"
                    onClick={handleLogout}
                >
                    로그아웃
                </button>
            </header>

            <section className="travel-list">
                <div className='travel-list-header'>
                    <h2><b>나의 여행</b></h2>
                    <button
                        className='add-travel-button'
                        onClick={handleAddTravel}
                    >
                        +
                    </button>
                </div>

                <div className="travel-cards">
                    {travels.map((travel) => (
                        <div 
                            key={travel.id} 
                            className='travel-card'
                            onClick={()=>handleTravelClick(travel.id)}
                        >

                            <div className="travel-card-header">
                                <div className='travel-title'>
                                    {travel.title}
                                </div>

                                <div className={`travel-status ${travel.status.toLowerCase()}`}>
                                    {getStatusText(travel.status)}
                                </div>
                            </div>

                            <div className="travel-info">
                                <div className='travel-destination'>
                                    📍 {travel.destination}
                                </div>

                                <div className='travel-date'>
                                    📅 {travel.startDate} ~ {travel.endDate}
                                </div>

                                <div className='travel-budget'>
                                    💰 {travel.budget?.toLocaleString()}원
                                </div>
                            </div>

                        </div>
                    ))}
                </div>
            </section>
        </main>
    )
}

export default TravelListPage