package com.service.route.model;

import java.io.Serializable;

/**
 *
 * @author Kim Askebris
 */

public class Route implements Serializable {
    
    private Integer id;
    private String departure;
    private String destination;

    public Route() {
    }

    public Route(String depature, String destination) {
        this.departure = depature;
        this.destination = destination;
    }

    public String getDepature() {
        return departure;
    }

    public void setDepature(String depature) {
        this.departure = depature;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Route{" + "depature=" + departure + ", destination=" + destination + '}';
    }
    
}

   
