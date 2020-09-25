package pkg_4;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

import plants.*;


/*
 * Learn stream pipelines
 * 
 * 
 * */
public class SurinamBananas {

	private static int idSrc;
	
	// max pallet load for 40 ft container with EUR pallet
	private static int shippingContainer40FtPalletMaxLoad = 24;
	
	// max number of CardboardBox on EURPallet
	private static int maxCardboardBoxLoadOnEUROallet = 36;
	
	// batch of pallets loaded to the truck
	private Set<Pallet <CardboardBox<Musa,Integer>, Integer>> pallets;

	// banana supplier
	private Supplier<Musa> musaSupplier = () -> {
		int n = (int)(Math.random() +1);
		if( n < 5 ) {
			return new Acuminata();
		}else {
			return new Basjoo();
		}
		
	};	
	
	// supplies banana boxes
	public Supplier< CardboardBox<Musa,Integer> > boxSupplier = () -> {
		return new CardboardBox<>( ++idSrc );
	};
	
	// supplies EURPallet pallets
	private Supplier<EURPallet<CardboardBox<Musa,Integer>, Integer>> palletSupplier = () -> {
		return new EURPallet<CardboardBox<Musa,Integer>, Integer>(++idSrc);
	};
	
	/**
	 * main()
	 * 
	 * */
	public static void main(String[] args) {
		SurinamBananas sb = new SurinamBananas();
		sb.go();
	}
	
	/*
	 * loads 24 EURPallets for a 40ft shipping container
	 * */
	private void go() {
		
		// collection of Box
		Set<Box<Musa>> boxes = new HashSet<>();
		
		// stream of EURPallet
		Stream<EURPallet<CardboardBox<Musa,Integer>, Integer >  > eurPalletStream = Stream.generate(palletSupplier);
		
		pallets = eurPalletStream
			.limit(24)
			.map( (t) -> {
				int boxCount = maxCardboardBoxLoadOnEUROallet;
				do {
					// get a cardboard box
					CardboardBox<Musa,Integer> cbox = boxSupplier.get();
					
					// fill box with bananas
					// stream of musa will fill the box
					Stream<Musa> musaStream = Stream.generate(musaSupplier);
						long count = musaStream
										.limit(24)
										.map( (m) -> {
											cbox.add(m);
											return "\n loaded box with a banana";
										} )
										.count();
						//System.out.println("\n box loaded with "+ count +" bananas.");
					// load box to pallet	
					t.loadItem(cbox);
					boxCount--;	
				}while (boxCount > 0);
				return t;
				
			} )
			.collect( TreeSet::new, TreeSet::add, TreeSet::addAll  );
		
		pallets.forEach(System.out::println);
	}
	
	private void listPalletContent( Pallet<?,?> pallet ) {
		Iterator <?> iter = pallet.iterator();
		while( iter.hasNext() ) {
			System.out.println( iter.next() );
		}
		
	}
	
}
