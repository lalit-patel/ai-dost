import clients from "./client";
import { ProfileDto } from "./types";
export const profileClient = {
  getMe: async () => (await clients.profile.get<ProfileDto>("/profile/me")).data,
  updateMe: async (payload: ProfileDto) => (await clients.profile.put<ProfileDto>("/profile/me", payload)).data,
};
