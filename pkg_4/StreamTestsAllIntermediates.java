package pkg_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamTestsAllIntermediates {
	
	
	
	public static void main(String[] args) {
		
		
		Stream<Ding> dingerStream = Stream.generate( () -> Ding.makeDing());
		Stream<Ding> dingerStream2 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream3 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream4 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream5 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream6 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream7 = Stream.generate( Ding::makeDing );
		Stream<Ding> dingerStream8 = Stream.generate( Ding::makeDing );
		
		System.out.println( dingerStream.limit(10)
										.skip(3)
										.count() 
							);
	// limit() skip()
		dingerStream2.limit(12)
					 .skip(5)
					 .forEach(System.out::println);
		// peek() allMatch noneMatch anyMatch
		System.out.println( "===");
		boolean idGroesser10 = dingerStream3.limit(12)
				.peek(System.out::println)
				.skip(8)
				.peek(System.out::println)
				.noneMatch( x -> x.id > 34);
		
		System.out.println(idGroesser10);
		
	
		
		// filter
		
		DoubleStream doubleStream = DoubleStream.generate(Math::random);
		doubleStream.limit(20)
					.filter(p -> p > 0.5)
					.forEach( d -> System.out.printf( "%1$.3fh %n", d) );	//%1$ erste Argument
																			//%2$ zweites Argument
																			// f = floating point .x = Anzahl dezimalsdtellem
																			// d - integer, f - floating point, s - string
		
		
		
		dingerStream5.limit(3)
					 .peek(System.out::println)
					 .forEach( a -> System.out.printf("%1$x %n", a.id));	// x - Darstellung in Hexadeczimal
		
		// min() max()
		
		
	//	Stream<String> stringStream = Stream.of("A","ABC","D","EFG");
		Stream<String> stringStream = Stream.empty();
		
		Optional<String> stroptional = stringStream.min( (a,b) -> a.compareTo(b) );
						
		System.out.println( stroptional +  " -  " + stroptional.orElse("nix da") );
		
		
		
		Optional<Ding> basicErrors = dingerStream6.limit(5)
												.min( (a,b) -> (a.name).compareTo((b.name) ));
		
		basicErrors.ifPresent( d -> System.out.println( d.id) );
		
		
		
		DoubleStream doubleStream2 = DoubleStream.generate(Math::random);

		doubleStream2.limit(20)
					 .sorted()
					 .forEach(  System.out::println );
		
		
		
		
		
		Stream<String> stringStream2 = Stream.of("H","ä","n","s","c","h","e","n"," ","k","l","e","i","n"," ","g","i","n","g"," ","a","l","l","l","e","i","n");
		Stream<String> parallelStringStream = stringStream2.parallel();
		
		parallelStringStream.distinct()
					  .forEach( System.out::print );
		
		System.out.println("\n sorted() und distinct() stateful");
		// sorted und distinct sind stateful, d.h. sie müssen den gesamten Stream sehen
		System.out.println("kleinerStream");
		DoubleStream kleinerStream = DoubleStream.generate(Math::random);
		
//		kleinerStream.sorted()							// Achtung: sorted() vor Limit() => OutOfMemoryExc
//					  .limit(10)						// sorted und distinct sind stateful, d.h. sie müssen den gesamten Stream sehen
//					  									// Sortieren vor Begrenzen klappt nicht
//					  .forEach( System.out::println);
//		
		
		System.out.println("\n Länge des Namens gemapped - map() baut automatisch einen neuen Stream als Rückgabewert");
		
		// flatMap()
		
		dingerStream7.limit(15)
					 .map( a -> a.name.length())
					 .forEach(System.out::println);
		
		
		System.out.println("\n flatMap() - muss man selbst einen neuen Stream bauen, der zurückgegeben wird");
		
		List<Ding> dinglist1 = new ArrayList<>();
		List<Ding> dinglist2 = new ArrayList<>();
		List<Ding> dinglist3 = new ArrayList<>();
		List<Ding> dinglist4 = new ArrayList<>();
		dinglist1.add( Ding.makeDing() );
		dinglist1.add( Ding.makeDing() );
		dinglist2.add( Ding.makeDing() );
		dinglist2.add( Ding.makeDing() );
		dinglist3.add( Ding.makeDing() );
		dinglist4.add( Ding.makeDing() );
		
		Stream<List<Ding>> listDingStream = Stream.of(dinglist1,dinglist2,dinglist3,dinglist4 );
	
		long amount = listDingStream.flatMap( liste -> liste.stream() ) 		// auf den Listen kann stream() aufgerufen werden, weil sie Collections sind
					  .count();													
		
		System.out.println(amount);
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		}
	}










