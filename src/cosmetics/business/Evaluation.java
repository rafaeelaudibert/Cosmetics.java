package cosmetics.business;

public class Evaluation {

	private Integer score;
	private User reviewer;
	private Group group;
	private Product product;
	
	private static Integer MINIMUM_SCORE = -3;
	private static Integer MAXIMUM_SCORE = 3;

	public Evaluation(User reviewer, Product product) throws BusinessException {
		this.group = product.getGroup();
		this.product = product;
		this.reviewer = reviewer;
		this.score = null;
		
		product.addEvaluation(this);
		reviewer.addEvaluation(this);
	}

	public Boolean isDone() {
		if (this.getScore() == null) {
			return false;
		}

		return true;
	}

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
