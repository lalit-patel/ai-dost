import { Link } from "react-router-dom";
import { ReadinessCard } from "../components/ReadinessCard";
export function DashboardPage(){ return <div className="space-y-4"><h1 className="text-2xl font-semibold">Dashboard</h1><ReadinessCard /><div className="grid grid-cols-2 md:grid-cols-4 gap-3">{[{to:"/dsa",label:"DSA"},{to:"/mock",label:"Mock Interviews"},{to:"/projects",label:"Projects"},{to:"/profile",label:"Profile"}].map(c=><Link key={c.to} to={c.to} className="bg-white p-4 rounded shadow hover:bg-slate-100">{c.label}</Link>)}</div></div>; }
