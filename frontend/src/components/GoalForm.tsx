import { useState } from "react";
import { createGoal } from "../api";

export default function GoalForm({ onCreated }: { onCreated: (g: any) => void }) {
  const [goalText, setGoalText] = useState("");
  const [timeline, setTimeline] = useState("");

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    const goal = await createGoal(goalText, timeline);
    onCreated(goal);
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create Goal</h2>
      <input value={goalText} onChange={e => setGoalText(e.target.value)} placeholder="Goal" />
      <input value={timeline} onChange={e => setTimeline(e.target.value)} placeholder="Timeline" />
      <button type="submit">Save</button>
    </form>
  );
}
