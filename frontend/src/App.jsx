import { useState } from 'react'
import './App.css'

function App() {
  const [loginId, setLoginId] = useState('')
  const [password, setPassword] = useState('')

  const handleLogin = async (event) => {
    event.preventDefault();

    const response = await fetch('http://localhost:8080/users/login', 
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          loginId: loginId,
          password: password
        })
      }
    );
    console.log(response);
  };


  return (
    <main className="login-page">
      <section className="login-container">
        <div className="login-header">
          <h1>TravelMate</h1>
          <p>여행을 계획하고 기록하고 공유하세요.</p>
        </div>

        <form 
          className="login-form"
          onSubmit={handleLogin}
        >
          <div className="form-group">
            <label htmlFor="loginId">아이디</label>
            <input
              id="loginId"
              type="text"
              placeholder="아이디를 입력하세요"
              value={loginId}
              onChange={(e) => setLoginId(e.target.value)}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">비밀번호</label>
            <input
              id="password"
              type="password"
              placeholder="비밀번호를 입력하세요"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <button type="submit">로그인</button>
        </form>

        <div className="signup-link">
          <span>아직 회원이 아니신가요?</span>
          <button type="button">회원가입</button>
        </div>
      </section>
    </main>
  )
}

export default App