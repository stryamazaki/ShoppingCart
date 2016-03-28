/**
 * 
 */
package au.com.satoruTour.factory;

import au.com.satoruTour.businessLogic.BulkDiscountRuleCalculator;
import au.com.satoruTour.businessLogic.BuyAnotherGetFreeRuleCalculator;
import au.com.satoruTour.businessLogic.BuyManyGetFreeRuleCalculator;
import au.com.satoruTour.businessLogic.PromotionRuleCalculatorIF;
import au.com.satoruTour.entity.PromotionRule;

/**
 * @author Satoru
 *
 */
public abstract class AbstractPromotionsRuleCalculatorFactory {
	/**
	 * get promotionRuleCalculator for a given promotionRule
	 * 
	 * @param promotionRule
	 * @return promotionRuleCalculator
	 */
	public PromotionRuleCalculatorIF createPromotionRuleCalculator(PromotionRule promotionRule) {
		PromotionRuleCalculatorIF promotionRuleCalculator = null;
		if (BuyManyGetFreeRuleCalculator.INSTANCE.getPromotionRuleName().equals(promotionRule.getName())){
			promotionRuleCalculator = createBuyManyGetFreeRuleCalculator();
		} else if (BuyAnotherGetFreeRuleCalculator.INSTANCE.getPromotionRuleName().equals(promotionRule.getName())){
			promotionRuleCalculator = createBuyAnotherGetFreeRuleCalculator();
		} else if (BulkDiscountRuleCalculator.INSTANCE.getPromotionRuleName().equals(promotionRule.getName())){
			promotionRuleCalculator = createBulkDiscountRuleCalculator();
		}
		return promotionRuleCalculator;
	}
	
	protected abstract PromotionRuleCalculatorIF createBulkDiscountRuleCalculator();
	
	protected abstract PromotionRuleCalculatorIF createBuyAnotherGetFreeRuleCalculator();
	
	protected abstract PromotionRuleCalculatorIF createBuyManyGetFreeRuleCalculator();
}
