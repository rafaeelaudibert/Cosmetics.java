package cosmetics.business;

public class Evaluation {

	private Integer score;
	private User reviewer;
	private Group group;
	private Product product;
	
	private static Integer MINIMUM_SCORE = -3;
	private static Integer MAXIMUM_SCORE = 3;

	public Evaluation(User reviewer, Product product) throws Exception {
		this.group = product.getGroup();
		this.product = product;
		this.reviewer = reviewer;
		this.score = null;
		
		product.addEvaluation(this);
		reviewer.addEvaluation(this);
	}

	public boolean isDone() {
		if (this.getScore() == null) {
			return false;
		}

		return true;
	}

	public void setScore(Integer score) throws Exception {
		if (this.score != null) {
			throw new Exception(); 
		}
		
		if (score == null) {
			throw new Exception(); 
		}
				
		if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE) {
			throw new Exception(); 
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
}
