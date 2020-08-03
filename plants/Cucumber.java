package plants;
/** A cucumber is a cucumis fruit */
public class Cucumber extends Cucumis {

     /** No-arg constructor */
       public Cucumber(){
           super("Cucumber");
     }
  
       /** constructor */
      public Cucumber(String name){
          super(name);
      }
 
      public double getWeight(){
          return 0.3;
      }
 
      public double getVolume(){
          return -1;
      }
}
