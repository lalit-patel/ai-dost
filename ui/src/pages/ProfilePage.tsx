import { FormEvent, useEffect, useState } from "react";
import { useProfile, useUpdateProfile } from "../hooks/useProfile";
export function ProfilePage(){ const {data}=useProfile(); const update=useUpdateProfile(); const [form,setForm]=useState<any>({}); useEffect(()=>{ if(data) setForm(data); },[data]);
const submit=(e:FormEvent)=>{e.preventDefault(); update.mutate(form);};
return <div className="max-w-2xl"><h1 className="text-2xl font-semibold mb-4">Profile</h1><form onSubmit={submit} className="bg-white p-4 rounded shadow space-y-2">{["name","email","currentRole","yearsOfExperience","primaryTechStack","targetRoleGoals","goals"].map(f=><input key={f} className="w-full border p-2" placeholder={f} value={form?.[f] ?? ""} onChange={e=>setForm({...form,[f]:e.target.value})} />)}<button className="bg-slate-900 text-white px-3 py-1 rounded">Save Profile</button></form></div>; }
