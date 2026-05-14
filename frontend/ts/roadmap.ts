const backendBaseRoadmap = "http://localhost:8080";

function getGoalId(): number {
  const params = new URLSearchParams(window.location.search);
  return Number(params.get("goalId"));
}

const roadmapContainer = document.getElementById("roadmap-container") as HTMLDivElement;
const goalIdR = getGoalId();

async function loadRoadmap() {
  const res = await fetch(`${backendBaseRoadmap}/api/roadmap/${goalIdR}`);
  const data = await res.json();
  roadmapContainer.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
}

loadRoadmap();
