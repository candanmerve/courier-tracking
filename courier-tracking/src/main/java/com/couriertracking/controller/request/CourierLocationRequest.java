package com.couriertracking.controller.request;

import lombok.Data;

@Data
public class CourierLocationRequest {
    private Long courierId;
    private double latitude;
    private double longitude;
}
