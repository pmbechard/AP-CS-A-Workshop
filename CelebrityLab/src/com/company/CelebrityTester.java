/**
 * Tester the Celebrity class during Activity 2.
 * @author sage miller
 * @version 1 22/04/2019
 */
public class CelebrityTester {
  
  public static void main(String[] args) {
    Celebrity cel = new Celebrity("Big Bird", "Covered in yellow feathers"); //construct a Celebrity object
    System.out.println(cel); //test toString
    System.out.println("Celebrity clue: " + cel.getClue());
    System.out.println("Celebrity name: " + cel.getAnswer());
    //change the clue
    cel.setClue("Is 8 feet 2 inches tall");
    System.out.println("Celebrity clue: " + cel.getClue());
    
    //change the celebrity and the clue
    cel.setAnswer("Vint Cerf");
    cel.setClue("Is known as the \"Father of the Internet\"");
    System.out.println(cel); 
  }
  
}
