package com.couriertracking.controller;

import com.couriertracking.controller.request.CourierLocationRequest;
import com.couriertracking.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier-tracking")
@RequiredArgsConstructor
public class CourierTrackingController {
    private final CourierTrackingService courierTrackingService;
    private static String OUTPUT = "Location logged successfully";

    @PostMapping("/log-location")
    public String logLocation(@RequestBody CourierLocationRequest courierLocationRequest) {
        courierTrackingService.logCourierLocation(courierLocationRequest);
        return OUTPUT;
    }

    @GetMapping("/calculate-total-distance")
    public double getTotalTravelDistance(@RequestParam Long courierId) {
        return courierTrackingService.getTotalTravelDistance(courierId);
    }
}