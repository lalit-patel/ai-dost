import { useMemo, useState } from "react";
import { useParams } from "react-router-dom";
import { useDsaProblems, useUpdateProblemNotes } from "../hooks/useDsaProblems";
import { aiGatewayClient } from "../api/aiGatewayClient";

export function DsaProblemDetailPage(){ const {problemId}=useParams(); const {data}=useDsaProblems(); const updateNotes=useUpdateProblemNotes();
const problem=useMemo(()=>data?.find(p=>String(p.id)===problemId),[data,problemId]); const [notes,setNotes]=useState(problem?.notes ?? ""); const [aiResponse,setAiResponse]=useState("");
if(!problem) return <div>Problem not found.</div>;
return <div className="space-y-4"><h1 className="text-2xl font-semibold">{problem.title}</h1><a className="text-blue-700 underline" target="_blank" href={problem.link}>Open Problem</a><p>{problem.topic} • {problem.difficulty}</p>
<div className="bg-white p-4 rounded shadow"><h2 className="font-semibold mb-2">Notes</h2><textarea className="w-full border p-2 min-h-36" value={notes} onChange={e=>setNotes(e.target.value)} /><button className="mt-2 bg-slate-900 text-white px-3 py-1 rounded" onClick={()=>updateNotes.mutate({problemId:Number(problemId),notes})}>Save Notes</button></div>
<div className="bg-white p-4 rounded shadow"><h2 className="font-semibold mb-2">AI Panel (placeholder)</h2><div className="space-x-2"><button className="bg-indigo-600 text-white px-3 py-1 rounded" onClick={async()=>setAiResponse(await aiGatewayClient.explainProblem(problem.title))}>Explain problem (AI)</button><button className="bg-indigo-600 text-white px-3 py-1 rounded" onClick={async()=>setAiResponse(await aiGatewayClient.discussApproach(problem.title, notes))}>Discuss my approach (AI)</button></div><p className="mt-3 text-sm">{aiResponse}</p></div></div>; }
