package com.adambeddoe.bookinggoapplication.controllers;

import com.adambeddoe.bookinggoapplication.pojos.Location;
import com.adambeddoe.bookinggoapplication.services.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller for the REST API for quotes
 */
@RestController
public class RestApiController {

    @Autowired
    private Api api;

    /**
     * Returns JSON of the cheapest quotes for the number of passengers, if specified
     * @param pickup The pickup location, required
     * @param dropOff The drop off location, required
     * @param passengers The minimum number of seats, optional
     * @return JSON of the cheapest quotes for the number of passengers, if specified
     */
    @GetMapping("quotes")
    public ResponseEntity getQuotes(@RequestParam("pickup") String pickup,
                                    @RequestParam("dropoff") String dropOff,
                                    @RequestParam("passengers") Optional<Integer> passengers) {

        String[] pickupParts = pickup.split(",");
        String[] dropOffParts = dropOff.split(",");

        Location pickupLocation = Location.locationFromString(pickupParts[0], pickupParts[1]);
        Location dropOffLocation = Location.locationFromString(dropOffParts[0], dropOffParts[1]);

        if (passengers.isPresent()) {
            return ResponseEntity.ok(api.getStock(pickupLocation, dropOffLocation, passengers.get()));
        } else {
            return ResponseEntity.ok(api.getStock(pickupLocation, dropOffLocation));
        }
    }

}
