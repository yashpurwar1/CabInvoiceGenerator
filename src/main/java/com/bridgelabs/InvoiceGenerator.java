package com.bridgelabs;

public class InvoiceGenerator {

    /**
     * Given : MINIMUM_COST_PER_KM = 10.0
     *         COST_PER_TIME = 1
     *         MINIMUM_FARE = 5
     */
    private static final double MINIMUM_COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;

    /**
     * Purpose : Given distance and time,
     *           Return total fare for the journey
     *
     * Condition : If minimum total fare is less than the MINIMUM_FARE, return MINIMUM_FARE
     *
     * @param distance
     * @param time
     */
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;

        return Math.max(totalFare, MINIMUM_FARE);
    }

    /**
     * Purpose : Given distance and time for multiple rides,
     *           Return aggregate total fare for all the journey
     *
     * @param rides
     * @return
     */
    public double calculateTotalFare(Ride[] rides) {
        double totalFare = 0.0;
        for(Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }
}