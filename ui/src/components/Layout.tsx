import { Link, Outlet, useNavigate } from "react-router-dom";
import { authStorage } from "../auth/storage";

export function Layout() {
  const navigate = useNavigate();
  const meta = authStorage.getUserMeta();
  const logout = () => { authStorage.clear(); navigate("/login"); };
  return (
    <div className="min-h-screen">
      <header className="bg-slate-900 text-white px-6 py-3 flex items-center justify-between">
        <div className="font-bold">ai-dost</div>
        <nav className="space-x-4 text-sm">
          <Link to="/dashboard">Dashboard</Link><Link to="/dsa">DSA</Link><Link to="/profile">Profile</Link><Link to="/mock">Mock</Link><Link to="/projects">Projects</Link><Link to="/experience">Experience</Link>
        </nav>
        <div className="text-xs">{meta ? `Role: ${meta.roles?.[0] ?? "-"} | Plan: ${meta.plan}` : ""} <button className="ml-3 underline" onClick={logout}>Logout</button></div>
      </header>
      <main className="p-6"><Outlet /></main>
    </div>
  );
}
