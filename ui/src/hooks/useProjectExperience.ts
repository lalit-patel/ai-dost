import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { projectClient } from "../api/projectClient";
export const useProjects = () => useQuery({ queryKey: ["projects"], queryFn: projectClient.getProjects });
export const useCreateProject = () => { const qc = useQueryClient(); return useMutation({ mutationFn: projectClient.createProject, onSuccess: () => qc.invalidateQueries({ queryKey: ["projects"] }) }); };
export const useExperiences = () => useQuery({ queryKey: ["experience"], queryFn: projectClient.getExperiences });
export const useCreateExperience = () => { const qc = useQueryClient(); return useMutation({ mutationFn: projectClient.createExperience, onSuccess: () => qc.invalidateQueries({ queryKey: ["experience"] }) }); };
