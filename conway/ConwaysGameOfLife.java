package conway;
import java.util.*;
/**
 * John Horton Conway's Game of Life
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * */
public class ConwaysGameOfLife {

	
	public static void main(String[] args){
		
		//ConwaysGameOfLife game = new ConwaysGameOfLife( 20, 7, 3, 2 );
		//ConwaysGameOfLife game = new ConwaysGameOfLife( 20, 10, 10, 6 );
		
		ConwaysGameOfLife game = new ConwaysGameOfLife( 20, 5, 5, 2 );
		game.struggleForExistence();
	}

	// ---------------------------------------------------------------------------
	private int[][] world;
	
	private int[][] lastWorld;

	private Origin origin;

	private int intervalSecs = 2;

	private int changesInInterval = 0; 

	private boolean printZeros = false;	

	private boolean printChanges = true;

	

	/**
	 * represents heap of cells, where life starts
	 * @param x of center position T   
	 * @param y of center position T
	 * @param size distance from T i nworld 
	 * */
	private class Origin {
		private int x,y,size;
		private int[][] originBox;
		private boolean containsLife = true;

		private Origin(int x, int y, int size){
			this.x = x;
			this.y = y;
			this.y = y;
			originBox = new int[size*2][size*2];
		}

		private boolean containsLife(){
			return containsLife;
		}

		private int[][] getBox(){
			return originBox; 
		}

		private boolean tryCreation( ){
			for( int x = 0; x < originBox.length; x++ ){ 
				for( int y = 0; y < originBox[x].length; y++ ){
					if (Math.random() >= 0.5 ){
						originBox[x][y] = 1;
					}
						//originBox[x][y] = 1; // debug
				}
			}
			pBox( originBox );
			return this.containsLife();
		}
	}

	// ---------------------------------------------------------------------------


	public void struggleForExistence(){
		
		Thread t = Thread.currentThread();

		StringBuffer msg = new StringBuffer();
		int cycle = 0;		

		int repeatedPatternCount = 0;

		while( true ){

			changesInInterval = 0;

			cycle++;
			System.out.println( "#" + cycle );

			//msg.append("#" + cycle);
			int cellsAlive = 0;
			for( int x = 0; x < world.length; x++ ){
				for( int y = 0; y < world[x].length; y++ ){
					int r = evolveCell( x, y );
					if ( r == 1 ){ cellsAlive++; }
					//pBox(world);
				}
			}

			// check repeated pattern
			/*
			if ( Arrays.deepEquals( world, lastWorld ) ){
				repeatedPatternCount++;
				System.out.println( "-- deep equals !!" );

				if ( repeatedPatternCount > 4 ){
					//msg.append("pattern repeats"); break;
				}
			}
			*/

			if (changesInInterval == 0){
				repeatedPatternCount++;
				printZeros = true;
				if ( repeatedPatternCount == 2 ){
					msg.append("pattern repeats"); break;
				}
			}

			// copy pattern to last value
			System.arraycopy( world, 0, lastWorld, 0, world.length );	

			// no life in world
			if ( cellsAlive == 0 ){
				msg.append( "\tno cells alive" );
				break;
			}

			// display world
			pBox(world);

			// pause	

			try{
				Thread.sleep( intervalSecs * 1000 );
			}
			catch( InterruptedException ie ){
				msg.append( "thread interrupted" );
				break;
			}

		}

		System.out.println( " --------------------------------------- " );

		pBox(world);

		System.out.println( msg );
		msg.append("\tgame end");
	}

