const backendBaseQuiz = "http://localhost:8080";

function getGoalIdQ(): number {
  const params = new URLSearchParams(window.location.search);
  return Number(params.get("goalId"));
}

const goalIdQ = getGoalIdQ();
const generateBtn = document.getElementById("generate-quiz") as HTMLButtonElement;
const quizContainer = document.getElementById("quiz-container") as HTMLDivElement;

generateBtn.addEventListener("click", async () => {
  const res = await fetch(`${backendBaseQuiz}/api/quiz/generate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalId: goalIdQ, skill: "Java Backend" })
  });
  const quiz = await res.json();
  quizContainer.innerHTML = `<pre>${quiz.questionsJson}</pre>
    <p>After answering, imagine a score and submit via API or extend UI later.</p>`;
});
