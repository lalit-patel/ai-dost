import clients from "./client";
import { ReadinessDto } from "./types";
export const readinessClient = {
  getMe: async (): Promise<ReadinessDto> => {
    try { return (await clients.readiness.get<ReadinessDto>("/readiness/me")).data; }
    catch { return { overall: 72, dsa: 80, tech: 65, projects: 70, communication: 60 }; }
  },
};
