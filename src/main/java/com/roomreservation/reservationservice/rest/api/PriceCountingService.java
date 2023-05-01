package com.roomreservation.reservationservice.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceCountingService {


    /**
     *  Return actual prices of rooms with all kind of reservations criteria
     *  in following structure:
     *  {
     *      room_type : String,
     *      services :
     *      {
     *          service_name : String
     *          price : Double
     *      }
     *  }
     *
     */
    @GetMapping
    public void getActualPrices(List<String> roomTypes, List<String> services) {

    }


    @GetMapping
    public Double recalculateToOtherCurrency(Double value, Currency actual, Currency expected) {
        return null;
    }


    private enum Currency {
        PLN,
        USD
    }

}
