import clients from "./client";
import { MockSession } from "./types";
export const mockClient = {
  getSessions: async () => (await clients.mock.get<MockSession[]>("/mock/me/sessions")).data,
  createSession: async (type: string) => (await clients.mock.post<MockSession>("/mock/me/sessions", { type })).data,
};
