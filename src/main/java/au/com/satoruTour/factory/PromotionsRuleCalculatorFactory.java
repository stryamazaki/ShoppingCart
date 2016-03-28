/**
 * 
 */
package au.com.satoruTour.factory;

import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.businessLogic.BulkDiscountRuleCalculator;
import au.com.satoruTour.businessLogic.BuyAnotherGetFreeRuleCalculator;
import au.com.satoruTour.businessLogic.BuyManyGetFreeRuleCalculator;
import au.com.satoruTour.businessLogic.PromotionRuleCalculatorIF;

/**
 * @author Satoru
 *
 */
public class PromotionsRuleCalculatorFactory extends AbstractPromotionsRuleCalculatorFactory {
	
	private static PromotionsRuleCalculatorFactory instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private PromotionsRuleCalculatorFactory() {
		// do nothing but necessary to be singleton
	}

	/**
	 * get singleton
	 * 
	 * @return instance
	 */
	public static PromotionsRuleCalculatorFactory getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new PromotionsRuleCalculatorFactory();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
    
	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractPromotionsRuleCalculatorFactory#createBulkDiscountRuleCalculator()
	 */
	@Override
	protected PromotionRuleCalculatorIF createBulkDiscountRuleCalculator() {
		return BulkDiscountRuleCalculator.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractPromotionsRuleCalculatorFactory#createBuyAnotherGetFreeRuleCalculator()
	 */
	@Override
	protected PromotionRuleCalculatorIF createBuyAnotherGetFreeRuleCalculator() {
		return BuyAnotherGetFreeRuleCalculator.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractPromotionsRuleCalculatorFactory#createBuyManyGetFreeRuleCalculator()
	 */
	@Override
	protected PromotionRuleCalculatorIF createBuyManyGetFreeRuleCalculator() {
		return BuyManyGetFreeRuleCalculator.INSTANCE;
	}
}
