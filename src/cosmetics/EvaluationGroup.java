package cosmetics;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EvaluationGroup {

	private String name;
	private List<String> products;
	private List<User> members;
	private Map<Product,List<Evaluation>> evaluations;
	
	public EvaluationGroup(String name,List<String> products,List<User> members) {
		this.name = name;
		this.products = products;
		this.members = members;
		this.evaluations = new HashMap<>();
	}
	
	private void addEvaluation(Product product, User reviewer) {
		
	}
	
	public void allocate(int NumMembers) {
		
	}
	
	public boolean EvaluationDone() {
		return true;
	}
	
	public List<Product> getAcceptableProducts(){
		return null;
	}
	
	public List<Product> getNotAcceptableProducts(){
		return null;
	}
	
	private List<User> getOrderedCandidateReviewers(Product product){
		return null;
	}
	
	private List<Product> getOrderedProducts(){
		return null;
	}
	
	public boolean isAllocated() {
		return true;
	}
	
}

