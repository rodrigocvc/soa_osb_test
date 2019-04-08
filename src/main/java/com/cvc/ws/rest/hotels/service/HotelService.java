/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 16:10
 */
package com.cvc.ws.rest.hotels.service;

import com.cvc.ws.rest.hotels.bean.ResponseHotel;
import com.cvc.ws.rest.hotels.bean.ResponseHotelTravel;

import java.util.Date;
import java.util.List;

/**
 * Class: HotelService
 * Description: Interface hotel service business
 */
public interface HotelService {

	/**
	 * Method travelHotelsAvail
	 * @param idCity Long
	 * @param checkIn Date
	 * @param checkOut Date
	 * @param qtdAdult int
	 * @param qtdChild int
	 * @param hotelList ResponseHotel[]
	 *
	 * @return List<ResponseHotelTravel>
	 */
	List<ResponseHotelTravel> travelHotelsAvail(Long idCity, Date checkIn, Date checkOut, int qtdAdult, int qtdChild, ResponseHotel[] hotelList);

	/**
	 * Method travelHotelDetails
	 * @param idHotel Long
	 * @param checkIn Date
	 * @param checkOut Date
	 * @param qtdAdult int
	 * @param qtdChild int
	 * @param hotelList ResponseHotel[]
	 *
	 * @return ResponseHotelTravel
	 */
	ResponseHotelTravel travelHotelDetails(Long idHotel, Date checkIn, Date checkOut, int qtdAdult, int qtdChild, ResponseHotel[] hotelList);
}
