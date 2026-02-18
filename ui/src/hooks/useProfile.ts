import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { profileClient } from "../api/profileClient";
export const useProfile = () => useQuery({ queryKey: ["profile"], queryFn: profileClient.getMe });
export const useUpdateProfile = () => { const qc = useQueryClient(); return useMutation({ mutationFn: profileClient.updateMe, onSuccess: () => qc.invalidateQueries({ queryKey: ["profile"] }) }); };
