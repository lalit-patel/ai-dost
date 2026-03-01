import axios from "axios";
import { authStorage } from "../auth/storage";

const defaults = {
  gateway: "http://localhost:8080",
  auth: "http://localhost:8081",
  profile: "http://localhost:8082",
  dsa: "http://localhost:8083",
  mock: "http://localhost:8084",
  project: "http://localhost:8085",
  experience: "http://localhost:8085",
  readiness: "http://localhost:8087",
  aiGateway: "http://localhost:8088",
} as const;

const useGateway = (import.meta.env.VITE_USE_API_GATEWAY ?? "true") === "true";
const gatewayBaseUrl = import.meta.env.VITE_API_GATEWAY_URL ?? defaults.gateway;
const serviceUrls = {
  auth: import.meta.env.VITE_AUTH_SERVICE_URL ?? defaults.auth,
  profile: import.meta.env.VITE_PROFILE_SERVICE_URL ?? defaults.profile,
  dsa: import.meta.env.VITE_DSA_SERVICE_URL ?? defaults.dsa,
  mock: import.meta.env.VITE_MOCK_SERVICE_URL ?? defaults.mock,
  project: import.meta.env.VITE_PROJECT_SERVICE_URL ?? defaults.project,
  experience: import.meta.env.VITE_EXPERIENCE_SERVICE_URL ?? defaults.experience,
  readiness: import.meta.env.VITE_READINESS_SERVICE_URL ?? defaults.readiness,
  aiGateway: import.meta.env.VITE_AI_GATEWAY_SERVICE_URL ?? defaults.aiGateway,
};

const createClient = (service: keyof typeof serviceUrls) =>
  axios.create({
    baseURL: useGateway ? gatewayBaseUrl : serviceUrls[service],
    headers: { "Content-Type": "application/json" },
  });

const clients = {
  auth: createClient("auth"),
  profile: createClient("profile"),
  dsa: createClient("dsa"),
  mock: createClient("mock"),
  project: createClient("project"),
  experience: createClient("experience"),
  readiness: createClient("readiness"),
  aiGateway: createClient("aiGateway"),
};

Object.values(clients).forEach((c) =>
  c.interceptors.request.use((config) => {
    const token = authStorage.getToken();
    if (token) config.headers.Authorization = `Bearer ${token}`;
    return config;
  }),
);

export default clients;
