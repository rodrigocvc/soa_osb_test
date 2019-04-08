/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 12:57
 */
package com.cvc.ws.rest.hotels.controller;

import com.cvc.ws.rest.hotels.bean.ResponseHotel;
import com.cvc.ws.rest.hotels.bean.ResponseHotelTravel;
import com.cvc.ws.rest.hotels.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class: HotelRestController
 * Description: Controller api restfull call service hotels informations
 */
@RestController
@RequestMapping("/hotels")
@EnableSwagger2
@Api(description = "Consulting Hotels Information")
public class HotelRestController {

	private static final Logger LOG = LoggerFactory.getLogger(HotelRestController.class);

	@Resource
	private HotelService hotelService;

	private RestTemplate restTemplate = new RestTemplate();
	private String urlAvail = "https://cvcbackendhotel.herokuapp.com/hotels/avail/";
	private String urlDetail = "https://cvcbackendhotel.herokuapp.com/hotels/";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@ApiOperation(value = "Consulting hotel available travel.")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<ResponseHotelTravel>> travelHotelsAvail(
			@RequestParam(value="cityCode") Long cityCode,
			@RequestParam(value="checkIn") String checkIn,
			@RequestParam(value="checkOut") String checkOut,
			@RequestParam(value="qtdAdult") Integer qtdAdult,
			@RequestParam(value="qtdChild") Integer qtdChild)
	{
		LOG.info("Start travelHotelsAvail with cityCode: " + cityCode);

		if(cityCode == null || checkIn == null || checkOut == null || qtdAdult == null || qtdChild == null)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<ResponseHotelTravel> hotelTravels = null;

		try
		{
			ResponseHotel[] hotels = restTemplate.getForObject(urlAvail + cityCode, ResponseHotel[].class);
			hotelTravels = hotelService.travelHotelsAvail(cityCode, sdf.parse(checkIn), sdf.parse(checkOut), qtdAdult, qtdChild, hotels);
			if (hotelTravels == null || hotelTravels.size() == 0)
			{
				LOG.error(HttpStatus.NOT_FOUND.name());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error", e);
		}

		LOG.info("Finish travelHotelsAvail with cityCode: " + cityCode + " Return " + HttpStatus.OK.name());
		return new ResponseEntity<>(hotelTravels, HttpStatus.OK);
	}

	@ApiOperation(value = "Consulting hotel details.")
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<ResponseHotelTravel> getHotelDetails(
			@RequestParam(value="idHotel") Long idHotel,
			@RequestParam(value="checkIn") String checkIn,
			@RequestParam(value="checkOut") String checkOut,
			@RequestParam(value="qtdAdult") Integer qtdAdult,
			@RequestParam(value="qtdChild") Integer qtdChild)
	{
		LOG.info("Start getHotelDetails with idHotel: " + idHotel);

		if(idHotel == null || checkIn == null || checkOut == null || qtdAdult == null || qtdChild == null)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		ResponseHotelTravel hotelTravel = null;

		try
		{
			ResponseHotel[] hotels = restTemplate.getForObject(urlDetail + idHotel, ResponseHotel[].class);
			hotelTravel = hotelService.travelHotelDetails(idHotel, sdf.parse(checkIn), sdf.parse(checkOut), qtdAdult, qtdChild, hotels);
			if (hotelTravel == null)
			{
				LOG.error(HttpStatus.NOT_FOUND.name());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		LOG.info("Finish getHotelDetails with idHotel: " + idHotel + " Return " + HttpStatus.OK.name());
		return new ResponseEntity<>(hotelTravel, HttpStatus.OK);
	}
}
