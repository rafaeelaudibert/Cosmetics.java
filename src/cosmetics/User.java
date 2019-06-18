package cosmetics;

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

	public void addEvaluation(Evaluation evaluation) {

		if (evaluation == null) {
			throw new NullPointerException("Avaliacao null");
		} else if (evaluation.getReviewer() != this) {
			// System.out.println("Usuarios incompativeis");
		} else if (!this.canEvaluate(evaluation.getProduct())) {
			// System.out.println("Usuario e produto incompativeis");
		} else if (!this.groups.contains(evaluation.getGroup())) {
			// System.out.println("Usuario e grupo incompativeis");
		} else {
			this.evaluations.get(evaluation.getGroup()).add(evaluation);
		}

	}

	public boolean canEvaluate(Product product) {
		if (product == null) {
			return false;
		}
		
		if (product.getCategory() == null) {
			return false;
		}
		
		if (!this.categories.contains(product.getCategory())) {
			return false;
		}
		
		if (this.state == product.getRequester().getState()) {
			return false;
		}
		
		if (this.getEvaluationsFromGroup(product.getGroup()).stream().anyMatch((Evaluation evaluation) -> evaluation.getProduct() == product)) {
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
