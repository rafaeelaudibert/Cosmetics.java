package cosmetics;

import java.util.HashMap;
import java.util.Map;

public class Product {

	private int id;
	private String name;
	private ProductCategory category;
	private Map<User, Evaluation> evaluations;

	public Product(int id, String name, ProductCategory category) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.evaluations = new HashMap<>();
	}

	public void addEvaluation(Evaluation evaluation) {

	}

	public void addScore(User user, Integer score) {

	}

	public double getScoreAvg() {
		return 0;
	}

	public boolean isAcceptable() {
		return true;
	}

	public boolean evaluationDone() {
		return true;
	}

	public ProductCategory getProductCategory() {
		return this.category;
	}
}
