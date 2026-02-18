import clients from "./client";

export const aiGatewayClient = {
  explainProblem: async (problemContext: string) =>
    (await clients.aiGateway.post<string>("/ai-gateway/explain-dsa", { problemContext, tier: "FREE" })).data,
  discussApproach: async (problemContext: string, userContext: string) => {
    try {
      return (await clients.aiGateway.post<string>("/ai-gateway/discuss-dsa", { problemContext, userContext, tier: "FREE" })).data;
    } catch {
      return (await clients.aiGateway.post<string>("/ai-gateway/evaluate-mock-answer", { question: problemContext, answer: userContext, tier: "FREE" })).data;
    }
  },
};
