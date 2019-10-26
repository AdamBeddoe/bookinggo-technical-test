package com.adambeddoe.bookinggoapplication.services;

import com.adambeddoe.bookinggoapplication.pojos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Handles all requests to APIs
 */
@Service
public class Api {

    private final String url = "https://techtest.rideways.com/";
    private final int defaultPassengers = 0;
    private final List<String> serviceProviders = Arrays.asList("dave", "eric", "jeff");

    private RestTemplate restTemplate = new RestTemplate();

    public Api() {
    }


    /**
     * Gets the lowest prices quotes for all possible vehicle types
     * @param pickup The pickup location
     * @param dropOff The drop off location
     * @return A list of the lowest prices quotes for all possible vehicle types
     */
    public List<Quote> getStock(Location pickup, Location dropOff) {
        return getStock(pickup, dropOff, defaultPassengers);
    }

    /**
     * Gets the lowest prices quotes for vehicles with seats greater than the number of passengers
     * @param pickup The pickup location
     * @param dropOff The drop off location
     * @param numberOfPassengers The minimum number of seats
     * @return A list of the lowest prices quotes for all possible vehicle types
     */
    public List<Quote> getStock(Location pickup, Location dropOff, int numberOfPassengers) {
        List<Quote> quotes = getQuotes(pickup, dropOff);

        List<Quote> validQuotes = quotes.stream()
                .filter(q -> maximumPassengers(q.getCarType()) >= numberOfPassengers)
                .collect(Collectors.toList());

        Map<CarType, Quote> bestQuotes = new HashMap<>();
        validQuotes.forEach(q -> bestQuotes.putIfAbsent(q.getCarType(), q));
        validQuotes.forEach(q -> {
            if (q.getPrice() < bestQuotes.get(q.getCarType()).getPrice()) {
                bestQuotes.put(q.getCarType(), q);
            }
        });

        return new ArrayList<>(bestQuotes.values());
    }

    // Gets all quotes from all APIs
    private List<Quote> getQuotes(Location pickup, Location dropOff) {
        List<Quote> quotes = new ArrayList<>();

        for (String provider : this.serviceProviders) {

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.url)
                    .path("/" + provider)
                    .queryParam("pickup", pickup.printPair())
                    .queryParam("dropoff", dropOff.printPair());

            try {
                ResponseEntity<Response> response = restTemplate.getForEntity(builder.build().encode().toUri(), Response.class);

                List<Option> options = response.getBody().getOptions();
                List<Quote> supplierQuotes = options.stream()
                        .map(o -> new Quote(provider, o.getCarType(), o.getPrice()))
                        .collect(Collectors.toList());

                quotes.addAll(supplierQuotes);
            } catch (HttpStatusCodeException | NullPointerException | ResourceAccessException e) {
                System.out.println(provider + " API request failed!");
            }
        }

        return quotes;
    }

    // The maximum passengers for any vehicle type
    private int maximumPassengers(CarType carType) {
        switch (carType) {
            case STANDARD:
                return 4;
            case EXECUTIVE:
                return 4;
            case LUXURY:
                return 4;
            case PEOPLE_CARRIER:
                return 6;
            case LUXURY_PEOPLE_CARRIER:
                return 6;
            case MINIBUS:
                return 16;
            default:
                return -1;
        }
    }
}
