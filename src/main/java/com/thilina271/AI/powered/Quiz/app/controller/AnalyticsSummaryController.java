package com.thilina271.AI.powered.Quiz.app.controller;

import com.thilina271.AI.powered.Quiz.app.dto.AnalyticsSummaryDTO;
import com.thilina271.AI.powered.Quiz.app.service.AnalyticsSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/analytics")
@RequiredArgsConstructor
@RestController
public class AnalyticsSummaryController {
    private final AnalyticsSummaryService analyticsSummaryService;

    @GetMapping("summary")
    public ResponseEntity<AnalyticsSummaryDTO> getSummary(){
        return analyticsSummaryService.getSummary();
    }
}
