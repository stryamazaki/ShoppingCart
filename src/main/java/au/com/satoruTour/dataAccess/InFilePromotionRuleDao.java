/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.entity.PromotionRule;

/**
 * @author Satoru
 *
 */
public class InFilePromotionRuleDao extends InFileGenericDao<PromotionRule> implements PromotionRuleDaoIF {
	
	private static InFilePromotionRuleDao instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private InFilePromotionRuleDao() {
		// do nothing but necessary to be singleton
	}

	/**
	 * get singleton
	 * 
	 * @return instance
	 */
	public static InFilePromotionRuleDao getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new InFilePromotionRuleDao();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	@Override
	protected PromotionRule populateEntity(String id, String[] splitPromotionsRulePropertiesString) {
		PromotionRule promotionRule = new PromotionRule();
		try {
			promotionRule.setName(splitPromotionsRulePropertiesString[0]);
			promotionRule.setRuleAttribute1(splitPromotionsRulePropertiesString[1]);
			promotionRule.setRuleAttribute2(splitPromotionsRulePropertiesString[2]);
			promotionRule.setId(Long.valueOf(id));
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalStateException("APPROPREATE MESSAGE", ex);
		} catch (NumberFormatException ex) {
			throw new IllegalStateException("APPROPREATE MESSAGE", ex);
		}
		return promotionRule;
	}

	@Override
	protected String getPropertiesFileName() {
		return PropertyFilesHelper.PROPERTIES_FILE_NAME_PROMOTION_RULES;
	}
}
