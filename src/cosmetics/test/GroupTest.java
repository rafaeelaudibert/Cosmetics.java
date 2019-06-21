package cosmetics.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cosmetics.business.BusinessException;
import cosmetics.business.Category;
import cosmetics.business.Evaluation;
import cosmetics.business.Group;
import cosmetics.business.Product;
import cosmetics.business.User;

public class GroupTest {
	User userJoao, userAna, userManoela, userJoana, userMiguel, userBeatriz, userSuzana, userNatasha, userPedro, userCarla;
	List<User> spfAMembers, spfBMembers, spfCMembers;
	Category ddCream, ccCream, bbCream, powderSunscreen, foundationSPF, oilFreeMatteSPF;
	List<Category> categoryListEmpty, categoryListUser;
	Product lorealDDCream;
	Product avonCCCream, revolutionPowderSunscreen, maybellineBBCream, revlonFoundationSPF20, niveaMatteFaceSPF, laRocheCCCream, yvesRocherPowderSPF15, niveaBBCream, baseOBoticarioSPF20, naturaSPF20RostoMatte;
	Product productNotCovered;
	List<Product> productListEmpty, productListSPFA, productListSPFB, productListSPFC;
	Group spfA, spfB, spfC,groupNEUsers;
	List<Evaluation> evaluation;
	Evaluation evaluation1;
	
