package cosmetics.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product {

	private int id;
	private String name;
	private Category category;
	private User requester;
	private Group group;
	private Map<User, Evaluation> evaluations;
	private static int ACCEPTABLE_GRADE = 0;

	public Product(int id, String name, User requester, Category category, Group group) {
		this.id = id;
		this.name = name;
		this.requester = requester;
		this.category = category;
		this.group = group;
		this.evaluations = new HashMap<>();
	}

	public void addEvaluation(Evaluation evaluation) throws BusinessException {
		if (evaluation == null) {
			throw new BusinessException("Evaluation passed as parameter cannot be null", new NullPointerException());
		} 
		
		if (evaluation.getProduct() != this) {
			throw new BusinessException("This product is not related to the evaluation passed as parameter"); 
		} 
		
		if (!evaluation.getReviewer().canEvaluate(this)) {
			throw new BusinessException("The evaluation's passed as parameter reviewer cannot evaluate this product");
		}  
		
		this.evaluations.put(evaluation.getReviewer(), evaluation);
		
	}
	
	public void addScore(User user, Integer score) throws BusinessException {
		if (user == null) {
			throw new BusinessException("The user passed as parameter cannot be null");
		}
		
		evaluations.get(user).setScore(score);
	}

	public Double getAverageScore() {
		return evaluations.values()
				.parallelStream()
				.filter((Evaluation e) -> e.getScore() != null)
				.mapToDouble(Evaluation::getScore)
				.average()
				.orElse(Double.NaN);
	}

	public boolean isAcceptable() {
		return getAverageScore() >= ACCEPTABLE_GRADE;
	}
	
	public ArrayList<Evaluation> getEvaluations() {
		return new ArrayList<Evaluation>(evaluations.values());
	}
	
	public Evaluation getEvaluationFromUser(User user) {
		return evaluations.get(user);
	}

	public Category getCategory() {
		return this.category;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public User getRequester() {
		return this.requester;
	}
	
	public Group getGroup() {
		return this.group;
	}
	
	@Override
	public String toString() {
		return "Product " + id + ": " + name + "\t| Category: " + category + "\t| Requester: " + requester + "\t| Group: " + group.getName();
	}
	
	public String toStringWithGrade() {
		return "Product " + id + ": " + name + "\t| Grade: " + this.getAverageScore();
	}
}
