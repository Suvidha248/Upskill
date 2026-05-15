const API = "/api";

export interface Goal {
  id: number;
  goalText: string;
  timeline: string;
}

export interface Roadmap {
  id: number;
  weekNumber: number;
  tasksJson: string;
  resourcesJson: string;
}

export interface Quiz {
  id: number;
  question: string;
  options: string[];
}

export interface Progress {
  id: number;
  goalId: number;
  completionPercent: number;
}

export async function createGoal(goalText: string, timeline: string) {
  return fetch(`${API}/goal`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalText, timeline })
  }).then(r => r.json());
}

export async function getGoal(goalId: number) {
  return fetch(`${API}/goal/${goalId}`).then(r => r.json());
}

export async function getProgress(goalId: number) {
  return fetch(`${API}/progress/${goalId}`).then(r => r.json());
}

export async function generateQuiz(goalId: number) {
  return fetch(`${API}/quiz/generate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalId })
  }).then(r => r.json());
}

export async function submitQuiz(quizId: number, answer: string) {
  return fetch(`${API}/quiz/${quizId}/submit`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ answer })
  }).then(r => r.json());
}

export async function submitReflection(goalId: number, text: string) {
  return fetch(`${API}/reflection`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalId, text })
  }).then(r => r.json());
}

export async function getResources(skill: string) {
  return fetch(`${API}/resources/${skill}`).then(r => r.json());
}

export async function generateRoadmap(goalId: number, userHistoryJson: string) {
  return fetch(`${API}/roadmap/generate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalId, userHistoryJson })
  }).then(r => r.json());
}

export async function getRoadmap(goalId: number) {
  return fetch(`${API}/roadmap/${goalId}`).then(r => r.json());
}
