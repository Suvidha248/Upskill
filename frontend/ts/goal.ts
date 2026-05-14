const backendBase = "http://localhost:8080";

const form = document.getElementById("goal-form") as HTMLFormElement;
const goalResult = document.getElementById("goal-result") as HTMLDivElement;

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const goalText = (document.getElementById("goalText") as HTMLInputElement).value;
  const timeline = (document.getElementById("timeline") as HTMLInputElement).value;

  // For MVP: hardcode userId = 1
  const createGoalRes = await fetch(`${backendBase}/api/goal`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userId: 1, goalText, timeline })
  });

  const goal = await createGoalRes.json();
  const goalId = goal.id;

  goalResult.innerHTML = `<p>Goal created with ID: ${goalId}</p><p>Generating roadmap...</p>`;

  const roadmapRes = await fetch(`${backendBase}/api/roadmap/generate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ goalId, userHistoryJson: "[]" })
  });

  const roadmap = await roadmapRes.json();
  goalResult.innerHTML += `<pre>${JSON.stringify(roadmap, null, 2)}</pre>
    <p><a href="dashboard.html?goalId=${goalId}">Go to Dashboard</a></p>`;
});
