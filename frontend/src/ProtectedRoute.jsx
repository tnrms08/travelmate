import { Navigate } from "react-router-dom";

function ProtectedRoute({children}){
    const token = sessionStorage.getItem('token')
    if(!token)  //token이 없는 경우
        return <Navigate to="/login" />;
    else        //token이 있는 경우
        return children;
}

export default ProtectedRoute