import { useMutation, useQuery } from "@tanstack/react-query";
import { authClient } from "../api/authClient";
import { authStorage } from "../auth/storage";

export const useLogin = () =>
  useMutation({
    mutationFn: ({ email, password }: { email: string; password: string }) => authClient.login(email, password),
    onSuccess: authStorage.setSession,
  });

export const useRegister = () =>
  useMutation({
    mutationFn: ({ displayName, email, password }: { displayName: string; email: string; password: string }) =>
      authClient.register(displayName, email, password),
    onSuccess: authStorage.setSession,
  });

export const useAuthMe = () => useQuery({ queryKey: ["auth-me"], queryFn: authClient.me, enabled: Boolean(authStorage.getToken()) });
