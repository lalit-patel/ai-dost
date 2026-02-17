import clients from "./client";
import { DsaProblemItem, ProblemStatus } from "./types";

export const dsaClient = {
  getMyProblems: async () => (await clients.dsa.get<DsaProblemItem[]>("/dsa/me/problems")).data,
  updateStatus: async (problemId: number, status: ProblemStatus) => clients.dsa.put(`/dsa/me/problems/${problemId}/status`, { status }),
  updateNotes: async (problemId: number, notes: string) => clients.dsa.put(`/dsa/me/problems/${problemId}/notes`, { notes }),
};
