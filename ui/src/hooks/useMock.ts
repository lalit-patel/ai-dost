import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { mockClient } from "../api/mockClient";
export const useMockSessions = () => useQuery({ queryKey: ["mock-sessions"], queryFn: mockClient.getSessions });
export const useCreateMockSession = () => { const qc = useQueryClient(); return useMutation({ mutationFn: (type: string) => mockClient.createSession(type), onSuccess: () => qc.invalidateQueries({ queryKey: ["mock-sessions"] }) }); };
