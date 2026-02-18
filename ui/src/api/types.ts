export type Role = "CANDIDATE" | "MENTOR" | "RECRUITER" | "ADMIN";
export type Plan = "FREE" | "PRO";

export interface AuthResponse { userId: number; token: string; roles: Role[]; plan: Plan; }
export interface AuthMeResponse { userId: number; name: string; email: string; roles: Role[]; plan: Plan; }

export type ProblemStatus = "TODO" | "IN_PROGRESS" | "DONE" | "REVISE";
export interface DsaProblemItem { id: number; title: string; link: string; topic: string; difficulty: string; status: ProblemStatus; notes?: string; hasNotes?: boolean; }
export interface ProfileDto { name: string; email: string; currentRole?: string; yearsOfExperience?: number; primaryTechStack?: string; targetRoleGoals?: string; goals?: string; }
export interface MockSession { id: number; type: string; status: string; createdAt?: string; }
export interface ProjectDto { id?: number; title: string; description: string; techStack: string; }
export interface ExperienceDto { id?: number; company: string; role: string; duration: string; responsibilities: string; }
export interface ReadinessDto { overall: number; dsa: number; tech: number; projects: number; communication: number; }
