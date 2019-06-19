package cosmetics;

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

	public void addEvaluation(Evaluation evaluation) {
		//System.out.println("[Product.addEvaluation]: Vou inserir "+evaluation.getProduct().getName()+" "+evaluation.getReviewer().getName());
		if (evaluation == null) {
			throw new NullPointerException(); // Should be changed to a custom exception
		} 
		
		if (evaluation.getProduct() != this) {
			// throw new CustomException(); // Should be changed to a custom exception
			return;
		} 
		
		if (!evaluation.getReviewer().canEvaluate(this)) {
			// throw new NullPointerException(); // Should be changed to a custom exception
			return;
		}  
		//System.out.println("Consegui");
		this.evaluations.put(evaluation.getReviewer(), evaluation);
		
	}
	
	public void addScore(User user, Integer score) throws Exception {
		if (user == null) {
			throw new Exception(); // Should be changed to a custom exception
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
		return "Product " + id + ": " + name + " | Category: " + category + " | Requester: " + requester + " | Group: " + group.getName();
	}
	
	public String toStringWithGrade() {
		return "Product " + id + ": " + name + " | Grade: " + this.getAverageScore();
	}
}
