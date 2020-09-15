package plants;
/** Acuminata is a banana fruit native to south east asia */
public class Acuminata extends Musa {

     /** No-arg constructor */
       public Acuminata(){
           super("Acuminata");
      }
  
      /** constructor */
      public Acuminata(String name){
          super(name);
      }
 
      public double getWeight(){
          return .6;
      }
 
      public double getVolume(){
          return -1;
      }
}
