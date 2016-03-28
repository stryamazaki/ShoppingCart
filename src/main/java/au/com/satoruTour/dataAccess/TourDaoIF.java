/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.util.List;

import au.com.satoruTour.entity.Tour;

/**
 * @author Satoru
 *
 */
public interface TourDaoIF {
	
	/**
	 * get all tours from the data storage
	 * 
	 * @return tours
	 */
	List<Tour> getAll();

	/**
	 * get a tour for a given id from the data storage
	 * 
	 * @param id
	 * @return tour
	 */
	Tour getById(String id);
}
