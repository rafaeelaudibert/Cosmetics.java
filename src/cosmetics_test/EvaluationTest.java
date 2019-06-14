package cosmetics_test;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluationTest {

	User userJoao;
	Category cream;
	ArrayList categoryListCream;
	Product creamProduct;
	Group groupA;
	List<Product> ListProductEmpty, ListProductCream;
	
	
	@Before
	public void setUp() {
		// Definindo categorias para teste
		cream = new Category("creme");
		categoryListCream = new ArrayList<Category>();
		categoryListCream.add(cream);
		
		userJoao = new User(01, "Joao", "RS", categoryListCream);
		
		creamProduct = new Product(01, "Creme X", userJoao, cream);
	
		groupA = new Group("Grupo A", productListCream, groupAMembers);
		
		evaluation1 = new Evaluation(userJoao, creamProduct, GroupA);			
	}
	

	
	@Test
	public void isDoneTest() {
		Evaluation evaluation1;
		evaluation1 = new Evaluation(userJoao, creamProduct, GroupA);
		evaluation1.setScore(1);
		AssertTrue(evaluation1.isDone());
	}
	
	@Test
	public void isDoneTest() {
		Evaluation evaluation2;
		evaluation2 = new Evaluation(userJoao, creamProduct, GroupA);
		AssertFalse(evaluation2.isDone());
	}

	@Test
	 public void setScoreTest() {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, GroupA);
		evaluation.setScore(-5);
		AssertTrue(evaluation.getScore() == null); 
	}
	
	@Test
	 public void setScoreTest() {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, GroupA);
		evaluation.setScore(-3);
		AssertTrue(evaluation.getScore() == -3);
	}
	
	@Test
	 public void setScoreTest() {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, GroupA);
		evaluation.setScore(3);
		AssertTrue(evaluation.getScore() == 3);
	}
	
	@Test
	 public void setScoreTest() {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, GroupA);
		evaluation.setScore(0);
		evaluation.setScore(-5);
		AssertTrue(evaluation.getScore() == 0);  
	}
	
}


