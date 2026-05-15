"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const backendBaseDash = "http://localhost:8080";
function getGoalIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return Number(params.get("goalId"));
}
const progressSection = document.getElementById("progress-section");
const roadmapLink = document.getElementById("roadmap-link");
const quizLink = document.getElementById("quiz-link");
const reflectionLink = document.getElementById("reflection-link");
const goalId = getGoalIdFromUrl();
roadmapLink.href = `roadmap.html?goalId=${goalId}`;
quizLink.href = `quiz.html?goalId=${goalId}`;
reflectionLink.href = `reflection.html?goalId=${goalId}`;
function loadProgress() {
    return __awaiter(this, void 0, void 0, function* () {
        const res = yield fetch(`${backendBaseDash}/api/progress/${goalId}`);
        if (!res.ok) {
            progressSection.innerHTML = "<p>No progress yet.</p>";
            return;
        }
        const progress = yield res.json();
        const completed = progress.completedTasks || 0;
        const total = progress.totalTasks || 1;
        const percent = Math.round((completed / total) * 100);
        progressSection.innerHTML = `
    <h2>Goal Progress</h2>
    <div class="progress-bar">
      <div class="progress-bar-inner" style="width:${percent}%"></div>
    </div>
    <p>${completed} / ${total} tasks completed (${percent}%)</p>
    <p>Weak areas: ${progress.weakAreasJson || "[]"}</p>
  `;
    });
}
loadProgress();
