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
	public void addEvaluation(Evaluation evaluation) throws Exception{

		if (evaluation == null){
		  throw new Exception("Evaluation � null");
		}
	
		if (!this.canEvaluate(evaluation.getProduct())){
		  throw new Exception("Produto n�o pode ser avaliado por este avaliador");
		}
		
		if (this.evaluations.get(evaluation.getGroup()) == null){
		  throw new Exception("Avaliador n�o pertence a um grupo onde este produto esteja sendo avaliado!");
		}
	
		this.evaluations.get(evaluation.getGroup()).add(evaluation);
		
	}
	
	public boolean canEvaluate(Product product){
		if (product == null) {
			return false;
		}
		if (this.categories.contains(product.getProductCategory())){
			return true;
		}
		else {
			return false;
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
	
	public List<EvaluationGroup> getGroups(){
		return this.groups;
	}
	
}
