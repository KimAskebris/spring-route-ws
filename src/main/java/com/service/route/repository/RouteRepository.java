package com.service.route.repository;

import com.service.route.model.Route;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kim Askebris
 */
@Component
public class RouteRepository {
    
    //private static final Map<Integer, Route> ROUTES = new HashMap<>();
    private static final Map<String, Integer> AIRPORTS = new HashMap<>();
    private ArrayList<Integer>[] CONNECTED_AIRPORTS;
    
    @PostConstruct
    public void setupData() {
        
        AIRPORTS.put("STO", 0);
        AIRPORTS.put("LON", 1);
        AIRPORTS.put("BER", 2);
        AIRPORTS.put("PAR", 3);
        AIRPORTS.put("NYC", 4);
        AIRPORTS.put("LA", 5);
        
        int numberOfAirports = AIRPORTS.size();
        
        CONNECTED_AIRPORTS = new ArrayList[numberOfAirports];

        for (int i = 0; i < numberOfAirports; i++) {
            CONNECTED_AIRPORTS[i] = new ArrayList<>();
        }
        
        //STO -> NYC
        CONNECTED_AIRPORTS[0].add(4);
        
        //STO -> LON -> NYC
        CONNECTED_AIRPORTS[0].add(1);
        CONNECTED_AIRPORTS[1].add(4);

        //STO -> BER -> PAR -> NY
        CONNECTED_AIRPORTS[0].add(2);
        CONNECTED_AIRPORTS[2].add(3);
        CONNECTED_AIRPORTS[3].add(4);
        
    }
    
    public Map<String, Integer> getAllAirports() {
        return AIRPORTS;
    }
    
    public ArrayList<Integer>[] getAllAirportConnections() {
        return CONNECTED_AIRPORTS;
    }
    
}
