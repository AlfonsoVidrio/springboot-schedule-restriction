package com.alfonsovidrio.springboot.calendar.interceptor.springboot_schedule.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest req) {

        Map<String, Object> data = new HashMap<>();
        data.put("title", "Welcome to the schedule app");
        data.put("date", new Date());
        data.put("message", req.getAttribute("message"));
        return ResponseEntity.ok(data);
    }
}
