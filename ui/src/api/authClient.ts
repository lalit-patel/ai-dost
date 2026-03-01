import axios, { AxiosError } from "axios";
import clients from "./client";
import { AuthMeResponse, AuthResponse } from "./types";
import { authStorage } from "../auth/storage";

const directAuthBaseUrl = import.meta.env.VITE_AUTH_SERVICE_URL ?? "http://localhost:8081";
const directAuthClient = axios.create({
  baseURL: directAuthBaseUrl,
  headers: { "Content-Type": "application/json" },
});

directAuthClient.interceptors.request.use((config) => {
  const token = authStorage.getToken();
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

const shouldFallbackToDirectAuth = (err: unknown) => {
  if (!(err instanceof AxiosError)) return false;
  if (!err.response) return true;
  return [404, 502, 503, 504].includes(err.response.status);
};

export const authClient = {
  login: async (email: string, password: string) => {
    try {
      return (await clients.auth.post<AuthResponse>("/auth/login", { email, password })).data;
    } catch (err) {
      if (!shouldFallbackToDirectAuth(err)) throw err;
      return (await directAuthClient.post<AuthResponse>("/auth/login", { email, password })).data;
    }
  },

  register: async (displayName: string, email: string, password: string) => {
    try {
      return (await clients.auth.post<AuthResponse>("/auth/register", { displayName, email, password })).data;
    } catch (err) {
      if (!shouldFallbackToDirectAuth(err)) throw err;
      return (await directAuthClient.post<AuthResponse>("/auth/register", { displayName, email, password })).data;
    }
  },

  me: async () => {
    try {
      return (await clients.auth.get<AuthMeResponse>("/auth/me")).data;
    } catch (err) {
      if (!shouldFallbackToDirectAuth(err)) throw err;
      return (await directAuthClient.get<AuthMeResponse>("/auth/me")).data;
    }
  },
};
