package pkg_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Learn how to create streams
 * 
 * */
public class KB_9_2 {

	private class DVDInfo {
		private String title;
		private String genre;
		private String leadActor;
		
		/**
		 * @param title
		 * @param genre
		 * @param leadActor
		 */
		private DVDInfo(String title, String genre, String leadActor) {
			super();
			this.title = title;
			this.genre = genre;
			this.leadActor = leadActor;
		}
		
		/**
		 * @return
		 * */
		public String toString() {
			return "title: " + title + ", genre: " + ", leadActor: " + leadActor;
		}
	}

	private List<DVDInfo> dvds;
	
	public static void main(String[] args) {
		KB_9_2 obj = new KB_9_2();
		obj.buildListFromFile();
	}

	/*
	 * Exercise 9-1
	 * */
	private void buildListFromFile() {
		
		try (Stream<String> lines = Files.lines(Paths.get("D:\\projects\\eclipse-workspace\\java_ocp\\src\\dvdinfo.txt"))) {
			dvds =
					lines
					.map( str -> {
						String[] arr = str.split("/");
						// Donnie Darko/sci-fi/Gyllenhall, Jake
						return new DVDInfo(arr[0], arr[1], arr[2]);
					} )
					.collect( Collectors.toList() );
		}catch( IOException e )   {
			e.printStackTrace();
			System.exit(-1);
		}
		
		dvds.forEach(System.out::println);
	}
	
}
