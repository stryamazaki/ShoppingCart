/**
 * 
 */
package au.com.satoruTour.factory;

import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.businessLogic.ShoppingCartService;
import au.com.satoruTour.businessLogic.ShoppingCartServiceIF;

/**
 * @author Satoru
 *
 */
public class ServiceFactory extends AbstractServiceFactory {
	
	private static ServiceFactory instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private ServiceFactory() {
		// do nothing but necessary to be singleton
	}

	/**
	 * get singleton
	 * 
	 * @return instance
	 */
	public static ServiceFactory getInstance() {
    	if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new ServiceFactory();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
    
	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractServiceFactory#createShoppingCartService()
	 */
	@Override
	public ShoppingCartServiceIF createShoppingCartService() {
		return ShoppingCartService.getInstance();
	}
}
