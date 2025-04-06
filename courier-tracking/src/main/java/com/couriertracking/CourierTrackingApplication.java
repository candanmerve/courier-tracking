package com.couriertracking;

import com.couriertracking.model.Store;
import com.couriertracking.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableCaching
public class CourierTrackingApplication {

    private final StoreRepository storeRepository;

    public CourierTrackingApplication(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingApplication.class, args);
    }

    @PostConstruct
    public void insertStoreRecords() {
        List<Store> stores = new ArrayList<>();
        Store atasehir = new Store(1L, "Ataşehir MMM Migros", 40.9923307, 29.1244229);
        Store novada = new Store(2L, "Novada MMM Migros", 40.986106, 29.1161293);
        Store beylikdüzü = new Store(3L, "Beylikdüzü 5M Migros", 41.0066851, 28.6552262);
        Store ortakoy = new Store(4L, "Ortaköy MMM Migros", 41.055783, 29.0210292);
        Store caddebostan = new Store(5L, "Caddebostan MMM Migros", 40.9632463, 29.0630908);
        stores.add(atasehir);
        stores.add(novada);
        stores.add(beylikdüzü);
        stores.add(ortakoy);
        stores.add(caddebostan);
        storeRepository.saveAll(stores);
    }
}