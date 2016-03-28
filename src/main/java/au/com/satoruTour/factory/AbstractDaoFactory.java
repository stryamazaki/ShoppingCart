/**
 * 
 */
package au.com.satoruTour.factory;

import au.com.satoruTour.dataAccess.PromotionRuleDaoIF;
import au.com.satoruTour.dataAccess.TourDaoIF;

/**
 * @author Satoru
 *
 */
public abstract class AbstractDaoFactory {
	/**
	 * create and/or get tourDao
	 * 
	 * @return tourDao
	 */
	public abstract TourDaoIF createTourDao();
	
	/**
	 * create and/or get promotionRuleDao
	 * 
	 * @return promotionRuleDao
	 */
	public abstract PromotionRuleDaoIF createPromotionRuleDao();
}
