/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.route.service;

import com.service.route.model.Route;
import com.service.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kim Askebris
 */
/*
@Service
public class AvailableRoute {
            
    @Autowired
    RouteRepository routeRepository;

    public AvailableRoute() {
    }
    
    public boolean isAvailable(String depature, String destination) {
       boolean available = false;
       Route route = routeRepository.findByName(depature, destination);
       if (route != null) {
           available = true;
       }
       return available;
    }
    
}
*/