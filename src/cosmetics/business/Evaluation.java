package cosmetics.business;

/**
 * This class represents a {@link Product}'s {@link Evaluation} assigned by an {@link User}
 */
public class Evaluation {

	private Integer score;
	private User reviewer;
	private Group group;
	private Product product;
	
	/**
	 * Static variable which rule which is the minimum score an {@code Evaluation} can have
	 */
	private static Integer MINIMUM_SCORE = -3;
	
	/**
	 * Static variable which rule which is the maximum score an {@code Evaluation} can have
	 */
	private static Integer MAXIMUM_SCORE = 3;
	
	/**
	 * Evaluation constructor
	 * 
	 * @param reviewer	{@link User} which gave the {@code score} for {@code this}
	 * @param product 	Product which the {@code reviewer} is evaluating
	 */
	public Evaluation(User reviewer, Product product) throws BusinessException {
		this.group = product.getGroup();
		this.product = product;
		this.reviewer = reviewer;
		this.score = null;
		
		product.addEvaluation(this);
		reviewer.addEvaluation(this);
	}
	
	/**
	 * Returns if this {@link Evaluation} has already been evaluated or not
	 * 
	 * @return value representing if this {@link Evaluation} has already been evaluated or not
	 */
	public Boolean isDone() {
		if (this.getScore() == null) {
			return false;
		}

		return true;
	}
	
	/**
	 * Sets the score for this @{link Evaluation}
	 * 
	 * @param score	value to assign to this {@link Evaluation} {@link #score} attribute
	 * @throws BusinessException if {@link #score} is not null, or {@code score} is null or not in the valid range
	 */
	public void setScore(Integer score) throws BusinessException {
		if (this.score != null) {
			throw new BusinessException("This evaluation has already been given an score"); 
		}
		
		if (score == null) {
			throw new BusinessException("The score passed as parameter cannot be null", new NullPointerException()); 
		}
				
		if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE) {
			throw new BusinessException("The score passed as parameter must be in the interval [" + MINIMUM_SCORE + ", " + MAXIMUM_SCORE + "]"); 
		}
		
		this.score = score;
	}

	public Group getGroup() {
		return this.group;
	}

	public Product getProduct() {
		return this.product;
	}

	public User getReviewer() {
		return this.reviewer;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		return "Evaluation \t| User: " + getReviewer().getId() + "\t| Product: " + getProduct().getId() + "\t| Grade: " + (isDone() ? getScore() : "-");
	}
}
