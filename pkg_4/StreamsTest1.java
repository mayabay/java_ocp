package pkg_4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Learn streams KB OCP Chapter 4
 * 
 * */
public class StreamsTest1 {

	private class DVDInfo{
		private String title;
		private String genre;
		private String leadActor;
		
		private DVDInfo(String t, String g, String a){
			title = t;
			genre = g;
			leadActor = a;
		}
		
		private String getTitle() {
			return title;
		}

		private void setTitle(String title) {
			this.title = title;
		}

		private String getGenre() {
			return genre;
		}

		private void setGenre(String genre) {
			this.genre = genre;
		}

		private String getLeadActor() {
			return leadActor;
		}

		private void setLeadActor(String leadActor) {
			this.leadActor = leadActor;
		}

		public String toString() {
			return "[title: "+title+", genre: "+genre+", leadActor: "+leadActor+" ]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		StreamsTest1 st1 = new StreamsTest1();
		// st1.printLinesFromFile( new File("D:\\projects\\java_ocp\\resources.txt").toPath());
		
		List<DVDInfo> dvds = st1.loadDVDs("D:\\projects\\eclipse-workspace\\java_ocp\\src\\dvdinfo.txt");
		dvds.forEach(System.out::println);
		 
	}

	
	private void printLinesFromFile( Path path ) throws IOException {
		
		Stream<String> lines = Files.lines(path);
		
		lines.filter(line -> line.startsWith("?"));
		
		//lines.forEach(System.out::println);
		
	}
	
	
	private List<DVDInfo> loadDVDs(String fileName){
		List<DVDInfo> dvds = new ArrayList<>();
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			
			stream.forEach( line -> {
				String[] dvdItems = line.split("/");
				DVDInfo dvd = new DVDInfo(dvdItems[0], dvdItems[1], dvdItems[2]);
				dvds.add(dvd);
				
			} );
			
		}catch( IOException e ) {
			e.printStackTrace(); System.exit(-1);
		}
		
		return dvds;
	}
	
}
