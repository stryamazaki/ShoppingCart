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
public enum BuyAnotherGetFreeRuleCalculator implements PromotionRuleCalculatorIF{

	INSTANCE ("buyAnotherGetFreeRule");

	private String promotionRuleName;
	
	private BuyAnotherGetFreeRuleCalculator(String promotionRuleName) {
		this.promotionRuleName = promotionRuleName;
	}

	/* (non-Javadoc)
	 * @see au.com.sydneyTour.businessLogic.PromotionRuleCalculator#calculate(java.lang.String, java.util.Map, au.com.sydneyTour.entity.PromotionRule)
	 */
	@Override
	public BigDecimal calculate(String tourId, Map<String, List<Tour>> shoppingCart, PromotionRule promotionRule) {
		// get all tours which the But Another Get Free Rule to be applied
		List<Tour> tours = shoppingCart.get(tourId);
		BigDecimal calculatedPrice = BigDecimal.ZERO;
		if (tours != null && tours.size() > 0){
			// get the rule attribute
			String tourIdToBeFreeFor = promotionRule.getRuleAttribute1();
			// get the unit price
			BigDecimal unitPrice = ((ArrayList<Tour>)tours).get(0).getPrice();
			List<Tour> toursToBeFreeFor = shoppingCart.get(tourIdToBeFreeFor);
			if (toursToBeFreeFor == null || toursToBeFreeFor.isEmpty()) {
				// calculate sub-total
				calculatedPrice = new BigDecimal(tours.size()).multiply(unitPrice);
			} else {
				// calculate the number of tours still need to be paid by subtracting the number of tours to be free for
				int numberOfToursStillNeedToBePaid = tours.size() -  toursToBeFreeFor.size();
				if (numberOfToursStillNeedToBePaid > 0) {
					// calculate sub-total
					calculatedPrice = new BigDecimal(numberOfToursStillNeedToBePaid).multiply(unitPrice);
				}
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
