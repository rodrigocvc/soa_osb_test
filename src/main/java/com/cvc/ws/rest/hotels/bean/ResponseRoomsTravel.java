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

/**
 * Class: ResponseRoomsTravel
 * Description: Responsible for rooms travel service response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoomsTravel {
	Long roomID;
	String categoryName;
	Double totalPrice;
	ResponsePriceTravel priceDetail;
}
