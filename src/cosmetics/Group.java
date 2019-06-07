package cosmetics;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

public class Group {

	private String name;
	private List<Product> products;
	private List<User> members;
	private Map<Product, List<Evaluation>> evaluations;
	private boolean allocated;
	
	public Group(String name) {
		new Group(name, new ArrayList<Product>(), new ArrayList<User>(), allocated);
	}
	
	public Group(String name, List<Product> products, List<User> members, boolean allocated) {
		this.name = name;
		this.products = products;
		this.members = members;
		this.evaluations = new HashMap<>();
		this.allocated = allocated;
	}
	
	public void addMember(User user) {
		this.members.add(user);
	}

	public void addEvaluation(Product product, User reviewer) {
		
	}

	public void allocate(int NumMembers) {
		int i = 1;
		List <Product> orderedproducts = getOrderedProducts();
			while (i < NumMembers) {
				while (orderedproducts.isEmpty()) {
					
				}
			}
		

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
		return products.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList());
	}

	public boolean isAllocated() {
		return this.allocated;
	}

	public List<User> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.name;
	}
}
