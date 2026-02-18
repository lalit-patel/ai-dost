import { Navigate, Route, Routes } from "react-router-dom";
import { AuthGuard } from "../components/AuthGuard";
import { Layout } from "../components/Layout";
import { DashboardPage } from "../pages/DashboardPage";
import { DsaPage } from "../pages/DsaPage";
import { DsaProblemDetailPage } from "../pages/DsaProblemDetailPage";
import { ExperiencePage } from "../pages/ExperiencePage";
import { LoginPage } from "../pages/LoginPage";
import { MockPage } from "../pages/MockPage";
import { ProfilePage } from "../pages/ProfilePage";
import { ProjectsPage } from "../pages/ProjectsPage";
import { RegisterPage } from "../pages/RegisterPage";

export function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route element={<AuthGuard />}>
        <Route element={<Layout />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/dsa" element={<DsaPage />} />
          <Route path="/dsa/:problemId" element={<DsaProblemDetailPage />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/mock" element={<MockPage />} />
          <Route path="/projects" element={<ProjectsPage />} />
          <Route path="/experience" element={<ExperiencePage />} />
        </Route>
      </Route>
      <Route path="*" element={<Navigate to="/dashboard" replace />} />
    </Routes>
  );
}
