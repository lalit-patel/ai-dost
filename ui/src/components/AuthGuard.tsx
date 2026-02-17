import { Navigate, Outlet } from "react-router-dom";
import { authStorage } from "../auth/storage";
export function AuthGuard() { return authStorage.getToken() ? <Outlet /> : <Navigate to="/login" replace />; }
