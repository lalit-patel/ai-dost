import clients from "./client";
import { ExperienceDto, ProjectDto } from "./types";
export const projectClient = {
  getProjects: async () => (await clients.project.get<ProjectDto[]>("/project/me")).data,
  createProject: async (payload: ProjectDto) => (await clients.project.post<ProjectDto>("/project/me", payload)).data,
  getExperiences: async () => (await clients.experience.get<ExperienceDto[]>("/experience/me")).data,
  createExperience: async (payload: ExperienceDto) => (await clients.experience.post<ExperienceDto>("/experience/me", payload)).data,
};
