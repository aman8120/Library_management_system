package com.example.lib_mng_sys.controller;

import com.example.lib_mng_sys.dto.response.DashboardSummaryResponse;
import com.example.lib_mng_sys.service.DashboardService;
import com.example.lib_mng_sys.util.ResponseBuilder;
import com.example.lib_mng_sys.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000", // ✅ Your frontend
        allowCredentials = "true"
)
@RequestMapping("/api/v1/dashboard")
@AllArgsConstructor
public class DashBoardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<ResponseStructure<DashboardSummaryResponse>> getDashboardSummary() {
        DashboardSummaryResponse summary = dashboardService.getDashboardSummary();
        return ResponseBuilder.ok("Dashboard Summary Fetched", summary);
    }
}
