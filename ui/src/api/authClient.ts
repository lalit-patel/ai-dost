import clients from "./client";
import { AuthMeResponse, AuthResponse } from "./types";

export const authClient = {
  login: async (email: string, password: string) => (await clients.auth.post<AuthResponse>("/auth/login", { email, password })).data,
  register: async (name: string, email: string, password: string) => (await clients.auth.post<AuthResponse>("/auth/register", { name, email, password })).data,
  me: async () => (await clients.auth.get<AuthMeResponse>("/auth/me")).data,
};
