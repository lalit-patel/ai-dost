import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { ProblemStatus } from "../api/types";
import { dsaClient } from "../api/dsaClient";

export const useDsaProblems = () => useQuery({ queryKey: ["dsa-problems"], queryFn: dsaClient.getMyProblems });

export const useUpdateProblemStatus = () => {
  const qc = useQueryClient();
  return useMutation({
    mutationFn: ({ problemId, status }: { problemId: number; status: ProblemStatus }) => dsaClient.updateStatus(problemId, status),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["dsa-problems"] }),
  });
};

export const useUpdateProblemNotes = () => {
  const qc = useQueryClient();
  return useMutation({
    mutationFn: ({ problemId, notes }: { problemId: number; notes: string }) => dsaClient.updateNotes(problemId, notes),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["dsa-problems"] }),
  });
};
