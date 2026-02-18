import { DsaProblemList } from "../components/DsaProblemList";
import { useDsaProblems, useUpdateProblemStatus } from "../hooks/useDsaProblems";
export function DsaPage(){ const {data,isLoading}=useDsaProblems(); const update=useUpdateProblemStatus();
if(isLoading) return <div>Loading DSA problems...</div>;
return <div><h1 className="text-2xl font-semibold mb-4">DSA Problems</h1>{data && <DsaProblemList problems={data} onStatusChange={(id,status)=>update.mutate({problemId:id,status})} />}</div>; }
