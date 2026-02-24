import axios from "axios";
import { authStorage } from "../auth/storage";

const useGateway = import.meta.env.VITE_USE_API_GATEWAY === "true";
const gatewayBaseUrl = import.meta.env.VITE_API_GATEWAY_URL;
const serviceUrls = {
  auth: import.meta.env.VITE_AUTH_SERVICE_URL,
  profile: import.meta.env.VITE_PROFILE_SERVICE_URL,
  dsa: import.meta.env.VITE_DSA_SERVICE_URL,
  mock: import.meta.env.VITE_MOCK_SERVICE_URL,
  project: import.meta.env.VITE_PROJECT_SERVICE_URL,
  experience: import.meta.env.VITE_EXPERIENCE_SERVICE_URL,
  readiness: import.meta.env.VITE_READINESS_SERVICE_URL,
  aiGateway: import.meta.env.VITE_AI_GATEWAY_SERVICE_URL,
};

const createClient = (service: keyof typeof serviceUrls) => axios.create({
  baseURL: useGateway ? gatewayBaseUrl : serviceUrls[service],
  headers: { "Content-Type": "application/json" },
});

const clients = {
  auth: createClient("auth"), profile: createClient("profile"), dsa: createClient("dsa"), mock: createClient("mock"),
  project: createClient("project"), experience: createClient("experience"), readiness: createClient("readiness"), aiGateway: createClient("aiGateway"),
};
Object.values(clients).forEach((c) => c.interceptors.request.use((config) => {
  const token = authStorage.getToken();
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
}));
export default clients;
