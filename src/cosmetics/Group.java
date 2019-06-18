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
	private boolean allocated = false;

	public Group(String name) {
		this(name, new ArrayList<Product>(), new ArrayList<User>());
	}

	public Group(String name, List<Product> products, List<User> members) {
		this.name = name;
		this.products = products;
		this.members = members;
		this.evaluations = new HashMap<>();
		
		for(User member : members) {
			try {
				member.addGroup(this);
			} catch(Exception e) {
				// Pass, because the error is only raised if the members are
				// not on this group. But we set it in the lines above, so this
				// exception never occurs
			}
		}
	}

	private void addEvaluation(Product product, User reviewer) {
		Evaluation evaluation = new Evaluation(reviewer, product); // Already configures the evaluations to the Product and the User
		evaluations.get(product).add(evaluation);
	}

	public void allocate(int numMembers) throws Exception {
		
		// If the group is already allocated, throw an exception
		if (this.isAllocated())
			throw new Exception();
		
		// Para cada produto, realiza a alocação
		for (Product product : getOrderedProducts()) {
			List<User> reviewers = getOrderedCandidateReviewers(product);
			evaluations.put(product, new ArrayList<Evaluation>());
			
			for (int i = 0; i < Math.min(reviewers.size(), numMembers); i++) {
				this.addEvaluation(product, reviewers.get(i));
			}
		}
		
		// Mark the group as allocated
		this.allocated = true;
	}

	public boolean evaluationDone() {		
		return evaluations.values()
				.parallelStream()
				.map(evaluationList -> evaluationList.stream().allMatch(Evaluation::isDone))
				.allMatch(Boolean::valueOf);
	}

	public List<Product> getAcceptableProducts() {
		return products.parallelStream()
				.filter(product -> product.isAcceptable())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Product> getNotAcceptableProducts() {
		return products.parallelStream()
				.filter(product -> !product.isAcceptable())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<User> getOrderedCandidateReviewers(Product product) {
		return members.parallelStream()
				.filter(user -> user.canEvaluate(product))
				.distinct()
				.sorted(Comparator.comparing((User user) -> user.getEvaluationsFromGroup(this).size())
						.thenComparing(User::getId))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private List<Product> getOrderedProducts() {
		return products.parallelStream()
				.sorted(Comparator.comparing(Product::getId))
				.collect(Collectors.toCollection(ArrayList::new));
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
		if (this.evaluationDone() == true) {
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

	public void addMember(User newMember) throws Exception {
			this.members.add(newMember);			
			newMember.addGroup(this); // Throws exception, but shouldn't throw in this case so we don't handle it
	}
	
	public void addProduct(Product product) {
			this.products.add(product);
	}
}
