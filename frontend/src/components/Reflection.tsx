import { useState } from "react";
import { submitReflection } from "../api";

export default function Reflection({ goalId }: { goalId: number }) {
  const [text, setText] = useState("");
  const [msg, setMsg] = useState("");

  async function handleSubmit() {
    await submitReflection(goalId, text);
    setMsg("Reflection saved");
  }

  return (
    <div>
      <h2>Reflection</h2>
      <textarea value={text} onChange={e => setText(e.target.value)} />
      <button onClick={handleSubmit}>Submit</button>
      {msg && <p>{msg}</p>}
    </div>
  );
}
