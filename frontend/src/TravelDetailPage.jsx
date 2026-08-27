import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function TravelDetailPage() {
    const [travel, setTravel] = useState(null);
    const [openScheduleId, setOpenScheduleId] = useState(null);

    const { id } = useParams();
    const navigate = useNavigate();

    // 여행 상세 정보 가져오기
    const getTravel = async () => {
        const token = sessionStorage.getItem('token');

        const response = await fetch(
            `http://localhost:8080/travels/${id}/detail`,
            {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }
        );

        console.log(response);

        const data = await response.json();
        console.log(data);

        setTravel(data);
    };

    useEffect(() => {
        getTravel();
    }, [id]);

    // 일정 클릭
    const handleScheduleClick = (scheduleId) => {
        if (openScheduleId === scheduleId) {
            setOpenScheduleId(null);
        } else {
            setOpenScheduleId(scheduleId);
        }
    };

    // 여행 상태 텍스트
    const getStatusText = (status) => {
        if (status === 'PLANNED') {
            return '계획';
        }

        if (status === 'ONGOING') {
            return '진행';
        }

        if (status === 'COMPLETED') {
            return '완료';
        }

        return status;
    };

    // 일정 시간 표시
    const getTimeText = (dateTime) => {
        return dateTime.substring(11, 16);
    };

    if (!travel) {
        return <div>여행 정보를 불러오는 중...</div>;
    }

    const schedules = travel.schedules || [];

    return (
        <main className="travel-detail-page">
            <header className="travel-detail-header">
                <button
                    type="button"
                    onClick={() => navigate('/travels')}
                > ← </button>

                <div>
                    <h1>{travel.title}</h1>
                    <p>{travel.destination}</p>
                </div>
            </header>


            <section className="travel-detail-info">
                <div className="travel-detail-status">
                    {getStatusText(travel.status)}
                </div>
                <div>
                    📅 {travel.startDate} ~ {travel.endDate}
                </div>
                <div>
                    💰 {travel.budget?.toLocaleString()}원
                </div>
            </section>

            <section className="schedule-section">
                <div className="schedule-header">
                    <h2>여행 일정</h2>
                </div>
                {schedules.length === 0 ? (
                    <p>등록된 일정이 없습니다.</p>
                ) : (
                    schedules.map((schedule) => (
                        <div
                            key={schedule.id}
                            className="schedule-item"
                        >
                            <div
                                className="schedule-summary"
                                onClick={() =>
                                    handleScheduleClick(schedule.id)
                                }
                            >
                                <span className="schedule-time">
                                    {getTimeText(schedule.startTime)}
                                </span>
                                <span className="schedule-title">
                                    {schedule.title}
                                </span>
                                <span className="schedule-arrow">
                                    {openScheduleId === schedule.id
                                        ? '▲'
                                        : '▼'}
                                </span>
                            </div>

                            {openScheduleId === schedule.id && (
                                <div className="schedule-detail">
                                    <p>📍 장소: {schedule.place || '-'}</p>
                                    <p>🚗 교통: {schedule.transportation || '-'}</p>
                                    <p>🍴 식사: {schedule.meal || '-'}</p>
                                    <p>🏨 숙소: {schedule.accommodation || '-'}</p>
                                </div>
                            )}
                        </div>
                    ))
                )}
            </section>
        </main>
    );
}

export default TravelDetailPage;