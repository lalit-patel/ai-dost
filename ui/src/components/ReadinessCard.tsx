import { useReadiness } from "../hooks/useReadiness";
export function ReadinessCard() {
  const { data, isLoading } = useReadiness();
  if (isLoading) return <div className="p-4 bg-white rounded shadow">Loading readiness...</div>;
  if (!data) return null;
  return <div className="p-4 bg-white rounded shadow"><h3 className="font-semibold mb-2">Readiness Snapshot</h3><p>Overall: <b>{data.overall}</b></p><p className="text-sm">DSA {data.dsa} | Tech {data.tech} | Projects {data.projects} | Communication {data.communication}</p></div>;
}