	@Before
	public void setUp() throws BusinessException {
		// Definindo categorias para teste
		ddCream 		= new Category("DD Cream");
		ccCream 		= new Category("CC Cream");
		bbCream 		= new Category("BB Cream");
		powderSunscreen = new Category("Powder Sunscreen");
		foundationSPF	= new Category("Foundation+SPF");
		oilFreeMatteSPF	= new Category("Oil Free Matte SPF");
	
		// Definindo listas de Usuários para teste
		categoryListEmpty	= new ArrayList<Category>();
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(bbCream);
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		userJoao 	= new User(1, "Joao", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(foundationSPF);
		userAna 	= new User(2, "Ana", "SP", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(oilFreeMatteSPF);
		categoryListUser.add(bbCream);
		userManoela = new User(3, "Manoela", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(foundationSPF);
		categoryListUser.add(bbCream);
		categoryListUser.add(powderSunscreen);
		userJoana 	= new User(4, "Joana", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(foundationSPF);
		categoryListUser.add(ddCream);
		categoryListUser.add(oilFreeMatteSPF);
		userMiguel 	= new User(5, "Miguel", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(oilFreeMatteSPF);
		categoryListUser.add(ccCream);
		categoryListUser.add(powderSunscreen);
		userBeatriz = new User(6, "Beatriz", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(powderSunscreen);
		userSuzana 	= new User(7, "Suzana", "RS", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(bbCream);
		userNatasha = new User(8, "Natasha", "CE", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(foundationSPF);
		categoryListUser.add(powderSunscreen);
		userPedro 	= new User(9, "Pedro", "SP", categoryListUser);
		categoryListUser 	= new ArrayList<Category>(); // Cria outra lista de categorias
		categoryListUser.add(ccCream);
		categoryListUser.add(ddCream);
		categoryListUser.add(oilFreeMatteSPF);
		userCarla 	= new User(10, "Carla", "SP", categoryListUser);
			
		// Definindo grupos para teste
		spfA = new Group("SPF A");
		spfB = new Group("SPF B");
		spfC = new Group("SPF C");
		groupNEUsers = new Group("Grupo sem usu�rios suficientes");
		
		// Definindo usuarios dos grupos
		// Grupo a
		spfA.addMember(userJoao);
		spfA.addMember(userAna);
		spfA.addMember(userManoela);
		spfA.addMember(userJoana);
		spfA.addMember(userMiguel);
		spfA.addMember(userBeatriz);
		spfA.addMember(userSuzana);
		// Grupo b
		spfB.addMember(userJoao);
		spfB.addMember(userAna);
		spfB.addMember(userManoela);
		spfB.addMember(userJoana);
		spfB.addMember(userMiguel);
		spfB.addMember(userBeatriz);
		spfB.addMember(userSuzana);		
		// Grupo c
		spfC.addMember(userJoana);
		spfC.addMember(userMiguel);
		spfC.addMember(userBeatriz);
		spfC.addMember(userSuzana);
		spfC.addMember(userNatasha);
		spfC.addMember(userPedro);
		spfC.addMember(userCarla);
		
		groupNEUsers.addMember(userJoao);
		groupNEUsers.addMember(userPedro);
		groupNEUsers.addMember(userAna);
		
		// Adicionando usuários aos grupos (não precisamos pegar a exceção pois é garantido disso funcionar)
		userJoao.addGroup(spfA);
		userJoao.addGroup(spfB);
		userJoao.addGroup(groupNEUsers);
		userAna.addGroup(spfA);
		userAna.addGroup(spfB);
		userAna.addGroup(groupNEUsers);
		userManoela.addGroup(spfA);
		userManoela.addGroup(spfB);
		userJoana.addGroup(spfA);
		userJoana.addGroup(spfB);
		userJoana.addGroup(spfC);		
		userMiguel.addGroup(spfA);
		userMiguel.addGroup(spfB);
		userMiguel.addGroup(spfC);		
		userBeatriz.addGroup(spfA);
		userBeatriz.addGroup(spfB);
		userBeatriz.addGroup(spfC);		
		userSuzana.addGroup(spfA);
		userSuzana.addGroup(spfB);
		userSuzana.addGroup(spfC);		
		userNatasha.addGroup(spfC);		
		userPedro.addGroup(spfC);
		userPedro.addGroup(groupNEUsers);
		userCarla.addGroup(spfC);

		// Definindo os produtos
		lorealDDCream 				= new Product(1, "L'oreal DD Cream", userJoao, ddCream,spfC);
		avonCCCream 				= new Product(2, "Avon CC Cream", userBeatriz, ccCream,spfB);
		revolutionPowderSunscreen 	= new Product(3, "Revolution Powder Sunscreen", userSuzana, powderSunscreen,spfB);
		maybellineBBCream 			= new Product(4, "Maybelline BB Cream", userNatasha, bbCream,spfB);
		revlonFoundationSPF20 		= new Product(5, "Revlon Foundation+SPF20", userPedro, foundationSPF,spfB);
		niveaMatteFaceSPF 			= new Product(6, "Nivea Matte Face SPF", userCarla, oilFreeMatteSPF,spfB);
		laRocheCCCream 				= new Product(7, "La Roche CC Cream", userBeatriz, ccCream,spfA);
		yvesRocherPowderSPF15 		= new Product(8, "Yves Rocher Powder+SPF15", userSuzana, powderSunscreen,spfA);
		niveaBBCream 				= new Product(9, "Nivea BB Cream", userNatasha, bbCream, spfA);
		baseOBoticarioSPF20 		= new Product(10, "Base O Boticario SPF20", userPedro, foundationSPF,spfA);
		naturaSPF20RostoMatte 		= new Product(11,"Natura SPF20 Rosto Matte",userCarla,oilFreeMatteSPF,spfA);
		
		productNotCovered			= new Product(12,"Produto Especial",userJoao,ccCream,groupNEUsers);
		
		spfA.addProduct(naturaSPF20RostoMatte);
		spfA.addProduct(baseOBoticarioSPF20);
		spfA.addProduct(niveaBBCream);
		spfA.addProduct(yvesRocherPowderSPF15);
		spfA.addProduct(laRocheCCCream);
		
		spfB.addProduct(niveaMatteFaceSPF);
		spfB.addProduct(revlonFoundationSPF20);
		spfB.addProduct(maybellineBBCream);
		spfB.addProduct(revolutionPowderSunscreen);
		spfB.addProduct(avonCCCream);
		
		spfC.addProduct(lorealDDCream);
		spfC.addProduct(laRocheCCCream);
		
		groupNEUsers.addProduct(productNotCovered);
		
		
	}

	//Testando o m�todo allocate
	@Test
	public void allocateTestNormalAllocationFirstProduct() throws BusinessException {
		//O primeiro produto da lista de produtos � alocado
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(laRocheCCCream).get(0).getReviewer().equals(userJoao));
	}

	@Test
	public void allocateTestNormalAllocationMiddleProduct() throws BusinessException {
		//Produtos internos da lista de produtos s�o alocados
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(yvesRocherPowderSPF15).get(0).getReviewer().equals(userJoana));
	}
	@Test
	public void allocateTestNormalAllocationLastProduct() throws BusinessException {
		//O �tlimo produto da lista de produtos � alocado
		spfA.allocate(2);
		assert(spfA.getEvaluations().get(naturaSPF20RostoMatte).get(1).getReviewer().equals(userMiguel));
	}
	@Test
	public void allocateTestNotEnoughUsersToCoverProduct() throws BusinessException {
		//N�o aloca usuarios a mais quando faltam usuarios para completar a aloca��o
		groupNEUsers.allocate(3);
		assertTrue(groupNEUsers.getEvaluations().get(productNotCovered).size() < 3);
	}
	
	@Test(expected = BusinessException.class)
	public void allocateTestAlreadyAllocated() throws BusinessException {
		//N�o pode alocar um grupo que j� est� alocado
		spfA.allocate(2);
		spfA.allocate(1);
	}

	//Testando o m�todo evaluationDone
	@Test
	public void evaluationDoneTestComplete() throws BusinessException {
		//Evaluations foram completas
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assertTrue(groupNEUsers.evaluationDone());
	}
	
	@Test
	public void evaluationDoneTestNotComplete() throws BusinessException {
		//Evaluations n�o foram completas
		groupNEUsers.allocate(2);
		assertFalse(groupNEUsers.evaluationDone());
	}
	
	@Test 
	public void evalluationDoneTestPartiallyComplete() throws BusinessException {
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		evaluations.get(laRocheCCCream).get(0).setScore(1);
		assertFalse(spfA.evaluationDone());
	}
	@Test
	public void evaluationDoneTestNoEvaluation() throws BusinessException {
		//N�o h� evaluations
		assertFalse(groupNEUsers.evaluationDone());
	}
	
	//Testando os m�todos getAcceptableProducts e getNotAcceptableProducts
	@Test
	public void getAceptableProductsTestAllAccepted() throws BusinessException {
		//Todo produto � aceito
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assert(groupNEUsers.getAcceptableProducts().contains(productNotCovered));
	}
	
	@Test
	public void getNotAceptableProductsTestAllAccepted() throws BusinessException {
		//Nenhum produto � rejeitado
		groupNEUsers.allocate(2);
		Map<Product,List<Evaluation>> evaluations = groupNEUsers.getEvaluations();
		evaluations.get(productNotCovered).get(0).setScore(3);
		assert(groupNEUsers.getNotAcceptableProducts().size() == 0);
	}
	
	@Test
	public void getAcceptableProductsTestSomeAccepted() throws BusinessException {
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		List<Product> expectedAcceptableProducts = new ArrayList<Product>();
		expectedAcceptableProducts.add(naturaSPF20RostoMatte);
		expectedAcceptableProducts.add(baseOBoticarioSPF20);
		expectedAcceptableProducts.add(yvesRocherPowderSPF15);
		expectedAcceptableProducts.add(laRocheCCCream);
		
		//Aceitavel
		evaluations.get(laRocheCCCream).get(0).setScore(2);
		evaluations.get(laRocheCCCream).get(1).setScore(3);
		//Aceitavel
		evaluations.get(yvesRocherPowderSPF15).get(0).setScore(1);
		evaluations.get(yvesRocherPowderSPF15).get(1).setScore(-1);
		//Nao aceitavel
		evaluations.get(niveaBBCream).get(0).setScore(0);
		evaluations.get(niveaBBCream).get(1).setScore(-2);
		//Aceitavel
		evaluations.get(baseOBoticarioSPF20).get(0).setScore(-1);
		evaluations.get(baseOBoticarioSPF20).get(1).setScore(2);
		//Aceitavel
		evaluations.get(naturaSPF20RostoMatte).get(0).setScore(1);
		evaluations.get(naturaSPF20RostoMatte).get(1).setScore(2);
		
		assert(spfA.getAcceptableProducts().equals(expectedAcceptableProducts));
	}
	
	@Test
	public void getNotAcceptableProductsTestSomeAccepted() throws BusinessException {
		spfA.allocate(2);
		Map<Product,List<Evaluation>> evaluations = spfA.getEvaluations();
		List<Product> expectedNotAcceptableProducts = new ArrayList<Product>();
		expectedNotAcceptableProducts.add(niveaBBCream);
		
		//Aceitavel
		evaluations.get(laRocheCCCream).get(0).setScore(2);
		evaluations.get(laRocheCCCream).get(1).setScore(3);
		//Aceitavel
		evaluations.get(yvesRocherPowderSPF15).get(0).setScore(1);
		evaluations.get(yvesRocherPowderSPF15).get(1).setScore(-1);
		//Nao aceitavel
		evaluations.get(niveaBBCream).get(0).setScore(0);
		evaluations.get(niveaBBCream).get(1).setScore(-2);
		
		//Aceitavel
		evaluations.get(baseOBoticarioSPF20).get(0).setScore(-1);
		evaluations.get(baseOBoticarioSPF20).get(1).setScore(2);
		//Aceitavel
		evaluations.get(naturaSPF20RostoMatte).get(0).setScore(1);
		evaluations.get(naturaSPF20RostoMatte).get(1).setScore(2);
		
		assert(spfA.getNotAcceptableProducts().equals(expectedNotAcceptableProducts));
	}
}

