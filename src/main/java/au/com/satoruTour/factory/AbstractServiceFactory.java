/**
 * 
 */
package au.com.satoruTour.factory;

import au.com.satoruTour.businessLogic.ShoppingCartServiceIF;

/**
 * @author Satoru
 *
 */
public abstract class AbstractServiceFactory {
	
	/**
	 * create and/or get shoppingCartService
	 * 
	 * @return shoppingCartService
	 */
	public abstract ShoppingCartServiceIF createShoppingCartService();
}
