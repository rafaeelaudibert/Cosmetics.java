package cosmetics.business;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class User {

	private Integer id;
	private String name;
	private String state;
	private List<Category> categories;
	private Map<Group, List<Evaluation>> evaluations;
	private List<Group> groups;

	public User(Integer id, String name, String state, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.categories = categories;
		this.evaluations = new HashMap<>();
		this.groups = new ArrayList<Group>();
	}

	public void addEvaluation(Evaluation evaluation) throws BusinessException {
		if (evaluation == null) {
			throw new BusinessException("Evaluation passed as parameter cannot be null", new NullPointerException());
		} else if (evaluation.getReviewer() != this) {
			throw new BusinessException("Evaluation does not belong to this user");
		} else if (!this.canEvaluate(evaluation.getProduct())) {
			throw new BusinessException("This user cannot evaluate this product");
		} else if (!this.groups.contains(evaluation.getGroup())) {
			throw new BusinessException("This user does not belong the group of this evaluation");
		}
		
		this.evaluations.get(evaluation.getGroup()).add(evaluation);		
	}

	public boolean canEvaluate(Product product) {		
		if (product == null) {
			return false;
		}
		
		if (!this.categories.contains(product.getCategory())) {
			return false;
		}
			
		if (this.state == product.getRequester().getState()) {
			return false;
		}
		
		return true;
	}

	public void addGroup(Group group) throws BusinessException {
		
		if (group == null) {
			throw new BusinessException("The group passed as parameter cannot be null");
		}
		
		if (!group.getMembers().contains(this)) {
			throw new BusinessException("This member does not belong to the group passed as parameter");
		}
		
		if (!this.groups.contains(group)) {						
			this.evaluations.put(group, new ArrayList<>());
			this.groups.add(group);
		}
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public String getState() {
		return this.state;
	}
	
	public List<Evaluation> getEvaluationsFromGroup(Group group) {
		return this.evaluations.get(group);
	}

	public List<Group> getGroups() {
		return this.groups;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
