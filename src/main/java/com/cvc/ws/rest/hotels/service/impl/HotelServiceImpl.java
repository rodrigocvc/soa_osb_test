/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 16:07
 */
package com.cvc.ws.rest.hotels.service.impl;

import com.cvc.ws.rest.hotels.bean.*;
import com.cvc.ws.rest.hotels.service.HotelService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class: HotelServiceImpl
 * Description: Implementation hotel service business
 */
@Service
@NoArgsConstructor
public class HotelServiceImpl implements HotelService {

	private static final Logger LOG = LoggerFactory.getLogger(HotelService.class);

	@Override
	public List<ResponseHotelTravel> travelHotelsAvail(Long idCity, Date checkIn, Date checkOut, int qtdAdult, int qtdChild, ResponseHotel[] hotelList)
	{
		LOG.info("Start service travelHotelsAvail");
		List<ResponseHotelTravel> hotelTravels = new ArrayList<>();
		for (ResponseHotel hotel : hotelList)
		{
			if (hotel.getCityCode().longValue() == idCity.longValue())
			{
				hotelTravels.add(populateHotelTravel(getPeriodDates(checkIn, checkOut), qtdAdult, qtdChild, hotel));
			}
		}
		if (hotelTravels.size() == 0)
		{
			LOG.warn("Not found travel hotels avail");
			return null;
		}
		LOG.info("Finish service travelHotelsAvail");
		return hotelTravels;
	}

	@Override
	public ResponseHotelTravel travelHotelDetails(Long idHotel, Date checkIn, Date checkOut, int qtdAdult, int qtdChild, ResponseHotel[] hotelList)
	{
		LOG.info("Start service travelHotelDetails");
		for (ResponseHotel hotel : hotelList)
		{
			if (hotel.getId().longValue() == idHotel.longValue())
			{
				LOG.info("Finish service travelHotelDetails");
				return populateHotelTravel(getPeriodDates(checkIn, checkOut), qtdAdult, qtdChild, hotel);
			}
		}
		LOG.warn("Not found travel hotels detail");
		return null;
	}

	/**
	 * Method getPeriodDates
	 * @param checkIn Date
	 * @param checkOut Date
	 *
	 * @return int
	 */
	private int getPeriodDates(Date checkIn, Date checkOut)
	{
		return (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * Method getComission
	 * @param value Double
	 *
	 * @return Double
	 */
	private Double getComission(Double value)
	{
		return value/0.7;
	}

	/**
	 * Method populateHotelTravel
	 * @param days int
	 * @param qtdAdult int
	 * @param qtdChild int
	 * @param hotel ResponseHotel
	 *
	 * @return ResponseHotelTravel
	 */
	private ResponseHotelTravel populateHotelTravel(final int days, final int qtdAdult, final int qtdChild, final ResponseHotel hotel)
	{
		ResponseHotelTravel hotelTravel = new ResponseHotelTravel(hotel.getId(), hotel.getCityName(), null);
		List<ResponseRoomsTravel> roomsTravels = new ArrayList<>();

		for (ResponseRooms room : hotel.getRooms())
		{
			final Double pricePerDayAdult = getComission(room.getPrice().getAdult());
			final Double pricePerDayChild = getComission(room.getPrice().getChild());
			final Double totalPrice = days * ((qtdAdult * pricePerDayAdult) + (qtdChild * pricePerDayChild));
			final ResponseRoomsTravel roomTravel = new ResponseRoomsTravel(room.getRoomID(), room.getCategoryName(), totalPrice, new ResponsePriceTravel(pricePerDayAdult, pricePerDayChild));
			roomsTravels.add(roomTravel);
		}

		if(roomsTravels.size() > 0)
		{
			hotelTravel.setRooms(roomsTravels);
		}
		else
		{
			hotelTravel = null;
		}

		return hotelTravel;
	}
}
