import { useState } from "react";
import { useNavigate } from 'react-router-dom';

function TravelCreatePage(){

    const [title, setTitle] = useState('');
    const [destination, setDestination] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [budget, setBudget] = useState('');

    const navigate = useNavigate()

    const handleCreateTravel = async (event) => {  
        event.preventDefault();
        
        
        const token = sessionStorage.getItem('token');
        const response = await fetch('http://localhost:8080/travels',
            {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                title: title,
                destination: destination,
                startDate: startDate,
                endDate: endDate,
                budget: budget
            })
            }
        );

        if (response.ok) {
            console.log("여행 생성 성공");
            navigate('/travels')
        } else {
            console.log("여행 생성 실패");
        }
        console.log(response);

    }

    return (
    <main className="travel-create-page">
        <header className="travel-create-header">
            <div className="travel-create-logo">
                <h1>TravelMate</h1>
                <button
                    type="button"
                    className="back-button"
                    onClick={() => navigate('/travels')}
                > ← </button>
            </div>
        </header>
        <section className="travel-create-container">
            <div className="travel-create-title">
                <h1>새 여행 만들기</h1>
                <p>새로운 여행 계획을 추가해보세요.</p>
            </div>
        </section>
      <form
        className="travel-create-form"
        onSubmit={handleCreateTravel}>
            <div className="form-group">
                <label htmlFor="title">여행 제목</label>
                <input
                    id = "title"
                    type="text"
                    placeholder="여행 제목을 입력하세요"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
            </div>
        
            <div className="form-group">
                <label htmlFor="destination">목적지</label>
                <input
                    id = "destination"
                    type="text"
                    placeholder="목적지을 입력하세요"
                    value={destination}
                    onChange={(e) => setDestination(e.target.value)}
                />
            </div>
            
            <label htmlFor="startDate">일정</label>
            <div className="date-group">
                <div className="form-group">
                    <input
                        id = "startDate"
                        type="date"
                        placeholder="여행 시작일을 입력하세요"
                        value={startDate}
                        onChange={(e) => setStartDate(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <input
                        id = "endDate"
                        type="date"
                        placeholder="여행 종료일을 입력하세요"
                        value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}
                    />
                </div>
            </div>
        
            <div className="form-group">
                <label htmlFor="budget">예산</label>
                <input
                    id = "budget"
                    type="number"
                    placeholder="예산을 입력하세요"
                    value={budget}
                    onChange={(e) => setBudget(e.target.value)}
                />
            </div>


        <button type="submit">
          여행 추가
        </button>
      </form>
    </main>
  )
}

export default TravelCreatePage