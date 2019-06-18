package cosmetics_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.Category;
import cosmetics.Evaluation;
import cosmetics.Group;
import cosmetics.Product;
import cosmetics.User;

public class EvaluationTest {

	User userJoao;
	Category cream;
	List<Category> categoryListCream;
	Product creamProduct;
	List<User> groupAMembers;
	Group groupA;
	List<Product> ListProductEmpty, ListProductCream;
	
	
	@Before
	public void setUp() {
		// Definindo categorias para teste
		cream = new Category("creme");
		categoryListCream = new ArrayList<Category>();
		categoryListCream.add(cream);
		
		// Definindo Grupo
		groupA = new Group("Grupo A");
		
		// Definindo Usuario
		userJoao = new User(01, "Joao", "RS", categoryListCream);
		
		// Definindo Produto
		creamProduct = new Product(01, "Creme X", userJoao, cream, groupA);
		
		groupA.addProduct(creamProduct);
		groupA.addMember(userJoao);
	
	}
	

	
	@Test
	public void isDoneTrueTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(1);
		assertTrue(evaluation.isDone());
	}
	
	@Test
	public void isDoneFalseTest() {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		assertFalse(evaluation.isDone());
	}
	
	@Test(expected=Exception.class)
	 public void setScoreSmallerValueTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(-5);
	}
	
	@Test
	 public void setScoreLowLimitTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(-3);
		assertTrue(evaluation.getScore() == -3);
	}
	
	@Test
	 public void setScoreHighLimitTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(3);
		assertTrue(evaluation.getScore() == 3);
	}
	
	@Test(expected=Exception.class)
	 public void setScoreAfterSetTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(0);
		evaluation.setScore(-5);
	}
	
	@Test(expected=Exception.class)
	 public void setScoreNullTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct, groupA);
		evaluation.setScore(null);
	}
	
}


