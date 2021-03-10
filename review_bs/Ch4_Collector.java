package review_bs;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.*;
import java.util.Arrays;

public class Ch4_Collector {

    class Person {
        String name;
        int ageInYears;
        char sex;
        Person(){}
        Person(String name, int ageInYears){
            this( name, ageInYears, 'w' );
        }
        Person(String name, int ageInYears, char sex){
            this.name = name;
            this.ageInYears = ageInYears;
            this.sex = sex;
        }
        public String toString(){
            return "["+name+", "+ageInYears+"]";
        }
    }

    public static void main(String[] args) {
        Ch4_Collector obj = new Ch4_Collector();
        //obj.test1();
        //obj.test2();
        obj.test3();
    }

    private Supplier<List<Person>> personSupp = () -> Arrays.asList( 
        new Person("Fred",43, 'm'),
        new Person("Linda",12, 'w'),
        new Person("Barni", 56,'m'),
        new Person("Bart", 25,'m')
    );

    private void test1(){
        Stream<Person> stream = Stream.of( new Person("Fred",43), new Person("Linda",12) );
        HashSet<Person> hs = stream
            .collect( Collectors.toCollection(HashSet::new) );
        System.out.println(hs);
    }

    private void test2(){
        String str =
        personSupp.get().stream()
            .collect( 
                Collectors.mapping( Person::toString, Collectors.joining("--") )

             );
        System.out.println(str);
    }

    private void test3(){
        Map<Character, List<Person>> map =
        personSupp.get().stream()
            .peek(System.out::println )
            .collect( 
                Collectors.groupingBy( p -> p.sex )
             );
        System.out.println(map);
    }    

}
