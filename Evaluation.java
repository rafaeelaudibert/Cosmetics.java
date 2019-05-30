package domain;

public class Evaluation {

	private Integer score;
	private User reviewer;
	private Product product;
	private EvaluationGroup group;
	
	public Evaluation(EvaluationGroup group,Product product,User reviewer) {
		this.group = group;
		this.product = product;
		this.reviewer = reviewer;
		this.score = null;
	}
	
	public boolean isDone() {
		return true;
	}
	
	public void setScore(Integer score) throws Exception{
		if (this.score == null){
			this.score = score;
		}
		else {
			throw new Exception();	//Trocar por uma excessão específica depois
		}
	}
	
	public EvaluationGroup getGroup() {
		return this.group;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
}
