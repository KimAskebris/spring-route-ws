/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.route.service;

import com.service.route.repository.RouteRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kim
 */
@Service
public class PossibleRoutes {
    
    // no. of airports in graph
    private Map<String, Integer> airports = new HashMap<>();
    //contains all airports flying by, including departure and destination
    private ArrayList<Integer>[] connectedAirports;
    //a list with all the airports
    private int numberOfAirports;
    //all routes
    private final List<ArrayList<Integer>> allFlightRoutes = new ArrayList<>();
    // a list containing all the airports connected to a airport
    private final List<ArrayList<String>> allRoutesInAirportCode = new ArrayList<>();
    
    @Autowired
    RouteRepository routeRepository;
    
    public PossibleRoutes() {
    
    }
    
    public List<ArrayList<String>> find(String from, String to) {
        airports = routeRepository.getAllAirports();
        connectedAirports = routeRepository.getAllAirportConnections();
        numberOfAirports = airports.size();

        printAllFlightRoutes(airports.get(from), airports.get(to));
        sortByShortestFlightRoute();
        convertRoutesToAirportCode();

        return allRoutesInAirportCode;
    }
    
    private void sortByShortestFlightRoute() {
        allFlightRoutes.sort((Comparator<List<Integer>>) (a, b) -> {
            Integer sizeA = a.size();
            Integer sizeB = b.size();
            return sizeA.compareTo(sizeB);
        });
    }
    
     private void convertRoutesToAirportCode() {
         allFlightRoutes.stream().map((route) -> {
             ArrayList<String> routeAirportCode = new ArrayList<>();
             route.forEach((aRoute) -> {
                 routeAirportCode.add(getKeyCode(aRoute));
             });
            return routeAirportCode;
        }).forEachOrdered((routeAirportCode) -> {
            allRoutesInAirportCode.add(routeAirportCode);
        });
    }
     
     
    private String getKeyCode(Integer value) {
        String key = null;
        for(Map.Entry entry: airports.entrySet()){
            if(value.equals(entry.getValue())){
                key = (String) entry.getKey();
                break;
            }
        }
        return key;
    }
    
    // Prints all paths from
    // 'from' to 'to'
    private void printAllFlightRoutes(int from, int to) {
        boolean[] isVisited = new boolean[numberOfAirports];
        ArrayList<Integer> flightRoute = new ArrayList<>();

        //add source to path[]
        flightRoute.add(from);

        //Call recursive utility
        printAllFlightRoutesUtil(from, to, isVisited, flightRoute);
    }
    
    // A recursive function to print
    // all paths from 'currentAirport' to 'dest'.
    // isVisited[] keeps track of
    // vertices in current path.
    // currentFlightRoute<> stores actual
    // vertices in the current path
    private void printAllFlightRoutesUtil(Integer currentAirport, Integer dest,
                                   boolean[] isVisited,
                                   ArrayList<Integer> currentFlightRoute) {

        // Mark the current airport
        isVisited[currentAirport] = true;

        if (currentAirport.equals(dest)) {
            allFlightRoutes.add(new ArrayList<>(currentFlightRoute));
        }
        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer airport : connectedAirports[currentAirport]) {
            if (!isVisited[airport]) {
                // store current node
                // in path[]
                currentFlightRoute.add(airport);
                printAllFlightRoutesUtil(airport, dest, isVisited, currentFlightRoute);

                // remove current airport
                // in route[]
                currentFlightRoute.remove(airport);
            }
        }
        // unmark the current airport
        isVisited[currentAirport] = false;
    }
    
}
