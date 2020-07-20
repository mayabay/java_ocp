package pkg_3;
import java.util.*;
import java.io.*;
class Exoplanets {
	
	private File dataSrcFilePath = new File("planets_2020.07.13_02.06.28.csv");
	private ArrayList<ExoPlanet> planets = new ArrayList<>();

	private class ExoPlanet {
		private String pl_name; 
	}


	Exoplanets( String dataSrcPath ){
		dataSrcFilePath = new File( dataSrcPath );
		if ( !dataSrcFilePath.exists() )
			new IllegalArgumentException("no data src!");
	}

	public static void main(String[] args){

		Exoplanets ep = new Exoplanets("planets_2020.07.13_02.06.28.csv");


	}




}
