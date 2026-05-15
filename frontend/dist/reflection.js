"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const backendBaseRef = "http://localhost:8080";
function getGoalIdRef() {
    const params = new URLSearchParams(window.location.search);
    return Number(params.get("goalId"));
}
const goalIdRef = getGoalIdRef();
const refForm = document.getElementById("reflection-form");
const refResult = document.getElementById("reflection-result");
refForm.addEventListener("submit", (e) => __awaiter(void 0, void 0, void 0, function* () {
    e.preventDefault();
    const reflectionText = document.getElementById("reflectionText").value;
    const res = yield fetch(`${backendBaseRef}/api/reflection`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            goalId: goalIdRef,
            reflectionText,
            weakAreasJson: "[]"
        })
    });
    const data = yield res.json();
    refResult.innerHTML = `<h3>AI Feedback</h3><pre>${data.aiFeedback}</pre>`;
}));
