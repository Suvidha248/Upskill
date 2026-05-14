const backendBaseDash = "http://localhost:8080";

function getGoalIdFromUrl(): number {
  const params = new URLSearchParams(window.location.search);
  return Number(params.get("goalId"));
}

const progressSection = document.getElementById("progress-section") as HTMLDivElement;
const roadmapLink = document.getElementById("roadmap-link") as HTMLAnchorElement;
const quizLink = document.getElementById("quiz-link") as HTMLAnchorElement;
const reflectionLink = document.getElementById("reflection-link") as HTMLAnchorElement;

const goalId = getGoalIdFromUrl();
roadmapLink.href = `roadmap.html?goalId=${goalId}`;
quizLink.href = `quiz.html?goalId=${goalId}`;
reflectionLink.href = `reflection.html?goalId=${goalId}`;

async function loadProgress() {
  const res = await fetch(`${backendBaseDash}/api/progress/${goalId}`);
  if (!res.ok) {
    progressSection.innerHTML = "<p>No progress yet.</p>";
    return;
  }
  const progress = await res.json();
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
}

loadProgress();
