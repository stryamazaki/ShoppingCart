/**
 * 
 */
package au.com.satoruTour.businessLogic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import au.com.satoruTour.entity.PromotionRule;
import au.com.satoruTour.entity.Tour;

/**
 * @author Satoru
 *
 */
public interface PromotionRuleCalculatorIF {
	/**
	 * calculate the total price of all items in a shopping cart
	 * 
	 * @param tourId
	 * @param shoppingCart
	 * @param promotionRule
	 * @return calculatedPrice
	 */
	public BigDecimal calculate(String tourId, Map<String, List<Tour>> shoppingCart, PromotionRule promotionRule);
}
