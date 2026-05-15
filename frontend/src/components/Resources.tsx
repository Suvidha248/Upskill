import { useState } from "react";
import { getResources } from "../api";

export default function Resources() {
  const [skill, setSkill] = useState("");
  const [resources, setResources] = useState<any[]>([]);

  async function load() {
    const data = await getResources(skill);
    setResources(data);
  }

  return (
    <div>
      <h2>Resources</h2>
      <input value={skill} onChange={e => setSkill(e.target.value)} placeholder="Skill" />
      <button onClick={load}>Search</button>

      <ul>
        {resources.map((r, i) => (
          <li key={i}>{r.title} — {r.url}</li>
        ))}
      </ul>
    </div>
  );
}
