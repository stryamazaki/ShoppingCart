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
public interface ShoppingCartServiceIF {
	
	/**
	 * get all tours from the data storage
	 * 
	 * @return tours
	 */
	List<Tour> getAllTours();
	
	/**
	 * get a tour for a given id from the data storage
	 * 
	 * @param id
	 * @return tour
	 */
	Tour getTourById(String id);
	
	/**
	 * get all promotion rules from the data storage
	 * 
	 * @return promotion rules
	 */
	List<PromotionRule> getAllPromotionRules();
	
	/**
	 * add a tour to a shopping cart
	 * 
	 * @param tour
	 * @param shoppingCart
	 */
	void addToShoppingCart(Tour tour, Map<String, List<Tour>> shoppingCart);
	
	/**
	 * calculate the total price of all items in a shopping cart
	 * 
	 * @param promotionRules
	 * @param shoppingCart
	 * @return total
	 */
	BigDecimal calculateTotalPrice(List<PromotionRule> promotionRules, Map<String, List<Tour>> shoppingCart);
}
