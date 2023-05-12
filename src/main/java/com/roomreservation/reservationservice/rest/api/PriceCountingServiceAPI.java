package com.roomreservation.reservationservice.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceCountingServiceAPI {


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
     * @param roomTypes - list of room types
     * @param services - list of all possible combinations of available services
     */
    @GetMapping
    public void getActualPrices(List<String> roomTypes, List<String> services) {
        // Oblicza ceny(roomTypes, services);
        // w jakiś magiczny sposób muszę ustawić cenę bazową i opłaty za serwisy
        // docelowo oczywiście powinny być od tego endpointy
    }


    /**
     * Return recalculated value in expected currency.
     * Exchange course is taken from NBP API once a day by automated scheduler and stored with information about date.
     * In case it will be null, then currency should be recalculated and returned base ond most recent values
     * additionally an extra request will be sent to external API.
     * ... internal logic described below:
     * In case of no response or error message (from NBP API), request should be repeated 5 times.
     * If fifth response from NBP will be failed, then HTTP 500 should be returned here with specified information.
     *
     * @param value - amount of money in actual currency
     * @param actual - currency in which money are already presented
     * @param expected - currency to which money should be recalculated
     * @return amount of money in expected currency
     */
    @GetMapping
    public Double recalculateToOtherCurrency(Double value, Currency actual, Currency expected) {
        return null;
    }

    private enum Currency {
        PLN,
        USD
    }

}
