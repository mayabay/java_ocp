package pkg_3;
import java.util.*;
public class DVDInfo implements Comparable<DVDInfo> {
	
	private String title;
	private String genre;
	
	public DVDInfo( String title, String genre ){
		this.title = title;
		this.genre = genre;
	}

	public String getTitle(){
		return title;
	}

	public String getGenre(){
		return genre;
	}

	public String toString(){
		return title + ",  " + genre + "/ ";
	}
	
	public int compareTo(DVDInfo other){
		return this.title.compareTo( other.getTitle() );
		
	}

}
