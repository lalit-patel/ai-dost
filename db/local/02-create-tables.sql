-- Run this inside ai_dost database

CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  plan VARCHAR(20),
  display_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_account_roles (
  user_account_id BIGINT NOT NULL,
  roles VARCHAR(50),
  CONSTRAINT fk_user_account_roles_user FOREIGN KEY (user_account_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_profile (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  name VARCHAR(255),
  email VARCHAR(255),
  current_role VARCHAR(255),
  years_of_experience INTEGER,
  primary_tech_stack VARCHAR(255),
  target_role_goals VARCHAR(255),
  preferences VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS problems (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  link VARCHAR(1024),
  topic VARCHAR(255),
  difficulty VARCHAR(50),
  description VARCHAR(3000)
);

CREATE TABLE IF NOT EXISTS user_problem_states (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  problem_id BIGINT NOT NULL,
  status VARCHAR(32) NOT NULL,
  notes VARCHAR(3000),
  last_solved TIMESTAMP,
  CONSTRAINT uk_user_problem UNIQUE (user_id, problem_id)
);

CREATE TABLE IF NOT EXISTS mock_session (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  type VARCHAR(255),
  tech_stack VARCHAR(255),
  scheduled_time TIMESTAMP,
  status VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mock_question (
  id BIGSERIAL PRIMARY KEY,
  session_id BIGINT,
  question_text VARCHAR(2000),
  category VARCHAR(255),
  difficulty VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mock_answer (
  id BIGSERIAL PRIMARY KEY,
  session_id BIGINT,
  answer_text VARCHAR(4000),
  transcript VARCHAR(8000),
  ai_evaluation VARCHAR(3000),
  score INTEGER
);

CREATE TABLE IF NOT EXISTS project (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  title VARCHAR(255),
  description VARCHAR(2000),
  tech_stack VARCHAR(255),
  repo_links VARCHAR(255),
  status VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS experience (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  company VARCHAR(255),
  role VARCHAR(255),
  duration VARCHAR(255),
  responsibilities VARCHAR(3000),
  impact_bullets VARCHAR(3000)
);

CREATE TABLE IF NOT EXISTS resume (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  education VARCHAR(3000),
  skills VARCHAR(3000),
  achievements VARCHAR(3000),
  linked_project_ids VARCHAR(5000),
  linked_experience_ids VARCHAR(5000)
);
