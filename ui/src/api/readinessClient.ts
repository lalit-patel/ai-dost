import clients from "./client";
import { ReadinessDto } from "./types";

export const readinessClient = {
  getMe: async (): Promise<ReadinessDto> => (await clients.readiness.get<ReadinessDto>("/readiness/me")).data,
};
