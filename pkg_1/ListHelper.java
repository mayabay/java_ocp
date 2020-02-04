package pkg_1;
import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.sort;

public class ListHelper {
	
	public List<String> copyAndSortList( List<String> original  ){
		
		List <String> list = new ArrayList<>( original  );
		
		sort(list);
		
		return list;

	}
}
