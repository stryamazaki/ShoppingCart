/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author Satoru
 * 
 */
public abstract class InFileGenericDao<T> {

	/**
	 * create entities from all items in a properties file
	 * 
	 * @return entityList
	 */
	public List<T> getAll() {
		Properties properties = PropertyFilesHelper.load(getPropertiesFileName());
		Enumeration<?> e = properties.propertyNames();
		List<T> entityList = new ArrayList<T>();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = properties.getProperty(key);
			if (value == null || value.isEmpty()) {
				throw new IllegalStateException("APPROPREATE MESSAGE");
			}
			String [] splitValueString = value.split(",");
			entityList.add(populateEntity(key, splitValueString));
		}
		return entityList;
	}

	/**
	 * create an entity from an item in a properties file for a given id
	 * 
	 * @param id
	 * @return entity
	 */
	public T getById(String id) {
		Properties properties = PropertyFilesHelper.load(getPropertiesFileName());
		Enumeration<?> e = properties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (key.equals(id)){
				String value = properties.getProperty(key);
				if (value == null || value.isEmpty()) {
					throw new IllegalStateException("APPROPREATE MESSAGE");
				}
				String [] splitValueString = value.split(",");
				return populateEntity(key, splitValueString);
			}
		}
		return null;
	}
	
	abstract protected T populateEntity(String id, String[] splitTourPropertiesString);
	
	abstract protected String getPropertiesFileName();
}
