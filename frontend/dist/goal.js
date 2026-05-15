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
const backendBase = "http://localhost:8080";
const form = document.getElementById("goal-form");
const goalResult = document.getElementById("goal-result");
form.addEventListener("submit", (e) => __awaiter(void 0, void 0, void 0, function* () {
    e.preventDefault();
    const goalText = document.getElementById("goalText").value;
    const timeline = document.getElementById("timeline").value;
    console.log("Frontend TS is running!");
    // For MVP: hardcode userId = 1
    const createGoalRes = yield fetch(`${backendBase}/api/goal`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId: 1, goalText, timeline })
    });
    const goal = yield createGoalRes.json();
    const goalId = goal.id;
    goalResult.innerHTML = `<p>Goal created with ID: ${goalId}</p><p>Generating roadmap...</p>`;
    const roadmapRes = yield fetch(`${backendBase}/api/roadmap/generate`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ goalId, userHistoryJson: "[]" })
    });
    const roadmap = yield roadmapRes.json();
    goalResult.innerHTML += `<pre>${JSON.stringify(roadmap, null, 2)}</pre>
    <p><a href="dashboard.html?goalId=${goalId}">Go to Dashboard</a></p>`;
}));
