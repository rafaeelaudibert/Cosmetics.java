# Final Assignment - INF01120 - Program Construction Techniques

## Assignment Definition

### Introduction
The cosmetic industry is highly lucrative. A big quantity of products is released frequently, and the consumer market is pretty big. In order for consumers to better assess which products are worthwhile,
a platform was created where products of a certain category can be evaluated.

In this platform, an avaliation group can be created for a certain kind of product (i.e face sunscreen), and some registered set of people para invited to be the evaluators for some products, which should be in according to the group interest. This way, registered people (part or not of the group avaliation committee), register products with the intent of verifying if they satisfy a threshold of acceptable quality.

The submitted products are evaluated by N evaluators (evaluation committee members), and each one gives a feedback (quantitative and qualitative.

The one which assignes a product to be evaluated by a committee member is the committee coordinator. Based on the feedbacks given by the evaluators, the committee coordinator decides which products are above the said threshold.

Your objective is to develop an application which can help the committee coordinator to assign products to be evaluated by the evaluation committee members, and help to determine the products which are acceptable.

The initial data insertion is done directly in the database, and new products register will not be implemented in this application version.


### Features

The application must have three features, descibred below:

**Allocation of Products to Members of the Evaluation Committee**
1. User (a evaluator committee coordinator) requests the system an allocation of products to members of the evaluation committee
2. System asks for which evaluation group the allocation should be done (a group which has not been allocated yet), and how many evaluators will be allocated for each product, which must be a number between 2 and 5.
3. User informs the asked data.
4. System allocates the products through the following algorithm:

    a. System initializes an allocation set with all the Evaluation Group products

    b. System chooses the product with the smaller id

    c. System chooses commitee members, which must agree with the following criteria:
        
        i. member which is the avaliation requester or belong to the requester state is excluded
        ii. member which categories of interest doesn't match the topic related to the product is excluded
        iii. member which has already been allocated to evaluate the product is excluded
    
    d. System orders the candidate members by the number of allocated products until the moment, in ascending order (considering only the current evaluation group). The user's id is used as tie-breaker attribute (user with smaller id has preference).
    
    e. System assigns the evaluation to the first user in the list

    f. System erases this product from de allocation group, and returns to step `b` if the product allocation group is not empty yet.
 
    g. If the products already have the number of evaluators as asked by the user, the algorithm finishes. Else, it returns to the step `a`.

5. System saves the allocation, and displays to the user an allocation group list of products, and a log in the following way:
    
    a. Starting the allocation.
    
    b. Product id X allocated to the evaluator id Y
    
    c. [...]
    
    d. Allocation end.


**Product scores attribution**

1. User requests to inform a score for a product, and the system shows the products list.
2. Usuer chooses a product, and the system displays the evaluators list.
3. User chooses an evaluator.
4. Systems asks a grade between -3 and 3 for the product, and the user informs it.
5. System stores the informed grade, relationing it with the evaluator and the product.


**Products report**

1. User requests to the system a products report.
2. System asks the selection of an evaluation group, and the user informs it.
3. If the evaluation group has not been alocated yet or there are scores not yet stored, the system emits an alert.
4. Else, the system computes the score averages and exhibits the products in 2 different lists:
a. Products with average score >= 0, are displayed in the list with acceptable quality products, in descending order.
b. Products with average score < 0, are displayed in the list with inacceptable quality products, in ascending order.

## Implementation

The application should be implementend AND tested. The development must use a test-oriented approach, this is, based in the UML project, unit tests (automated) should be created before the code implementation. After, the implementation should be performed, and the application tested based in the tests created before.

The system should be implemented in Java, and the unit tests should be done with the tool JUnit.

The unit tests should necessarily embrace the domain classes, and interface or database-related classes doesn't need to be tested.

## Authors

Code developed at [INF](https://inf.ufrgs.br)-[UFRGS](https://ufrgs.br) with Java, using Github, Astah and JUnit by:
* Bruno Trindade
* Fábio Gomes
* Junior Valcarenghi
* [Rafa Audibert](https://github.com/rafaeelaudibert)