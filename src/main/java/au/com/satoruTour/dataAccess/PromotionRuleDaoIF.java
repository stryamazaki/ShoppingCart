/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.util.List;

import au.com.satoruTour.entity.PromotionRule;

/**
 * @author Satoru
 *
 */
public interface PromotionRuleDaoIF {
	
	/**
	 * get all promotion rules from the data storage
	 * 
	 * @return promotionRules
	 */
	List<PromotionRule> getAll();
}
