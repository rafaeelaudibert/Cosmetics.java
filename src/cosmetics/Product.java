package cosmetics;

import java.util.HashMap;
import java.util.Map;

public class Product {

	private int id;
	private String name;
	private Category category;
	private User requester;
	private Map<User, Evaluation> evaluations;
	private static int ACCEPTABLE_GRADE = 0;

	public Product(int id, String name, User requester, Category category) {
		this.id = id;
		this.name = name;
		this.requester = requester;
		this.category = category;
		this.evaluations = new HashMap<>();
	}

	public void addEvaluation(Evaluation evaluation) {

	}

	public void addScore(User user, Integer score) {

	}

	public double getAverageScore() {
		return 0;
	}

	public boolean isAcceptable() {
		return getAverageScore() > ACCEPTABLE_GRADE;
	}

	public boolean evaluationDone() {
		return true;
	}

	public Category getCategory() {
		return this.category;
	}

	public Integer getId() {
		return this.id;
	}

	public User getRequester() {
		return this.requester;
	}
}
