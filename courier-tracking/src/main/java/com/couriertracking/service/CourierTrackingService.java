package com.couriertracking.service;

import com.couriertracking.config.CacheConfig;
import com.couriertracking.controller.request.CourierLocationRequest;
import com.couriertracking.model.CourierLocation;
import com.couriertracking.model.Store;
import com.couriertracking.repository.CourierLocationRepository;
import com.couriertracking.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierTrackingService {
    private final CourierLocationRepository courierLocationRepository;
    private final StoreRepository storeRepository;
    private final DistanceStrategyService distanceStrategyService;
    private final CacheManager cacheManager;

    private List<Store> getStoreList(){
        Cache store = cacheManager.getCache("store");
        List<Store> allStores = store.get("allStore", List.class);
        if (allStores == null || allStores.isEmpty()) {
            List<Store> all = storeRepository.findAll();
            store.put("allStore", all);
        }
        return allStores;
    }

    private boolean isCourierExistsInCache(Long id){
        Cache courierLocationCache = cacheManager.getCache("courier");
        CourierLocation courierLocation = courierLocationCache.get(id, CourierLocation.class);
        return courierLocation != null;
    }
    public void logCourierLocation(CourierLocationRequest courierLocationRequest) {
        CourierLocation location = new CourierLocation(null, courierLocationRequest.getCourierId(), courierLocationRequest.getLatitude(), courierLocationRequest.getLongitude(), LocalDateTime.now());
        courierLocationRepository.save(location);
        List<Store> storeList = getStoreList();

        for (Store store : storeList) {
            double distance = distanceStrategyService.calculateDistance(courierLocationRequest.getLatitude(), courierLocationRequest.getLongitude(), store.getLatitude(), store.getLongitude());

            if (distance <= 0.1) { // 100 metre
                // Aynı mağazaya son 1 dakika içinde giriş var mı kontrolü
                if(!isCourierExistsInCache(courierLocationRequest.getCourierId())){
                    System.out.println("Kurye mağaza yakınına girdi: storeId=" + store.getId());
                }
                Cache courierCache = cacheManager.getCache("courier");
                courierCache.put(courierLocationRequest.getCourierId(), location);
                return;
            }
        }
    }

    public double getTotalTravelDistance(Long courierId){
        List<CourierLocation> courierLocations = courierLocationRepository.findByCourierIdOrderByCreateTimeDesc(courierId);
        double total = 0;
        for (int i = 1; i < courierLocations.size() ; i++) {
            double distance = distanceStrategyService.calculateDistance(courierLocations.get(i - 1).getLatitude(), courierLocations.get(i - 1).getLongitude(),
                    courierLocations.get(i).getLatitude(), courierLocations.get(i).getLongitude());
            total += distance;
        }
        return total;
    }
}

