package cosmetics;

public class Evaluation {

	private Integer score;
	private User reviewer;
	private Product product;
	private Group group;

	public Evaluation(User reviewer, Product product, Group group) {
		this.group = group;
		this.product = product;
		this.reviewer = reviewer;
		this.score = null;
	}

	public boolean isDone() {
		return true;
	}

	public void setScore(Integer score) throws Exception {
		if (this.score == null) {
			this.score = score;
		} else {
			throw new Exception(); // Trocar por uma excess�o espec�fica depois
		}
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
