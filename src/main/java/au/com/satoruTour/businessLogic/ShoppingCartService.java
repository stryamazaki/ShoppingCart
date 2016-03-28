/**
 * 
 */
package au.com.satoruTour.businessLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.dataAccess.PromotionRuleDaoIF;
import au.com.satoruTour.dataAccess.TourDaoIF;
import au.com.satoruTour.entity.PromotionRule;
import au.com.satoruTour.entity.Tour;
import au.com.satoruTour.factory.AbstractDaoFactory;
import au.com.satoruTour.factory.AbstractPromotionsRuleCalculatorFactory;
import au.com.satoruTour.factory.DaoFactory;
import au.com.satoruTour.factory.PromotionsRuleCalculatorFactory;

/**
 * @author Satoru
 *
 */
public class ShoppingCartService implements ShoppingCartServiceIF {
	
	private TourDaoIF tourDao;
	private PromotionRuleDaoIF promotionRuleDao;
	private static ShoppingCartService instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private ShoppingCartService() {
		AbstractDaoFactory daoFactory = DaoFactory.getInstance();
		tourDao = daoFactory.createTourDao();
		promotionRuleDao = daoFactory.createPromotionRuleDao();
	}

	/**
	 * return singleton
	 * 
	 * @return instance
	 */
	public static ShoppingCartService getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new ShoppingCartService();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	

	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.ShoppingCartServiceIF#getAllTours()
	 */
	@Override
	public List<Tour> getAllTours() {
		List<Tour> tours = tourDao.getAll();
		if (tours == null)	{
			return new ArrayList<Tour>();
		}
		return tours;
	}
	
	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.ShoppingCartServiceIF#getTourById(java.lang.String)
	 */
	@Override
	public Tour getTourById(String id) {
		return tourDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.ShoppingCartServiceIF#getAllPromotionRules()
	 */
	@Override
	public List<PromotionRule> getAllPromotionRules() {
		List<PromotionRule> promotionRules = promotionRuleDao.getAll();
		if (promotionRules == null)	{
			return new ArrayList<PromotionRule>();
		}
		return promotionRules;
	}
	
	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.ShoppingCartServiceIF#addToShoppingCart(au.com.satoruTour.entity.Tour, java.util.Map)
	 */
	@Override
	public void addToShoppingCart(Tour tour, Map<String, List<Tour>> shoppingCart) {
		// if the same tour has been already added to the shopping cart
		if (shoppingCart.containsKey(tour.getId())){
			List<Tour> tours = shoppingCart.get(tour.getId());
			tours.add(tour);
		}
		// otherwise
		else{
			List<Tour> tours = new ArrayList<Tour>();
			tours.add(tour);
			shoppingCart.put(tour.getId(), tours); 
		}
	}
	
	/* (non-Javadoc)
	 * @see au.com.satoruTour.businessLogic.ShoppingCartServiceIF#calculateTotalPrice(java.util.List, java.util.Map)
	 */
	@Override
	public BigDecimal calculateTotalPrice(List<PromotionRule> promotionRules, Map<String, List<Tour>> shoppingCart) {
		BigDecimal total = BigDecimal.ZERO; 
		Set<String> tourIds = shoppingCart.keySet();
		for (String tourId : tourIds){
			// check if any promotion rule is attached
			PromotionRule promotionRule = findPromotionRule(promotionRules, tourId, shoppingCart);
			total = total.add(subtotal(tourId, promotionRule, shoppingCart));
		}
		return total;
	}

	private BigDecimal subtotal(String tourId, PromotionRule promotionRule, Map<String, List<Tour>> shoppingCart) {
		BigDecimal subtotal = BigDecimal.ZERO;
		// if no
		if (promotionRule == null) {
			subtotal = calculateNonPromotedTour(tourId, shoppingCart);
		}
		// if yes
		else {
			AbstractPromotionsRuleCalculatorFactory promotionsRuleCalculatorFactory = PromotionsRuleCalculatorFactory.getInstance();
			PromotionRuleCalculatorIF promotionRuleCalculator = promotionsRuleCalculatorFactory.createPromotionRuleCalculator(promotionRule);
			if (promotionRuleCalculator == null) {
				throw new IllegalStateException("APPROPREATE MESSAGE");
			} 
			subtotal = promotionRuleCalculator.calculate(tourId, shoppingCart, promotionRule);
		}
		return subtotal;
	}

	private PromotionRule findPromotionRule(List<PromotionRule> promotionRuleList, String tourId, Map<String, List<Tour>> shoppingCart) {
		// find whether or not a promotion is attached
		List<Tour> tourList = shoppingCart.get(tourId);
		Tour tour = tourList.get(0);
		if (tour.getPromotionRuleId() == null){
			return null;
		}
		// if attached, find the promotion rule
		if (promotionRuleList != null){
			for (PromotionRule promotionRule : promotionRuleList){
				if (tour.getPromotionRuleId().equals(promotionRule.getId())){
					return promotionRule;
				}
			}
		}
		return null;
	}

	private BigDecimal calculateNonPromotedTour(String tourId, Map<String, List<Tour>> shoppingCart) {
		List<Tour> tours = shoppingCart.get(tourId);
		BigDecimal unitPrice = ((ArrayList<Tour>)tours).get(0).getPrice();
		BigDecimal calculatedPrice = new BigDecimal(tours.size()).multiply(unitPrice);
		return calculatedPrice;
	}
}
