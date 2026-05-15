import { useEffect, useState } from "react";
import { getProgress } from "../api";

export default function Progress({ goalId }: { goalId: number }) {
  const [progress, setProgress] = useState<any | null>(null);

  useEffect(() => {
    getProgress(goalId).then(setProgress);
  }, [goalId]);

  return (
    <div>
      <h2>Progress</h2>
      {progress && <p>{progress.completionPercent}% completed</p>}
    </div>
  );
}
