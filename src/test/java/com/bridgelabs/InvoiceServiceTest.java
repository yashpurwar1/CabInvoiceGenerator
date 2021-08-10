package com.bridgelabs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

public class InvoiceServiceTest {
    private static final int NORMALRIDE = 1;
    private static final int PREMIUMRIDE = 2;
    InvoiceGenerator invoiceGenerator = null;

    /**
     * Purpose : To create object before any test case is called.
     *           Improves Redundancy of code
     */
    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    /**
     * Step 1.1
     * Purpose : Given distance and time,
     *           return total fare
     */
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        int option = NORMALRIDE;

        double fare = invoiceGenerator.calculateFare(distance, time, option);

        if(option == NORMALRIDE)
            Assert.assertEquals(25, fare, 0.0);
        else if(option == PREMIUMRIDE)
            Assert.assertEquals(40, fare, 0.0);
    }

    /**
     * Step 1.2
     * Purpose: Given distance and time,
     *          return minimum fare as MINIMUM_FARE
     */

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        int option = PREMIUMRIDE;

        double fare = invoiceGenerator.calculateFare(distance, time, option);

        if(option == NORMALRIDE)
            Assert.assertEquals(5, fare, 0.0);
        else if(option == PREMIUMRIDE)
            Assert.assertEquals(20, fare, 0.0);
    }

    /**
     * Step 2
     * Purpose : Given distance and time for multiple rides,
     *           return the aggregate total for all the rides
     */
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        int option = PREMIUMRIDE;

        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1) };

        double fare = invoiceGenerator.calculateTotalFare(rides, option);

        if(option == NORMALRIDE)
            Assert.assertEquals(30, fare, 0.0);
        else if(option == PREMIUMRIDE)
            Assert.assertEquals(60, fare, 0.0);
    }

    /**
     * Step 3
     * Purpose : Given distance and time,
     *           calculate the aggregate total for all the rides
     *           finally calculate the average fare per ride
     */
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        int option = PREMIUMRIDE;

        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1) };

        InvoiceSummary summary = invoiceGenerator.calculateFareSummary(rides, option);

        if(option == NORMALRIDE) {
            InvoiceSummary expectdInvoiceSummary = new InvoiceSummary(2, 30);
            Assert.assertEquals(expectdInvoiceSummary, summary);
        }
        else if(option == PREMIUMRIDE) {
            InvoiceSummary expectdInvoiceSummary = new InvoiceSummary(2, 60);
            Assert.assertEquals(expectdInvoiceSummary, summary);
        }
    }

    /**
     * Step 4
     * Purpose : Given userID,
     *           calculate the aggregate total for all the rides
     *           calculate the average fare per ride
     *           return the final invoice for that particular userID
     */
    @Test
    public void givenUserID_ShouldReturnInvoiceSummary() {
        Hashtable<Integer, Ride[]> htable = new Hashtable<>();

        int option = PREMIUMRIDE;

        int userID1 = 1;
        Ride[] ride1 = { new Ride(2.0, 5),
                new Ride(0.1, 1) };
        htable.put(userID1, ride1);

        int userID2 = 2;
        Ride[] ride2 = { new Ride(4.0, 10),
                new Ride(1, 1) };
        htable.put(userID2, ride2);

        int userID = 2;

        if(htable.containsKey(userID)) {
            InvoiceSummary summary = invoiceGenerator.calculateFareSummary(htable.get(userID), option);
            if(option == NORMALRIDE) {
                InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 61);
                Assert.assertEquals(expectedInvoiceSummary, summary);
            }
            else if(option == PREMIUMRIDE) {
                InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 100);
                Assert.assertEquals(expectedInvoiceSummary, summary);
            }
        }
    }
}