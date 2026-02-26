import { AxiosError } from "axios";
import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useLogin } from "../hooks/useAuth";

export function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const { mutateAsync, isPending } = useLogin();
  const nav = useNavigate();

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError(null);
    try {
      await mutateAsync({ email, password });
      nav("/dashboard");
    } catch (err) {
      const message =
        err instanceof AxiosError
          ? err.response?.data?.message ?? "Login failed. Please verify email/password and backend availability."
          : "Login failed. Please try again.";
      setError(message);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-20 bg-white p-6 rounded shadow">
      <h1 className="text-xl font-semibold mb-4">Login</h1>
      <form onSubmit={onSubmit} className="space-y-3">
        <input className="w-full border p-2" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
        <input type="password" className="w-full border p-2" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
        {error && <p className="text-sm text-red-600">{error}</p>}
        <button disabled={isPending} className="w-full bg-slate-900 text-white py-2 rounded">{isPending ? "Logging in..." : "Login"}</button>
      </form>
      <p className="text-sm mt-3">
        New user? <Link className="underline" to="/register">Register</Link>
      </p>
    </div>
  );
}
