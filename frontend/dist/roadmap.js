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
const backendBaseRoadmap = "http://localhost:8080";
function getGoalId() {
    const params = new URLSearchParams(window.location.search);
    return Number(params.get("goalId"));
}
const roadmapContainer = document.getElementById("roadmap-container");
const goalIdR = getGoalId();
function loadRoadmap() {
    return __awaiter(this, void 0, void 0, function* () {
        const res = yield fetch(`${backendBaseRoadmap}/api/roadmap/${goalIdR}`);
        const data = yield res.json();
        roadmapContainer.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
    });
}
loadRoadmap();
