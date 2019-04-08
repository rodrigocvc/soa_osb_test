/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 22:09
 */
package com.cvc.ws.rest.hotels.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class: ResponseHotelTravel
 * Description: Responsible for hotel travel service response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotelTravel {
	Long id;
	String cityName;
	List<ResponseRoomsTravel> rooms;
}
