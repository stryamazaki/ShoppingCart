/**
 * 
 */
package au.com.satoruTour.businessDelegate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.satoruTour.businessLogic.ShoppingCartServiceIF;
import au.com.satoruTour.entity.PromotionRule;
import au.com.satoruTour.entity.Tour;
import au.com.satoruTour.factory.AbstractServiceFactory;
import au.com.satoruTour.factory.ServiceFactory;

/**
 * @author Satoru
 *
 */
public class ShoppingCartBusinessDelegate {
	
	private ShoppingCartServiceIF shoppingCartService;
	private List<PromotionRule> promotionRules;
	private Map<String, List<Tour>> shoppingCart; 

	/**
	 * initialise shoppingCartService, shoppingCart and promotionRules
	 */
	public ShoppingCartBusinessDelegate() {
		AbstractServiceFactory serviceFactory = ServiceFactory.getInstance(); 
		shoppingCartService = serviceFactory.createShoppingCartService();
		shoppingCart = new HashMap<String, List<Tour>>();
		promotionRules = new ArrayList<PromotionRule>();
	}

	/**
	 * get all tours from the data storage
	 * 
	 * @return tours
	 */
	public List<Tour> getAllTours() {
		List<Tour> tours = null;
		try {
			tours = shoppingCartService.getAllTours(); 
		} catch (RuntimeException ex) {
			// TODO unexpected error to be recorded in a log file and also email it to IT support
			// TODO inform the unexpected error to a caller
		}
		return tours; 
	} 
	
	/**
	 * add a tour to a shopping cart
	 * 
	 * @param tour
	 */
	public void add(Tour tour) {
		// TODO validate the parameter. if invalid, inform the validation error to a caller
		try {
			shoppingCartService.addToShoppingCart(tour, shoppingCart);
		} catch (RuntimeException ex) {
			// TODO unexpected error to be recorded in a log file and also email it to IT support
			// TODO inform the unexpected error to a caller
		}
	}

	/**
	 * calculate the total price of all items in a shopping cart (promotion rules to be applied)
	 * 
	 * @return total
	 */
	public BigDecimal total() {
		BigDecimal total = null;
		try {
			total = shoppingCartService.calculateTotalPrice(promotionRules, shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (RuntimeException ex) {
			// TODO unexpected error to be recorded in a log file and also email it to IT support
			// TODO inform the unexpected error to a caller
		}
		return total;
	}

	/**
	 * get a tour for a given tour id
	 * 
	 * @param id
	 * @return tour
	 */
	public Tour getTourById(String id) {
		// TODO validate the parameter. if invalid, inform the validation error to a caller
		Tour tour = null;
		try {
			tour = shoppingCartService.getTourById(id); 
		} catch (RuntimeException ex) {
			// TODO unexpected error to be recorded in a log file and also email it to IT support
			// TODO inform the unexpected error to a caller
		}
		return tour;    
	}
	
	/**
	 * get all promotion rules from the data storage
	 * 
	 * @return promotionRules
	 */
	public List<PromotionRule> getAllPromotionRules() {
		List<PromotionRule> promotionRules = null;
		try {
			promotionRules = shoppingCartService.getAllPromotionRules(); 
		} catch (RuntimeException ex) {
			// TODO unexpected error to be recorded in a log file and also email it to IT support
			// TODO inform the unexpected error to a caller
		}
		return promotionRules;    	
	}

	/**
	 * @return the unmodifiable promotionRules
	 */
	public List<PromotionRule> getPromotionRules() {
		return Collections.unmodifiableList(promotionRules);
	}

	/**
	 * @param promotionRules the promotionRules to set
	 */
	public void setPromotionRules(List<PromotionRule> promotionRules) {
		this.promotionRules = promotionRules;
	}

	/**
	 * @return the unmodifiable shoppingCart
	 */
	public Map<String, List<Tour>> getShoppingCart() {
		return Collections.unmodifiableMap(shoppingCart);
	}
}
