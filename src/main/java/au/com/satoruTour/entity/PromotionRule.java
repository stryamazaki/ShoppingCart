/**
 * 
 */
package au.com.satoruTour.entity;

/**
 * @author Satoru
 *
 */
public class PromotionRule {

	private Long id;
	
	private String name;
	
	private String ruleAttribute1;
	
	private String ruleAttribute2;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ruleAttribute1
	 */
	public String getRuleAttribute1() {
		return ruleAttribute1;
	}

	/**
	 * @param ruleAttribute1 the ruleAttribute1 to set
	 */
	public void setRuleAttribute1(String ruleAttribute1) {
		this.ruleAttribute1 = ruleAttribute1;
	}

	/**
	 * @return the ruleAttribute2
	 */
	public String getRuleAttribute2() {
		return ruleAttribute2;
	}

	/**
	 * @param ruleAttribute2 the ruleAttribute2 to set
	 */
	public void setRuleAttribute2(String ruleAttribute2) {
		this.ruleAttribute2 = ruleAttribute2;
	}
}
