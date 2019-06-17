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
	private List<Evaluation> evaluation;
	private boolean allocated = false;

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
		user.addGroup(this);
	}

	public void addEvaluation(Product product, User reviewer) {
		Evaluation eval = new Evaluation(reviewer, product, this);
		reviewer.addEvaluation(eval);
		product.addEvaluation(eval);
		evaluation.add(eval);

	}

	public void allocate(int numMembers) {
		List<Product> products = getOrderedProducts();
		for (Product product : products) {
			evaluation = new ArrayList<Evaluation>();
			List<User> reviewers = getOrderedCandidateReviewers(product);
			int n = Math.min(reviewers.size(), numMembers);
			for (int i = 0; i < n; i++) {
				this.addEvaluation(product, reviewers.get(i));
			}
			evaluations.put(product, evaluation);
		}
		this.allocated = true;
	}

	public boolean EvaluationDone() {
		for(Product product : evaluations.keySet()) { 
			for(Evaluation eval : evaluations.get(product)) {
				if (eval.isDone() == false) {
					return false;
				}
		    } 
		}
		return true;
	}

	public List<Product> getAcceptableProducts() {		
		return products.stream().filter(product -> product.isAcceptable() == true).collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Product> getNotAcceptableProducts() {
		return products.stream().filter(product -> product.isAcceptable() == false).collect(Collectors.toCollection(ArrayList::new));
	}

	private List<User> getOrderedCandidateReviewers(Product product) {
		List<User> u = members.stream().filter(user -> user.canEvaluate(product) == true).distinct().collect(Collectors.toCollection(ArrayList::new));
		return u.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toCollection(ArrayList::new));
	}

	private List<Product> getOrderedProducts() {
		return products.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toCollection(ArrayList::new));
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

	public boolean testAllocate() {
		List<Product> products = getOrderedProducts();
		products.forEach(p -> System.out.println(p.getName()));
		
		System.out.println("\n");
		for (Product p : evaluations.keySet()) {
			System.out.println("Produto: " + p.getName());
			for (Evaluation e : evaluations.get(p)) {
				System.out.println(e.getReviewer().getName());
			}
		}
		if (this.EvaluationDone() == true) {
			List<Product> acceptable = getAcceptableProducts();
			List<Product> notacceptable = getNotAcceptableProducts();
			System.out.println("Produtos aceitos: \n");
			acceptable.forEach(acc-> System.out.println(acc.getName()));
			System.out.println("Produtos não aceitos: \n");
			notacceptable.forEach(na-> System.out.println(na.getName()));
		}
		else {
			System.out.println("\nNotas Faltando");
		}
		return true;
	}
}
