package com.couriertracking.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceStrategyService {

    double calculateDistance(double latitudeOne, double longitudeOne, double latitudeTwo, double longitudeTwo) {
        final int Radius = 6371; // Radius of the Earth in km
        double latitude = Math.toRadians(latitudeTwo - latitudeOne);
        double longitude = Math.toRadians(longitudeTwo - longitudeOne);
        double formula = Math.sin(latitude / 2) * Math.sin(latitude / 2) +
                   Math.cos(Math.toRadians(latitudeOne)) * Math.cos(Math.toRadians(latitudeTwo)) *
                   Math.sin(longitude / 2) * Math.sin(longitude / 2);
        double calculate = 2 * Math.atan2(Math.sqrt(formula), Math.sqrt(1 - formula));
        return Radius * calculate;
    }
}
