/**
 * 
 */
package au.com.satoruTour.dataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Satoru
 *
 */
public class PropertyFilesHelper {
	public static final String PROPERTIES_FILE_NAME_PROMOTION_RULES = "promotionRules.properties";
	public static final String PROPERTIES_FILE_NAME_TOURS = "tours.properties";
	
	public static Properties load(String propertiesFileName) {
		Properties properties = new Properties();
		try (InputStream propertiesInputStream = PropertyFilesHelper.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
			properties.load(propertiesInputStream);
		} catch (IOException ex) {
			throw new IllegalStateException("APPROPREATE MESSAGE", ex);
		}
		return properties;
	}
}
