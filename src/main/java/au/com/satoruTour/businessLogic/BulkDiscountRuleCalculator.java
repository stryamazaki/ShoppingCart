/**
 * 
 */
package au.com.satoruTour.businessLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import au.com.satoruTour.entity.PromotionRule;
import au.com.satoruTour.entity.Tour;

/**
 * @author Satoru
 *
 */
public enum BulkDiscountRuleCalculator implements PromotionRuleCalculatorIF {

	INSTANCE ("bulkDiscountRule");

	private String promotionRuleName;
	
	private BulkDiscountRuleCalculator(String promotionRuleName) {
		this.promotionRuleName = promotionRuleName;
	}

	/* (non-Javadoc)
	 * @see au.com.sydneyTour.businessLogic.PromotionRuleCalculator#calculate(java.lang.String, java.util.Map, au.com.sydneyTour.entity.PromotionRule)
	 */
	@Override
	public BigDecimal calculate(String tourId, Map<String, List<Tour>> shoppingCart, PromotionRule promotionRule) {
		// get all tours which the Bulk Discount Rule to be applied
		List<Tour> tours = shoppingCart.get(tourId);
		BigDecimal calculatedPrice = BigDecimal.ZERO;
		if (tours != null && tours.size() > 0)	{
			// get the rule attributes
			String discountPriceString = promotionRule.getRuleAttribute1();
			String numberOfToursSufficientForDiscountString = promotionRule.getRuleAttribute2();
			BigDecimal discountPrice;
			Integer numberOfToursSufficientForDiscount;
			try {
				discountPrice = new BigDecimal(discountPriceString);
				numberOfToursSufficientForDiscount = Integer.valueOf(numberOfToursSufficientForDiscountString);
			} catch (NumberFormatException ex) {
				throw new IllegalStateException("APPROPREATE MESSAGE", ex);
			}
			// get the unit price
			BigDecimal unitPrice = ((ArrayList<Tour>)tours).get(0).getPrice();
			// calculate sub-total
			calculatedPrice = new BigDecimal(tours.size()).multiply(unitPrice);
			// apply some discount (rule attribute 1) if number of tours sufficient for discount (rule attribute 2) more than number of purchased
			if (tours.size() > numberOfToursSufficientForDiscount){
				calculatedPrice = calculatedPrice.subtract(new BigDecimal(tours.size()).multiply(discountPrice));
			}
		}
		return calculatedPrice;
	}

	/**
	 * @return the promotionRuleName
	 */
	public String getPromotionRuleName() {
		return promotionRuleName;
	}
}