	private int evolveCell( int x, int y ){
		int status = world[x][y];
		int r = 0;	// 0 dead, 1 alive

		// 8 cell neighbors
		int[] cn = new int[8];	

		// check boundary
		int x_minus_1 =  x - 1 < 0 ? 0 : x-1 ;
		int x_plus_1 =  x + 1 > world.length-1 ? 0 : x+1 ;

		int y_minus_1 =  y - 1 < 0 ? 0 : y-1 ;
		int y_plus_1 =  y + 1 > world[x].length-1 ? 0 : y+1 ;
		
		// get neighbors
		cn[0] = world[  x_minus_1 ] [  y   ];				// N
		
		cn[1] = world[  x_minus_1 ] [  y_plus_1 ];			// NO
		
		cn[2] = world[  x   ] [  y_plus_1 ];				// O
		
		cn[3] = world[  x_plus_1 ] [  y_plus_1 ];			// SO
		
		cn[4] = world[  x_plus_1 ] [  y   ];				// S
		
		cn[5] = world[  x_minus_1 ] [  y_minus_1 ];			// SW
		
		cn[6] = world[  x   ] [  y_minus_1 ];				// W
		
		cn[7] = world[  x_minus_1 ] [  y_minus_1 ];			// NW
		
		// check neighors
		int nbAlive = 0;
		for( int n : cn ){
			if (n == 1) nbAlive++;
		}

		if ( world[x][y] == 1 ){
			// cell alive
			switch(nbAlive){
				case 0: ;		// ft	
				case 1: r = 0; break;
				case 2: ;		// ft
				case 3: r = 1; break;
				case 4: ;		// ft
				case 5: ;		// ft
				case 6: ;		// ft
				case 7: ;		// ft
				case 8: r = 0; break;
			}
		}
		else{
			// cell dead
			if ( nbAlive == 3 ){ r = 1; }	// reproduction
		}

		
		if (status == 1 && r == 0){ changesInInterval++; } 
		if (status == 0 && r == 1){ changesInInterval++; } 
		
		if ( printChanges ){

			if (status == 1 && r == 0){ 
				System.out.print( " t["+x+"]["+y+"]" );
			}

			if (status == 0 && r == 1){ 
				System.out.print( " *["+x+"]["+y+"]" );
			}
		}

		world[x][y] = r;
		
		return r;
	}

	// drop a box into the world
	private void dropBox(int world[][], int[][] box, int xStart, int yStart ){
		if (world == null){ throw new IllegalStateException("world is null"); }
	
		System.out.println( "# " + xStart + ", " + yStart );
		int yWorld = yStart; 
		for ( int x = 0; x < box.length; x++, xStart++ ){
			yWorld = yStart;
			for( int y = 0; y < box[x].length; y++, yWorld++ ){
				world[xStart][yWorld] = box[x][y];
				//System.out.println( "world[ "+xStart+" ][ "+yWorld+" ] set to" + " "+box[x][y]+""  );
			}
		}
	}

	// print 2D array
	private void pBox( int[][] box ){

		System.out.println(  );
		for( int x = 0; x < box.length; x++ ){ 
			for( int y = 0; y < box[x].length; y++ ){
				if (printZeros){
					System.out.print( " "+ box[x][y] + "  " );
				}else{
					System.out.print(   " "+ (box[x][y] == 1? "1":" ") + "  " );
				}
			}
			System.out.println( " | " + (x) + "\n" );
		}
		System.out.println( );
	}

	/**
	 * costructs game
	 * @param sizeOfWorld - value of x and y 
	 * @throws IllegalArgumentException, if size smaller than  0 
	 * @throws IllegalStateException, if origin is not positioned in world 
	 * */
	public ConwaysGameOfLife( int sizeOfWorld, int xOfT, int yOfT, int sizeOfOrigin ){
		if (sizeOfWorld < 0 ) throw new IllegalArgumentException("world size greater than 0 and greater than origin size");
		
		// x check 
		if ( (xOfT - sizeOfOrigin) < 0 ) throw new IllegalStateException("origin not in world");
		if ( (xOfT + sizeOfOrigin) > sizeOfWorld ) throw new IllegalStateException("origin not in world");


		// y check 
		if ( (yOfT - sizeOfOrigin) < 0 ) throw new IllegalStateException("origin not in world");
		if ( (yOfT + sizeOfOrigin) > sizeOfWorld ) throw new IllegalStateException("origin not in world");

		// build world
		world = new int[sizeOfWorld][sizeOfWorld];
		lastWorld = new int[sizeOfWorld][sizeOfWorld];
 
		//pBox(world);


		// build origin in world 
		origin = new Origin( xOfT, yOfT, sizeOfOrigin );
		origin.tryCreation();
		if ( !origin.containsLife() ){
			throw new IllegalStateException("no life developed in origin");
		}
		
		dropBox(world, origin.getBox(), yOfT-sizeOfOrigin, xOfT-sizeOfOrigin );

		// show eden
		pBox(world);


	}

}
