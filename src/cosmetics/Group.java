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
		Evaluation evaluation = new Evaluation(reviewer, product);
		
		evaluations.get(product).add(evaluation);
		try {
			reviewer.addEvaluation(evaluation);
			product.addEvaluation(evaluation);
		}catch(Exception e) {
			System.out.println("Erro adicionando a evaluation ao usuário (Possivelmente incompatíveis)");
		}
	}

	public void allocate(int numMembers) throws Exception {
		
		// If the group is already allocated, throw an exception
		if (this.isAllocated())
			throw new Exception();
		
		// Para cada produto, realiza a alocaÃ§Ã£o
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
		if (!isAllocated()) {
			return false;
		}
		return evaluations.values()
				.parallelStream()
				.map(evaluationList -> evaluationList.stream().allMatch(Evaluation::isDone))
				.allMatch(Boolean::valueOf);
		}

	public List<Product> getAcceptableProducts() {
//		return products.parallelStream()
//				.filter(product -> product.isAcceptable())
//				.collect(Collectors.toCollection(ArrayList::new));
		List<Product> acceptable = new ArrayList<Product>();
		for(Product product: this.products) {
			if(product.isAcceptable()) {
				acceptable.add(product);
			}
		}
		return acceptable;
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
//		return members.parallelStream()
//				.filter(user -> user.canEvaluate(product))
//				.distinct()
//				.sorted(Comparator.comparing(User::getId))
//				.collect(Collectors.toCollection(ArrayList::new));
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

	public void addMember(User newMember) {
			this.members.add(newMember);
	}
	
	public void addProduct(Product product) {
			this.products.add(product);
	}

	public Map<Product,List<Evaluation>>getEvaluations() {
		return this.evaluations;
	}
}
