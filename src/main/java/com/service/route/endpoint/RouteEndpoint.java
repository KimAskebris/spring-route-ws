package com.service.route.endpoint;

import com.kimper.route.Route;
import com.kimper.route.RouteRequest;
import com.kimper.route.RouteResponse;
import com.service.route.service.PossibleRoutes;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author Kim Askebris
 */
@Endpoint
public class RouteEndpoint {

    private static final String NAMESPACE_URI = "http://www.kimper.com/route";
    private static List<ArrayList<String>> routes = new ArrayList();
    @Autowired
    PossibleRoutes possibleRoutes;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "routeRequest")
    @ResponsePayload
    public RouteResponse findRoutes(@RequestPayload RouteRequest request) {
        RouteResponse response = new RouteResponse();
        routes = possibleRoutes.find(request.getDeparture(), request.getDestination());
        for (ArrayList<String> route : routes) {
            Route responseRoute = new Route();
            responseRoute.setDeparture(route.get(0));
            responseRoute.setDestination(route.get(route.size() - 1));
            if (route.size() > 2) {
                for (int i = 1; i < route.size() - 1; i++) {
                    if (route != null && !route.isEmpty()) {
                        responseRoute.getStopover().add(route.get(i));
                    }
                }
            }
            response.getRoute().add(responseRoute);           
        }
        return response;
    }

}
