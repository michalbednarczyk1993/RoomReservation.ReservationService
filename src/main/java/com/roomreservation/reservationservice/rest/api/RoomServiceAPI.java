package com.roomreservation.reservationservice.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RoomServiceAPI {

    /**
     * Return all room types available in hotel with selected criteria in JSON format.
     * In case when guestNumber is equal to null, then default value (1) is provided.
     * In case when daysNumber or startDate is equal to null, then system will return all possible values
     * that suit to guestNumber condition.
     * JSON structure:
     * {
     *     room_type.name: String,
     *     room_type.capacity: Integer,
     *     room_type.basePrice: Double,
     *     room_type.main_photo: href_to_data_storage
     * }
     *
     * @param guestsNum - number of guests that will be placed in one room;
     * @param daysNum - number of days that will take reservation;
     * @param startDate - first day of reservation (YYYY-MM-DD);
     * @return list of JSON objects that represent room types which satisfy conditions
     * Each element of the list should contain "illustrative photo", capacity and
     * base price of the room type.
     */
    @GetMapping("")
    public List<Object> getAllRoomTypes(
            @RequestBody(required = false) Integer guestsNum,
            @RequestBody(required = false) Integer daysNum,
            @RequestBody(required = false) Date startDate) {
        new ResponseEntity<>(new ArrayList<Object>(), HttpStatus.OK);
        return null;
    }

    /**
     * Return detail information about selected room type in JSON format.
     * In case of currency will be null, then default value (PLN) is provided.
     * In case of selectedServices will be null none of them will be counted in.
     * In case of selectedDate will be null, then first possible day will be chosen.
     * In case of numberOfDays will be null, then default value (1) will be provided.
     * In case of selectedMonth will be null, then month of first possible date will be chosen.
     * INFO: Service could return selected_date that differ from provided selectedDate
     * if those was not available for this room type.
     * JSON structure:
     * {
     *      room_type.name: room type name
     *      room_type.capacity: room type capacity
     *      room_type.description: room type description
     *      room_type.photos: list of hrefs to data storage with room type photo
     *      room_type.counted_price: total price of reservation
     *      room_type.selected_date: selected date of stay (YYYY-MM-DD)
     *      room_type.number_of_days: number of days chosen for stay
     *      room_type.free_dates: list of comma separated free dates (in selected_month and next) of selected rooms
     * }
     *
     * @param roomTypeId - id of selected room type
     * @param currency - currency in which user want to pay for reservation. Possible values: PLN, EUR, USD, CHF
     * @param selectedServices - comma separated extra paid services:
     *                      bb - breakfast included into price
     *                      di - dinner included into price
     *                      allIn - everything included into price
     *                      clean - everyday cleaning service included into price
     *                      sport - sport equipment rental included into price
     * @param selectedDate - selected date of stay (YYYY-MM-DD)
     * @param numberOfDays - number of days chosen for stay
     * @param selectedMonth - selected month to return "free_dates"
     * @return JSON object that represent detailed information about selected room type
     * which are list of photos related to this room type, description, counted price based on
     * selected services, list of available dates.
     */
    @GetMapping("/{roomTypeId}/details")
    public String getRoomTypeDetails(
            @PathVariable Long roomTypeId,
            @RequestBody(required = false) String currency,
            @RequestBody(required = false) String selectedServices,
            @RequestBody(required = false) Date selectedDate,
            @RequestBody(required = false) Integer numberOfDays,
            @RequestBody(required = false) Integer numberOfGuests,
            @RequestBody(required = false) Integer selectedMonth) {
        // API gateway should send a request to Price Counting Service with query about current price
        // Other way to do it, is to populate RoomType tables with this data based on cyclic requests
        return null;
    }

}
