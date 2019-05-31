package cosmetics;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class User {
	
	private int id;
	private String name;
	private String state;
	private List<ProductCategory> categories;
	private Map<EvaluationGroup,List<Evaluation>> evaluations;
	private List<EvaluationGroup> groups;
	
	public User(int id, String name, String state,List<ProductCategory> categories) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.categories = categories;
		this.evaluations = new HashMap<>();
		this.groups = new ArrayList<EvaluationGroup>();
	}
	
	public void addEvaluation(Evaluation evaluation){

		if (evaluation == null){
			throw new NullPointerException("Avaliacao null");
		}
		else if (evaluation.getReviewer() != this){
			//System.out.println("Usuarios incompativeis");
		}
		else if (!this.canEvaluate(evaluation.getProduct())){
			//System.out.println("Usuario e produto incompativeis");
		}
		else if (!this.groups.contains(evaluation.getGroup())){
			//System.out.println("Usuario e grupo incompativeis");
		}
		else {
			this.evaluations.get(evaluation.getGroup()).add(evaluation);
		}

	}
	
	public boolean canEvaluate(Product product){
		if (product == null) {
			return false;
		}
		if (product.getProductCategory() == null){
			return false;
		}
		if (this.categories.contains(product.getProductCategory())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addEvaluationGroup(EvaluationGroup group){
		if (!this.groups.contains(group) && group != null) {
			int old_size = this.evaluations.size();
			
			this.evaluations.put(group,new ArrayList<>());
			this.groups.add(group);
			if(group != null) {
				group.getMembers().add(this);
				assert group.getMembers().contains(this);
			}
			
			assert(this.evaluations.size() > old_size);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getState() {
		return this.state;
	}
	
	public List<Evaluation> getEvaluations(EvaluationGroup group){
		return this.evaluations.get(group);
	}
	
	public List<EvaluationGroup> getGroups(){
		return this.groups;
	}
	
}
