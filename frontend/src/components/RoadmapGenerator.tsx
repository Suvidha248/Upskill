import { useState } from "react";
import { generateRoadmap, getRoadmap } from "../api";

export default function RoadmapGenerator({ goalId }: { goalId: number }) {
  const [history, setHistory] = useState("");
  const [roadmap, setRoadmap] = useState<any[]>([]);

  async function handleGenerate() {
    const data = await generateRoadmap(goalId, history);
    setRoadmap(data);
  }

  async function handleRefresh() {
    const data = await getRoadmap(goalId);
    setRoadmap(data);
  }

  return (
    <div>
      <h2>Roadmap</h2>

      <textarea
        value={history}
        onChange={e => setHistory(e.target.value)}
        placeholder="Your background"
      />

      <button onClick={handleGenerate}>Generate</button>
      <button onClick={handleRefresh}>Refresh</button>

      {roadmap.map(r => {
        let tasks = [];
        try { tasks = JSON.parse(r.tasksJson); } catch {}

        return (
          <div key={r.id}>
            <h3>Week {r.weekNumber}</h3>
            <pre>{JSON.stringify(tasks, null, 2)}</pre>
          </div>
        );
      })}
    </div>
  );
}
