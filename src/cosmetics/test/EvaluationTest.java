package cosmetics.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetics.business.Category;
import cosmetics.business.Evaluation;
import cosmetics.business.Group;
import cosmetics.business.Product;
import cosmetics.business.User;

public class EvaluationTest {

	User userJoao,userJose;
	Category cream;
	List<Category> categoryListCream;
	Product creamProduct;
	List<User> groupAMembers;
	Group groupA;
	List<Product> ListProductEmpty, ListProductCream;
	
	
	@Before
	public void setUp() throws Exception {
		// Definindo categorias para teste
		cream = new Category("creme");
		categoryListCream = new ArrayList<Category>();
		categoryListCream.add(cream);
		
		// Definindo Grupo
		groupA = new Group("Grupo A");
		
		// Definindo Usuario
		userJose = new User(02,"Jose", "CE", categoryListCream);
		userJoao = new User(01, "Joao", "RS", categoryListCream);
		
		// Definindo Produto
		creamProduct = new Product(01, "Creme X", userJose, cream, groupA);
		
		groupA.addProduct(creamProduct);
		groupA.addMember(userJoao);	
		userJoao.addGroup(groupA);
	}
	

	
	@Test
	public void isDoneTrueTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(1);
		assertTrue(evaluation.isDone());
	}
	
	@Test
	public void isDoneFalseTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		assertFalse(evaluation.isDone());
	}
	
	@Test(expected=Exception.class)
	 public void setScoreSmallerValueTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(-5);
	}
	
	@Test
	 public void setScoreLowLimitTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(-3);
		assertTrue(evaluation.getScore() == -3);
	}
	
	@Test
	 public void setScoreHighLimitTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(3);
		assertTrue(evaluation.getScore() == 3);
	}
	
	@Test(expected=Exception.class)
	 public void setScoreAfterSetTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(0);
		evaluation.setScore(-5);
	}
	
	@Test(expected=Exception.class)
	 public void setScoreNullTest() throws Exception {
		Evaluation evaluation;
		evaluation = new Evaluation(userJoao, creamProduct);
		evaluation.setScore(null);
	}
	
}


