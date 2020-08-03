package plants;
/** A KoreanMelon is a cucumis fruit */
public class KoreanMelon extends Cucumis {

     /** No-arg constructor */
       public KoreanMelon(){
           super("KoreanMelon");
      }
  
      /** constructor */
      public KoreanMelon(String name){
          super(name);
      }
 
      public double getWeight(){
          return 3.6;
      }
 
      public double getVolume(){
          return -1;
      }
}
