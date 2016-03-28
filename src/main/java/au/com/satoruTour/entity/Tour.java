/**
 * 
 */
package au.com.satoruTour.entity;

import java.math.BigDecimal;

/**
 * @author Satoru
 *
 */
public class Tour {

	private String id;

	private String name;
	
	private BigDecimal price;
	
	private Long promotionRuleId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the promotionRuleId
	 */
	public Long getPromotionRuleId() {
		return promotionRuleId;
	}

	/**
	 * @param promotionRuleId the promotionRuleId to set
	 */
	public void setPromotionRuleId(Long promotionRuleId) {
		this.promotionRuleId = promotionRuleId;
	}
}
