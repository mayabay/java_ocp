/**
 * OCP 4 Learn Collector impl. for stream pipelines
 */
package pkg_4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * see http://manu.sporny.org/2011/public-domain-genome/
 */
public class ManuSNPTest1 {

	/**
	 * # rsid  chromosome  position    genotype
	 * */
	class SNP {
		
		private String rsid;
		private String chromosome;
		private int position;
		private String genotype;
		
		SNP(String rsid, String chromosome, int position, String genotype) {
			super();
			this.rsid = rsid;
			this.chromosome = chromosome;
			this.position = position;
			this.genotype = genotype;
		}

		/**
		 * @return the rsid
		 */
		String getRsid() {
			return rsid;
		}

		/**
		 * @return the chromosome
		 */
		String getChromosome() {
			return chromosome;
		}

		/**
		 * @return the position
		 */
		int getPosition() {
			return position;
		}

		/**
		 * @return the genotype
		 */
		String getGenotype() {
			return genotype;
		}

	}
	
	Path path = Paths.get("/home/andreas/git/dna/ManuSporny-genome.txt");
	
	List<String> lines = new ArrayList<>();
	List<SNP> snps = new ArrayList<>();
	
	Map<Integer,Instant> logs = new ConcurrentSkipListMap<>();
	
	int counter;
	
	Consumer<?> objToConsole = System.out::println;
	IntConsumer intToConsole = System.out::println;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManuSNPTest1 test1 = new ManuSNPTest1();
		
		try {
			test1.loadLines();
		}catch( IOException e ) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		//test1.test1();
		test1.test2();
		System.out.println("Ende");
	}
	
	private void loadLines() throws IOException {
		
		try(
				FileInputStream fis = new FileInputStream(path.toFile());
				InputStreamReader in = new InputStreamReader( fis );
				BufferedReader buf = new BufferedReader(in);				
				){
			
			String str = null;
			while ( (str = buf.readLine()) != null ) {
				lines.add(str);
			}
			
			
		}catch( IOException io ) {
			// log
			throw io;
		}

	}
	
	// Collectors.toMap( str -> split( "ţ" )[3], str -> split( "ţ" )[0] )
	private void test1() {
		
		logs.put(1, Instant.now());
		
		Map<String, Map<String,Long> > r =
		
		lines
		//.stream()
		.parallelStream()
		//.limit(100_000)
		.filter( str -> ! str.startsWith("#") )
		.collect( Collectors.groupingBy( 
					(String str) -> { String[] arr = str.split( "\t" ); return arr[1]; },
					TreeMap::new,
					//	Collectors.mapping(
					//		Function.identity(), Collectors.groupingBy( str -> str.split( "\t" )[3] ) 
					//	)
					Collectors.groupingBy( str -> str.split( "\t" )[3], Collectors.counting() )
				
				) 
			);
		
		logs.put(2, Instant.now());
		
		Duration du = Duration.between(logs.get(1), logs.get(2));
		System.out.println("duration milli sec. : " + (du.getNano()/1000) );
		System.out.println("duration mikro sec. : " + (du.getNano()/1000_000) );
		
		Iterator< Map.Entry<String, Map<String,Long>> > iter = r.entrySet().iterator();
		while( iter.hasNext() ) {
			Map.Entry<String, Map<String,Long>> me = iter.next();
			System.out.println("Chromosome = " + me.getKey());
			System.out.println("\t\t GenoType = " + me.getValue() );
		}
		
	}

	private void test2() {
		
		int counterLocal = 0;
		
		logs.put(1, Instant.now());
		
		int summy =
		
		lines
		.stream()
		//.parallelStream()
		.unordered()
		.map( str -> {
				String[] arr = str.split("\t");
				if (arr.length == 4) {
					SNP snp = null;
					try {
						snp = new SNP(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3]);
						counter++;
					}catch( NumberFormatException e ) {
						//counter++;
						//counterLocal++;
						return null;
					}
					return snp;
				}
				else {
					return null;
				}
			} 
		)
		.filter( Predicate.not( Objects::isNull ) )
		//.filter( snp -> snp.getChromosome().equals("2") )
		.mapToInt(SNP::getPosition)
		//.peek(intToConsole)
		.sum();
		
		logs.put(2, Instant.now());
		System.out.println("counter = " + counter );
		System.out.println("summy = " + summy );
		
		Duration du = Duration.between(logs.get(1), logs.get(2));
		System.out.println("duration milli sec. : " + (du.getNano()/1000) );
		System.out.println("duration mikro sec. : " + (du.getNano()/1000_000) );		
		
	}
	
}
