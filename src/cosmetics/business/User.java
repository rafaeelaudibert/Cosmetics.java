package cosmetics.business;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class User {

	private int id;
	private String name;
	private String state;
	private List<Category> categories;
	private Map<Group, List<Evaluation>> evaluations;
	private List<Group> groups;

	public User(int id, String name, String state, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.categories = categories;
		this.evaluations = new HashMap<>();
		this.groups = new ArrayList<Group>();
	}

	public void addEvaluation(Evaluation evaluation) throws Exception {
		if (evaluation == null) {
			throw new NullPointerException("Avaliacao null");
		} else if (evaluation.getReviewer() != this) {
			throw new Exception();
		} else if (!this.canEvaluate(evaluation.getProduct())) {
			throw new Exception();
		} else if (!this.groups.contains(evaluation.getGroup())) {
			throw new Exception();
		} else {
			this.evaluations.get(evaluation.getGroup()).add(evaluation);
			
		}

	}

	public boolean canEvaluate(Product product) {
		if (product == null) {
			return false;
		}

		if (!(this.categories.contains(product.getCategory()))) {
		//	System.out.println("Não posso avaliar, ele não está nas minhas categorias");
			return false;
		}
		
		if (this.state == product.getRequester().getState()) {
		//	System.out.println("Não posso avaliar, sou do mesmo estado que o requester");
			return false;
		}
		
		return true;
	}

	public void addGroup(Group group) throws Exception {
		
		if (group == null)
			throw new NullPointerException();
		
		if (!group.getMembers().contains(this))
			throw new Exception();
		
		if (!this.groups.contains(group)) {
			int old_size = this.evaluations.size();
						
			this.evaluations.put(group, new ArrayList<>());
			this.groups.add(group);		
			
			assert (this.evaluations.size() > old_size);
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
