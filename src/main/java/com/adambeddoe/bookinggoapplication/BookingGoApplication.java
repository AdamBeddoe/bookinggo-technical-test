package com.adambeddoe.bookinggoapplication;

import com.adambeddoe.bookinggoapplication.pojos.Location;
import com.adambeddoe.bookinggoapplication.pojos.Quote;
import com.adambeddoe.bookinggoapplication.services.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookingGoApplication {

	public static void main(String[] args) {
		if (args.length == 0) {
            SpringApplication.run(BookingGoApplication.class, args);

		} else if (args.length >= 2) {
		    Api api = new Api();

		    String[] numericalArguments = args[0].split(",");

		    try {
				Location pickup = Location.locationFromString(numericalArguments[0], numericalArguments[1]);
                Location dropOff = Location.locationFromString(numericalArguments[0], numericalArguments[1]);

                List<Quote> quotes;
                if (args.length == 2) {
                    quotes = api.getStock(pickup, dropOff);
                } else {
                    quotes = api.getStock(pickup, dropOff, Integer.valueOf(args[2]));
                }

				for (Quote quote : quotes) {
                    System.out.println(quote);
                }

            } catch (NumberFormatException e) {
                System.out.println("Latitude and longitude not input correctly.");
            }
		} else {
            System.out.println("Invalid program arguments, please provide pickup and drop off coordinates.");
        }
	}

}
