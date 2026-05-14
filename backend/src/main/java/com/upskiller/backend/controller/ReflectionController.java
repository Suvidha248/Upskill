package com.upskiller.backend.controller;

import com.upskiller.backend.model.Reflection;
import com.upskiller.backend.service.ReflectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reflection")
@CrossOrigin
public class ReflectionController {

    private final ReflectionService reflectionService;

    public ReflectionController(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    public static record ReflectionRequest(Long goalId, String reflectionText, String weakAreasJson) {}

    @PostMapping
    public Reflection create(@RequestBody ReflectionRequest req) {
        return reflectionService.createReflection(req.goalId(), req.reflectionText(), req.weakAreasJson());
    }
}
