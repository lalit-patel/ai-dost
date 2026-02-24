import { AuthResponse } from "../api/types";
const TOKEN_KEY = "ai_dost_token";
const USER_KEY = "ai_dost_user";
// TODO: migrate to httpOnly cookie-based auth when backend gateway supports it.
export const authStorage = {
  getToken: () => localStorage.getItem(TOKEN_KEY),
  setSession: (session: AuthResponse) => {
    localStorage.setItem(TOKEN_KEY, session.token);
    localStorage.setItem(USER_KEY, JSON.stringify({ userId: session.userId, roles: session.roles, plan: session.plan }));
  },
  getUserMeta: () => { const raw = localStorage.getItem(USER_KEY); return raw ? JSON.parse(raw) : null; },
  clear: () => { localStorage.removeItem(TOKEN_KEY); localStorage.removeItem(USER_KEY); }
};
