/**
 * BS 8.3
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 *
 */
public class ReadTotInVenedig {

	private Set<String> words = new TreeSet<>();

	private int sentences = 0;
	
	private Set<Character> ABCLowerScase = new HashSet<>( 
			Arrays.asList( 'a','b','c','d','e','f','g','h','i','j','k','l','m',
					'n','o','p','q','r','s','t','u','v','w','x','y','z' ) 
			);
	
	private Set<Character> sentenceSymbol = new HashSet<>( 
			Arrays.asList( '"',',',';','-','.',' ', '\n','\r') 
			);	
	
	private Predicate<Character> isSentenceSysmbol = sentenceSymbol::contains;

	private List<Integer> sentenceWordCount = new ArrayList<>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadTotInVenedig rtiv = new ReadTotInVenedig();
		// Kafka_DieVerwandlung.txt
		// ThomasMann_TodInVenedig.txt
		//rtiv.test1();
		//rtiv.test2("ThomasMann_TodInVenedig.txt");
		System.out.println("------------------------------------------------");
		rtiv.test2("ThomasMann_TodInVenedig.txt");
	}

	private void test1(  ) {
		File tiv = new File( ConfigObj.IMMO_DATA_PATH + File.separatorChar + "ThomasMann_TodInVenedig.txt" );
		if ( ! tiv.exists() ) throw new IllegalStateException("file not found: " + tiv.getName());
		
		// read bytes into array
		byte[] bytes = new byte[ 1024 ];
		
		try( BufferedInputStream in = new BufferedInputStream(new FileInputStream( tiv )) ) {
			
			in.read(bytes, 0, bytes.length);
			
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
		for( byte b : bytes ) {
			char c = (char)b;
			System.out.print( c );
		}
		
	}
	
	
	private void test2(String fileName) {
		File tiv = new File( ConfigObj.IMMO_DATA_PATH + File.separatorChar + fileName );
		if ( ! tiv.exists() ) throw new IllegalStateException("file not found: " + fileName);
		
		Set<String> words = new HashSet<>();
		
		try( BufferedReader in = new BufferedReader(new FileReader( tiv )) ) {
			
			int c = 0;
			StringBuffer buf = new StringBuffer();
			int wordsInSentence = 0;
			boolean beginSentence = true;
			boolean endSentence = false;
			
			while( ( c = in.read() ) != -1 ) {
				
				// find end of word
				if (isSentenceSysmbol.test( new Character((char)c) )) {
					
					if (buf.length() > 1) {
						this.words.add(buf.toString());
						if (beginSentence) wordsInSentence++;
						
						// new buf
						buf.delete( 0, buf.length() );
						//buf = new StringBuffer();
					}
					
					// dec 46 = full stop
					if ( c == 46 ) {
						this.sentences++;	// count sentence
						beginSentence = beginSentence ? false : true;
						sentenceWordCount.add(wordsInSentence);
						wordsInSentence = 0;	// reset word in sentence count
						
					}					
					
				};
				
				// find begin of word 
				if ( ( c >= 65 && c <= 90 ) || ( c >= 97 && c <= 122 )  ) {
					// is A..Z a..z
					// start
					buf.append((char)c);
				}
			}
		
		}catch( IOException e ) {
			e.printStackTrace();
		}		
		
		
		System.out.println("Text in "+ tiv.getName() +" contains " + this.words.size() + " words." );
		System.out.println("============");
		for( String word : this.words ) {
			//System.out.println( word );
		}
		
		this.analyzeWordsLength();
	}
	
	private void analyzeWordsLength() {
		IntSummaryStatistics intSummaryStats = 
		this.words.stream()
		.mapToInt( String::length )
		.summaryStatistics();
		
		System.out.println( intSummaryStats );
		System.out.println("words total : " + intSummaryStats.getCount());
		System.out.println("character min length : " + intSummaryStats.getMin());
		System.out.println("character max length : " + intSummaryStats.getMax());
		System.out.println("character mean length : " + intSummaryStats.getAverage()  );
		System.out.println("sentences : " + this.sentences + " ("+ this.sentenceWordCount.size() +") ");
		
		IntSummaryStatistics wordsInSentenceStats =
				this.sentenceWordCount.stream()
				.mapToInt( Integer::intValue )
				.summaryStatistics();
		System.out.println( "Statistics : word count in sentence : " + wordsInSentenceStats );
		
//		System.out.println("==========");
//		System.out.println("Words longer than 18 chars");
//		this.words.stream()
//		.filter( s -> s.length() > 18 )
//		.forEach(System.out::println);
	}
	
	
	
}
