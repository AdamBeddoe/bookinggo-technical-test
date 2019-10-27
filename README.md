# Adam Beddoe BookingGo Technical Test Submission

## Setup

```

mvn clean package

```

## Part 1

### Console application to print the search results for Dave's Taxis

`java -jar target/bookinggoapplication-0.0.1-SNAPSHOT.jar 10,10 20,20`

### Console application to filter by number of passengers

`java -jar target/bookinggoapplication-0.0.1-SNAPSHOT.jar 10,10 20,20 6`

## Part 2

To start the server:

`java -jar target/bookinggoapplication-0.0.1-SNAPSHOT.jar`

Sample request:

`/quotes?pickup=10,10&dropoff=20,20&passengers=6`

## Notes

Usually I'd write some unit tests with a mocking library such as Mockito and add some integration tests polling APIs, but I've ran out of time to do this unfortunately.
If you'd like to see details on how I'd do it, I've written a blog post about Spring testing on my website.


