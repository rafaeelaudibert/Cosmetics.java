package cosmetics.business;

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

	private void addEvaluation(Product product, User reviewer) throws BusinessException {		
		Evaluation evaluation = new Evaluation(reviewer, product); // Already configures the evaluations to the Product and the User
		evaluations.get(product).add(evaluation);
	}

	public void allocate(Integer numMembers) throws BusinessException {
		// If the group is already allocated, throw an exception
		if (this.isAllocated()) {
			throw new BusinessException("This group has already been allocated");
		}
		
		// For each product, allocates
		for (Product product : getOrderedProducts()) {

			List<User> reviewers = getOrderedCandidateReviewers(product);

			evaluations.put(product, new ArrayList<Evaluation>());
			
			for (Integer i = 0; i < Math.min(reviewers.size(), numMembers); i++) {
				this.addEvaluation(product, reviewers.get(i));
				System.out.println("[INFO] Product with ID " + product.getId() + " allocated to user with ID " + reviewers.get(i).getId());
			}
		}
		
		// Mark the group as allocated
		this.setAllocated();
	}

	public boolean evaluationDone() {	
		if (!isAllocated()) {
			return false;
		}
		
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

	private List<User> getOrderedCandidateReviewers(Product product) {
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

	public void addMember(User newMember) {
			this.members.add(newMember);
	}
	
	public void addProduct(Product product) {
			this.products.add(product);
	}

	public boolean isAllocated() {
		return this.allocated;
	}
	
	public void setAllocated() {
		this.allocated = true;
	}

	public List<User> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.name;
	}

	public Map<Product,List<Evaluation>> getEvaluations() {
		return this.evaluations;
	}
	
	@Override
	public String toString() {
		return "Group " + name + "\t| Allocated: " + this.isAllocated() + "\t| Evaluations completed: " + this.evaluationDone();
	}
}
