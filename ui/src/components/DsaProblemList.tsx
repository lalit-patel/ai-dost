import { Link } from "react-router-dom";
import { DsaProblemItem, ProblemStatus } from "../api/types";

type Props = { problems: DsaProblemItem[]; onStatusChange: (problemId: number, status: ProblemStatus) => void; };
const statuses: ProblemStatus[] = ["TODO", "IN_PROGRESS", "DONE", "REVISE"];
export function DsaProblemList({ problems, onStatusChange }: Props) {
  const grouped = problems.reduce<Record<string, DsaProblemItem[]>>((acc, p) => { acc[p.topic ?? "Other"] = [...(acc[p.topic ?? "Other"] ?? []), p]; return acc; }, {});
  return <div className="space-y-5">{Object.entries(grouped).map(([topic, items]) => <section key={topic} className="bg-white rounded shadow p-4"><h3 className="font-semibold mb-3">{topic}</h3><div className="space-y-2">{items.map((p) => <div key={p.id} className="grid grid-cols-12 gap-2 items-center border-b pb-2"><a className="col-span-4 text-blue-700 underline" href={p.link} target="_blank">{p.title}</a><span className="col-span-2 text-xs font-medium">{p.difficulty}</span><select className="col-span-3 border rounded px-2 py-1" value={p.status} onChange={(e)=>onStatusChange(p.id, e.target.value as ProblemStatus)}>{statuses.map(s=><option key={s} value={s}>{s}</option>)}</select><Link className="col-span-3 text-indigo-700 underline" to={`/dsa/${p.id}`}>Notes / Detail</Link></div>)}</div></section>)}</div>;
}
