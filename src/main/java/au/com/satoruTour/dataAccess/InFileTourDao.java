/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

import au.com.satoruTour.entity.Tour;

/**
 * @author Satoru
 * 
 */
public class InFileTourDao extends InFileGenericDao<Tour> implements TourDaoIF {
	
	private static InFileTourDao instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private InFileTourDao() {
		// do nothing but necessary to be singleton
	}

	/**
	 * get singleton
	 * 
	 * @return instance
	 */
	public static InFileTourDao getInstance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new InFileTourDao();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	@Override
	protected Tour populateEntity(String id, String[] splitTourPropertiesString) {
		Tour tour = new Tour();
		tour.setId(id);
		try {
			tour.setName(splitTourPropertiesString[0]);
			tour.setPrice(new BigDecimal(splitTourPropertiesString[1]));
			tour.setPromotionRuleId(Long.valueOf(splitTourPropertiesString[2]));
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalStateException("APPROPREATE MESSAGE", ex);
		} catch (NumberFormatException ex) {
			throw new IllegalStateException("APPROPREATE MESSAGE", ex);
		}
		return tour;
	}

	@Override
	protected String getPropertiesFileName() {
		return PropertyFilesHelper.PROPERTIES_FILE_NAME_TOURS;
	}
}
