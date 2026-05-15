import { useState } from "react";
import GoalForm from "./components/GoalForm";
import Progress from "./components/Progress";
import Quiz from "./components/Quiz";
import Reflection from "./components/Reflection";
import Resources from "./components/Resources";
import RoadmapGenerator from "./components/RoadmapGenerator";

function App() {
  const [goal, setGoal] = useState<any | null>(null);

  return (
    <div style={{ padding: 20 }}>
      <h1>UpSkiller</h1>

      {!goal && <GoalForm onCreated={setGoal} />}

      {goal && (
        <>
          <h2>Goal: {goal.goalText}</h2>

          <Progress goalId={goal.id} />
          <RoadmapGenerator goalId={goal.id} />
          <Quiz goalId={goal.id} />
          <Reflection goalId={goal.id} />
          <Resources />
        </>
      )}
    </div>
  );
}

export default App;
