package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        // We need to get a real duration between the In and Out moment
        // with getTime() this will be in millisecondes.
        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        //TODO: Some tests are failing here. Need to check if this logic is correct
        // We need to convert the time from milliseconds to hours
        // We need to add the f for the float results (with decimals)
        double duration = ((outHour - inHour)/1000)/3600f;

        // *** Here we check if the duration is less than 0.5 so less than 30 minutes.
		if (duration < 0.5) {
			duration = 0.0;
		}

        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                //ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
            double normalPrice = duration * Fare.CAR_RATE_PER_HOUR;
            ticket.setPrice(normalPrice);
                break;
            }
            case BIKE: {
                //ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
            double normalPrice = duration * Fare.BIKE_RATE_PER_HOUR;
			ticket.setPrice(normalPrice);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}