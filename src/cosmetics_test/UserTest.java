package cosmetics_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cosmetics.*;

public class UserTest {

	User userJoao, userJose, userPaulo, userMateus;
	List<User> groupAMembers, groupBMembers;
	Category cream, lotion, shampoo;
	List<Category> categoryListEmpty, categoryListCream, categoryListCreamShampoo;
	Product creamProduct, lotionProduct, shampooProduct;
	List<Product> productListEmpty, productListShampoo, productListCreamLotion;
	Group groupA, groupB;
	Evaluation evalJoaoCreamProduct, evalMateusCreamProduct, evalJoseShampooProduct;
	Evaluation evalJoaoCreamProductWrongGroup, evalJoaoShampooProduct;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		// Definindo categorias para teste
		cream = new Category("creme");
		lotion = new Category("locao");
		shampoo = new Category("shampoo");

		// Definindo listas de categorias para teste
		categoryListEmpty = new ArrayList<Category>();
		categoryListCream = new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListCream.add(cream);
		categoryListCreamShampoo = new ArrayList<Category>();
		categoryListCreamShampoo.add(cream);
		categoryListCreamShampoo.add(shampoo);
		
		// Definindo usu�rios para teste
		userJoao = new User(01, "Joao", "RS", categoryListCream);
		userMateus = new User(02, "Mateus", "BA", categoryListCream);
		userJose = new User(03, "Jose", "RS", categoryListCreamShampoo);
		userPaulo = new User(04, "Paulo", "MG", categoryListEmpty);

		// Definindo produtos para teste
		creamProduct = new Product(01, "Creme X", userPaulo, cream);
		lotionProduct = new Product(02, "Locao Y", userJose, lotion);
		shampooProduct = new Product(03, "Shampoo Z", userJoao, shampoo);

		// Definindo listas de produtos para teste;
		productListEmpty = new ArrayList<Product>();
		productListShampoo = new ArrayList<Product>();
		productListShampoo.add(shampooProduct);
		productListCreamLotion = new ArrayList<Product>();
		productListCreamLotion.add(creamProduct);
		productListCreamLotion.add(lotionProduct);

		// Definindo listas de usu�rios para teste
		groupAMembers = new ArrayList<User>();
		groupAMembers.add(userJose);
		groupAMembers.add(userPaulo);
		groupBMembers = new ArrayList<User>();
		groupBMembers.add(userJoao);
		groupBMembers.add(userMateus);

		// Definindo os grupos de usuarios
		groupA = new Group("Grupo A", productListShampoo, groupAMembers);
		groupB = new Group("Grupo B", productListCreamLotion, groupBMembers);
		
		// Adicionando usuários aos grupos
		userJoao.addGroup(groupB);
		userMateus.addGroup(groupB);
		userJose.addGroup(groupA);
		userPaulo.addGroup(groupA);

		// Definindo novas avalia��es e avalia��es concluidas;
		evalJoaoCreamProduct = new Evaluation(groupB, creamProduct, userJoao);
		evalMateusCreamProduct = new Evaluation(groupB, creamProduct, userMateus);
		evalJoseShampooProduct = new Evaluation(groupA, shampooProduct, userJose);
		evalJoaoCreamProductWrongGroup = new Evaluation(groupA, creamProduct, userJoao);
		evalJoaoShampooProduct = new Evaluation(groupB, shampooProduct, userJoao);
	}

	// Testando canEvaluate
	@Test
	public void canEvaluateTestInList() {
		assertTrue(userJoao.canEvaluate(creamProduct));
	}

	@Test
	public void canEvaluateTestNotInList() {
		assertFalse(userJose.canEvaluate(lotionProduct));
	}

	@Test
	public void canEvaluateTestNullProduct() {
		assertFalse(userJose.canEvaluate(null));
	}
	
	@Test
	public void canEvaluateTestSameState() {
		assertFalse(userJose.canEvaluate(shampooProduct));
	}

	@Test
	public void canEvaluateTestEmptyList() {
		assertFalse(userPaulo.canEvaluate(shampooProduct));
	}

	// Testando addEvaluationGroup
	@Test
	public void addEvaluationGroupTestNormalAddition() {
		userJoao.addGroup(groupA);
		assertTrue(userJoao.getGroups().contains(groupA));
	}

	@Test
	public void addEvaluationGroupTestNullAddition() {
		userJoao.addGroup(null);
		assertFalse(userJoao.getGroups().contains(null));
	}

	@Test
	public void addEvaluationGroupTestRepeatAddition() {
		userJoao.addGroup(groupB);
		assertTrue(userJoao.getGroups().contains(groupB));
	}

	// Testando addEvaluation
	@Test
	public void addEvaluationTestNull() {
		thrown.expect(NullPointerException.class);
		// thrown.expectMessage("expected messages");
		userJoao.addEvaluation(null);
	}

	@Test
	public void addEvaluationTestWrongUser() {
		userJoao.addEvaluation(evalMateusCreamProduct);
		assertFalse(userJoao.getEvaluations(evalMateusCreamProduct.getGroup()).contains(evalMateusCreamProduct));
	}

	@Test
	public void addEvaluationTestIncompatibleProduct() {
		userJoao.addEvaluation(evalJoaoShampooProduct);
		assertFalse(userJoao.getEvaluations(evalJoaoShampooProduct.getGroup()).contains(evalJoaoShampooProduct));
	}

	@Test
	public void addEvaluationTestNotExistingGroup() {
		userJoao.addEvaluation(evalJoaoCreamProductWrongGroup);
		assertTrue(userJoao.getEvaluations(evalJoaoCreamProductWrongGroup.getGroup()) == null);
	}

	@Test
	public void addEvaluationTestNormalAddition() {
		userJoao.addEvaluation(evalJoaoCreamProduct);
		assertTrue(userJoao.getEvaluations(evalJoaoCreamProduct.getGroup()).contains(evalJoaoCreamProduct));
	}
}
