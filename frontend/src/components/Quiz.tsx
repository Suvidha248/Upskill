import { useState } from "react";
import { generateQuiz, submitQuiz } from "../api";

export default function Quiz({ goalId }: { goalId: number }) {
  const [quiz, setQuiz] = useState<any | null>(null);
  const [result, setResult] = useState<string | null>(null);

  async function loadQuiz() {
    const q = await generateQuiz(goalId);
    setQuiz(q);
    setResult(null);
  }

  async function submit(answer: string) {
    const res = await submitQuiz(quiz.id, answer);
    setResult(res.correct ? "Correct!" : "Incorrect");
  }

  return (
    <div>
      <h2>Quiz</h2>

      {!quiz && <button onClick={loadQuiz}>Generate Quiz</button>}

      {quiz && (
        <div>
          <p>{quiz.question}</p>
          {quiz.options.map((opt: string) => (
            <button key={opt} onClick={() => submit(opt)}>{opt}</button>
          ))}
        </div>
      )}

      {result && <p>{result}</p>}
    </div>
  );
}
