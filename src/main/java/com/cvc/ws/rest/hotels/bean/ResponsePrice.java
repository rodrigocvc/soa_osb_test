/**
 * Created using IntelliJ IDEA
 *
 * @author Alysson Falcão
 * <p>
 * Project: hotels
 * Date: 01/10/18
 * Time: 13:06
 */
package com.cvc.ws.rest.hotels.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class: ResponsePrice
 * Description: Responsible for prices service response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePrice {
	Double adult;
	Double child;
}
