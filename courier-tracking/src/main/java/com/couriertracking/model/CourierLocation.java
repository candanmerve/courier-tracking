package com.couriertracking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURIER_LOCATION")
public class CourierLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courierId;
    private double latitude;
    private double longitude;
    private LocalDateTime createTime;
}