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
const backendBaseQuiz = "http://localhost:8080";
function getGoalIdQ() {
    const params = new URLSearchParams(window.location.search);
    return Number(params.get("goalId"));
}
const goalIdQ = getGoalIdQ();
const generateBtn = document.getElementById("generate-quiz");
const quizContainer = document.getElementById("quiz-container");
generateBtn.addEventListener("click", () => __awaiter(void 0, void 0, void 0, function* () {
    const res = yield fetch(`${backendBaseQuiz}/api/quiz/generate`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ goalId: goalIdQ, skill: "Java Backend" })
    });
    const quiz = yield res.json();
    quizContainer.innerHTML = `<pre>${quiz.questionsJson}</pre>
    <p>After answering, imagine a score and submit via API or extend UI later.</p>`;
}));
