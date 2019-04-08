/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 13:04
 */
package com.cvc.ws.rest.hotels.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class: ResponseRooms
 * Description: Responsible for rooms service response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRooms {
	Long roomID;
	String categoryName;
	ResponsePrice price;
}
