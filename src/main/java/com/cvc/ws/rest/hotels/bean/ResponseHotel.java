/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 12:57
 */
package com.cvc.ws.rest.hotels.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class: ResponseHotel
 * Description: Responsible for hotel service response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotel {
	Long id;
	String name;
	Long cityCode;
	String cityName;
	List<ResponseRooms> rooms;
}
