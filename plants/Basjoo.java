package plants;
/** Basjoo is a japanese banana fruit */
public class Basjoo extends Musa {

    /** No-arg constructor */
    public Basjoo(){
        super("Basjoo");
   }

   /** constructor */
   public Basjoo(String name){
       super(name);
   }

   public double getWeight(){
       return .5;
   }

   public double getVolume(){
       return -1;
   }	
	
}
