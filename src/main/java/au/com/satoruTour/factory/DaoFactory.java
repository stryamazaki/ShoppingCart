/**
 * 
 */
package au.com.satoruTour.factory;

import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.dataAccess.InFilePromotionRuleDao;
import au.com.satoruTour.dataAccess.PromotionRuleDaoIF;
import au.com.satoruTour.dataAccess.InFileTourDao;
import au.com.satoruTour.dataAccess.TourDaoIF;

/**
 * @author Satoru
 *
 */
public class DaoFactory extends AbstractDaoFactory {
	
	private static DaoFactory instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private DaoFactory() {
		// do nothing but necessary to be singleton
	}

	/**
	 * get singleton
	 * 
	 * @return instance
	 */
	public static DaoFactory getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new DaoFactory();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
    
	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractDaoFactory#createTourDao()
	 */
	@Override
	public TourDaoIF createTourDao() {
		return InFileTourDao.getInstance();
	}

	/* (non-Javadoc)
	 * @see au.com.satoruTour.factory.AbstractDaoFactory#createPromotionRuleDao()
	 */
	@Override
	public PromotionRuleDaoIF createPromotionRuleDao() {
		return InFilePromotionRuleDao.getInstance();
	}
}
