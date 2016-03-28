package au.com.satoruTour.businessDelegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import au.com.satoruTour.entity.PromotionRule;
import au.com.satoruTour.entity.Tour;

public class ShoppingCartBusinessDelegateTest {

	// test cases
	// 0. test loading tours and promotion rules from the data storage 
	// 1. test the BuyManyGetFree promotion rule 
	// 2. test the BuyAnothreGetFree promotion rule
	// 3. test the BulkDiscountRule promotion rule
	// 4. test the combination of promotion rules 
	// 5. test irregular cases
	
	// 0. test loading tours and promotion rules from the data storage
	@Test
	public void testLoadingToursAndPromotionRules() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		List<Tour> tours = shoppingCartBusinessDelegate.getAllTours();
		assertNotNull("No tours in the data storage.", tours);
		assertTrue("The number of the tours is not 3", tours.size() == 3);
		List<PromotionRule> promotionRules = shoppingCartBusinessDelegate.getPromotionRules();
		assertNotNull("No promotion rules in the data storage.", promotionRules);
		assertTrue("The number of the promotion rules is not 3", promotionRules.size() == 3);
	}
	
	// 1. test the BuyManyGetFree promotion rule
	@Test
	public void testTwoBuyManyGetFreeRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("600").compareTo(total) == 0);
	}
	
	@Test
	public void testThreeBuyManyGetFreeRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("600").compareTo(total) == 0);
	}
	
	@Test
	public void testFourBuyManyGetFreeRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("900").compareTo(total) == 0);
	}
	
	@Test
	public void testTenBuyManyGetFreeRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour5 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour6 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour7 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour8 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour9 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour10 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		shoppingCartBusinessDelegate.add(tour5);
		shoppingCartBusinessDelegate.add(tour6);
		shoppingCartBusinessDelegate.add(tour7);
		shoppingCartBusinessDelegate.add(tour8);
		shoppingCartBusinessDelegate.add(tour9);
		shoppingCartBusinessDelegate.add(tour10);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("2100").compareTo(total) == 0);
	}
	
	// 2. test BuyAnotherGetFreeRule promotion rule
	@Test
	public void testTwoBuyAnotherGetFreeRuleToursWithNoTriggeringTour() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("SK");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("60").compareTo(total) == 0);
	}
	
	@Test
	public void testTwoBuyAnotherGetFreeRuleToursWithOneTriggeringTour() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("330").compareTo(total) == 0);
	}
	
	@Test
	public void testTwoBuyAnotherGetFreeRuleToursWithTwoTriggeringTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("600").compareTo(total) == 0);
	}
	
	@Test
	public void testTwoBuyAnotherGetFreeRuleToursWithThreeTriggeringTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour5 = shoppingCartBusinessDelegate.getTourById("OH");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		shoppingCartBusinessDelegate.add(tour5);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("600").compareTo(total) == 0);
	}
	
	
	// 3. test the BulkDiscountRule promotion rule
	@Test
	public void testFourBulkDiscountRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("BC");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("440").compareTo(total) == 0);
	}
	
	@Test
	public void testFiveBulkDiscountRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour5 = shoppingCartBusinessDelegate.getTourById("BC");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		shoppingCartBusinessDelegate.add(tour5);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("450").compareTo(total) == 0);
	}
	
	@Test
	public void testSixBulkDiscountRuleTours() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour5 = shoppingCartBusinessDelegate.getTourById("BC");
		assertNotNull("The test tour no longer exists.", tour1);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		shoppingCartBusinessDelegate.add(tour5);
		shoppingCartBusinessDelegate.add(tour5);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("540").compareTo(total) == 0);
	}
	
	// 4. test the combination of promotion rules
	@Test
	public void testRuleCombination1() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("BC");
		assertNotNull("The test tour no longer exists.", tour1);
		assertNotNull("The test tour no longer exists.", tour2);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("710").compareTo(total) == 0);
	}
	
	@Test
	public void testRuleCombination2() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("SK");
		assertNotNull("The test tour no longer exists.", tour1);
		assertNotNull("The test tour no longer exists.", tour2);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("300").compareTo(total) == 0);
	}
	
	@Test
	public void testRuleCombination3() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		Tour tour1 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour2 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour3 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour4 = shoppingCartBusinessDelegate.getTourById("BC");
		Tour tour5 = shoppingCartBusinessDelegate.getTourById("OH");
		Tour tour6 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour7 = shoppingCartBusinessDelegate.getTourById("SK");
		Tour tour8 = shoppingCartBusinessDelegate.getTourById("BC");
		assertNotNull("The test tour no longer exists.", tour1);
		assertNotNull("The test tour no longer exists.", tour5);
		shoppingCartBusinessDelegate.add(tour1);
		shoppingCartBusinessDelegate.add(tour2);
		shoppingCartBusinessDelegate.add(tour3);
		shoppingCartBusinessDelegate.add(tour4);
		shoppingCartBusinessDelegate.add(tour5);
		shoppingCartBusinessDelegate.add(tour6);
		shoppingCartBusinessDelegate.add(tour7);
		shoppingCartBusinessDelegate.add(tour8);
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", new BigDecimal("780").compareTo(total) == 0);
	}
	
	// 5. Irregular cases
	@Test
	public void testNoToursInTheCart() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = createShoppingCartBusinessDelegate();
		assertEquals(0, shoppingCartBusinessDelegate.getShoppingCart().size());
		BigDecimal total = shoppingCartBusinessDelegate.total();
		assertTrue("The total price is incorrect", BigDecimal.ZERO.compareTo(total) == 0);
	}
	
	private ShoppingCartBusinessDelegate createShoppingCartBusinessDelegate() {
		ShoppingCartBusinessDelegate shoppingCartBusinessDelegate = new ShoppingCartBusinessDelegate();
		List<PromotionRule> promotionRules = shoppingCartBusinessDelegate.getAllPromotionRules();
		shoppingCartBusinessDelegate.setPromotionRules(promotionRules);
		return shoppingCartBusinessDelegate;
	}
}
