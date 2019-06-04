package cosmetics;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Group {

	private String name;
	private List<Product> products;
	private List<User> members;
	private Map<Product, List<Evaluation>> evaluations;
	
	public Group(String name) {
		new Group(name, new ArrayList<Product>(), new ArrayList<User>());
	}
	
	public Group(String name, List<Product> products, List<User> members) {
		this.name = name;
		this.products = products;
		this.members = members;
		this.evaluations = new HashMap<>();
	}
	
	public void addMember(User user) {
		this.members.add(user);
	}

	public void addEvaluation(Product product, User reviewer) {

	}

	public void allocate(int NumMembers) {

	}

	public boolean EvaluationDone() {
		return true;
	}

	public List<Product> getAcceptableProducts() {
		return null;
	}

	public List<Product> getNotAcceptableProducts() {
		return null;
	}

	private List<User> getOrderedCandidateReviewers(Product product) {
		return null;
	}

	private List<Product> getOrderedProducts() {
		return null;
	}

	public boolean isAllocated() {
		return true;
	}

	public List<User> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.name;
	}
}
