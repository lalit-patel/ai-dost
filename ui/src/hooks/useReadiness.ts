import { useQuery } from "@tanstack/react-query";
import { readinessClient } from "../api/readinessClient";
export const useReadiness = () => useQuery({ queryKey: ["readiness"], queryFn: readinessClient.getMe });
