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
public enum BuyManyGetFreeRuleCalculator implements PromotionRuleCalculatorIF {

	INSTANCE ("buyManyGetFreeRule");

	private String promotionRuleName;

	private BuyManyGetFreeRuleCalculator(String promotionRuleName) {
		this.promotionRuleName = promotionRuleName;
	}

	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.PromotionRuleCalculatorIF#calculate(java.lang.String, java.util.Map, au.com.satoruTour.entity.PromotionRule)
	 */
	@Override
	public BigDecimal calculate(String tourId, Map<String, List<Tour>> toursInShoppingCart, PromotionRule promotionRule) {
		// get all tours which the But Many Get Free Rule to be applied
		List<Tour> tours = toursInShoppingCart.get(tourId);
		BigDecimal calculatedPrice = BigDecimal.ZERO;
		if (tours != null && tours.size() > 0){
			// get the rule attribute
			String numberOfToursToBeEligibleString = promotionRule.getRuleAttribute1();
			String numberOfToursToNowUsedString = promotionRule.getRuleAttribute2();
			Integer numberOfToursToBeEligible;
			Integer numberOfToursToBeNowUsed;
			try {
				numberOfToursToBeEligible = Integer.valueOf(numberOfToursToBeEligibleString);
				numberOfToursToBeNowUsed = Integer.valueOf(numberOfToursToNowUsedString);
			} catch (NumberFormatException ex) {
				throw new IllegalStateException("APPROPREATE MESSAGE", ex);
			}
			// get the unit price
			BigDecimal unitPrice = ((ArrayList<Tour>)tours).get(0).getPrice();
			if (tours.size() < numberOfToursToBeEligible) {
				// calculate sub-total
				calculatedPrice = new BigDecimal (tours.size()).multiply(unitPrice);
			}
			else {
				// calculate sub-total
				BigDecimal numberOfTimesTobeMultiplied = new BigDecimal(tours.size() / numberOfToursToBeEligible);
				BigDecimal numberOfToursOriginalRuleStillApplied = new BigDecimal(tours.size() % numberOfToursToBeEligible);
				BigDecimal priceToBeMultiplied = new BigDecimal(numberOfToursToBeNowUsed).multiply(unitPrice);
				BigDecimal majorPricePart = priceToBeMultiplied.multiply(numberOfTimesTobeMultiplied);
				BigDecimal restPricePart = unitPrice.multiply(numberOfToursOriginalRuleStillApplied);
				calculatedPrice = calculatedPrice.add(majorPricePart).add(restPricePart);
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
