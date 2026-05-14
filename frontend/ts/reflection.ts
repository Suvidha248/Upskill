const backendBaseRef = "http://localhost:8080";

function getGoalIdRef(): number {
  const params = new URLSearchParams(window.location.search);
  return Number(params.get("goalId"));
}

const goalIdRef = getGoalIdRef();
const refForm = document.getElementById("reflection-form") as HTMLFormElement;
const refResult = document.getElementById("reflection-result") as HTMLDivElement;

refForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const reflectionText = (document.getElementById("reflectionText") as HTMLTextAreaElement).value;

  const res = await fetch(`${backendBaseRef}/api/reflection`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      goalId: goalIdRef,
      reflectionText,
      weakAreasJson: "[]"
    })
  });

  const data = await res.json();
  refResult.innerHTML = `<h3>AI Feedback</h3><pre>${data.aiFeedback}</pre>`;
});
