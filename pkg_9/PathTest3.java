/**
 * 
 */
package pkg_9;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 */
public class PathTest3 {

	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathTest3 pt3 = new PathTest3();
		//pt3.test1();
		//pt3.test0();
		pt3.test2();

	}

	private void test2() {
		System.out.println("-- learn resolve -----------------------------");
		System.out.println("resolve() ");
		System.out.println("enter path 1 : ");
		String strPath1 = scanner.next();
		System.out.println("enter path 2 : ");
		String strPath2 = scanner.next();
		Path p1 = Paths.get(strPath1);	// Paths.get(strPath1, null) NPE
		Path p2 = Paths.get(strPath2);		
		System.out.println("What do you think? : ");
		String userRelPath = scanner.next();
		System.out.println("..");
		System.out.println("res. path u() : " + Paths.get(userRelPath).toString()  ) ;
		System.out.println("res. path m() : " +  p1.resolve(p2) );		
		System.out.println("same? : " + (Paths.get(userRelPath).equals(p1.resolve(p2))));		
	
		/*
		 
			resolve() 
			enter path 1 : 
			/abs/thing
			enter path 2 : 
			/abs/anotherThing
			What do you think? : 
			/abs/anotherthing
			..
			res. path u() : \abs\anotherthing
			res. path m() : \abs\anotherThing
			same? : true		 
		 
			resolve() 
			enter path 1 : 
			rel/thing
			enter path 2 : 
			rel/anotherthing
			What do you think? : 
			rel/thing/rel/anotherthing
			..
			res. path u() : rel\thing\rel\anotherthing
			res. path m() : rel\thing\rel\anotherthing
			same? : true		 
		 */
		
		
	}
	
	private  void test0() {
		System.out.println("(y) continue; (c) cancel");
		LOOP: while(true) {
			String input =scanner.nextLine();
			switch(input) {
			case "y": this.test1(); break;
			case "n": // fall down
			case "c": break LOOP; 
			default: continue;
			}
		}
		System.out.println("end");
		scanner.close();
	}
	
	private void test1() {
		System.out.println("-- learn relativize -----------------------------");
		System.out.println("relativize() ");
		System.out.println("enter path 1 : ");
		String strPath1 = scanner.next();
		System.out.println("enter path 2 : ");
		String strPath2 = scanner.next();
		Path p1 = Paths.get(strPath1);	// Paths.get(strPath1, null) NPE
		Path p2 = Paths.get(strPath2);
		System.out.println("What do you think? : ");
		String userRelPath = scanner.next();
		System.out.println("..");
		System.out.println("rel. path u() : " + Paths.get(userRelPath).toString()  ) ;
		System.out.println("rel. path m() : " +  p1.relativize(p2) );		
		System.out.println("same? : " + (Paths.get(userRelPath).equals(p1.relativize(p2))));
		
		/*
			relativize() 
			
			enter path 1 : 
			/tmp/sub/file
			enter path 2 : 
			/tmp/sub/dir/afile
			rel. path : ..\dir\afile	
					
			enter path 1 : 
			/tmp/dir/file
			enter path 2 : 
			/tmp/dir/file
			rel. path : 		
		
			enter path 1 : 
			/file
			enter path 2 : 
			/tmp/dir1/dir2/file2
			rel. path : ..\tmp\dir1\dir2\file2		
		
			enter path 1 : 
			/home/sysadm/bin/cmd.sh
			enter path 2 : 
			/home/sysadm/log
			rel. path : ..\..\log		
		
			enter path 1 : 
			/root/afile.txt
			enter path 2 : 
			/tmp/
			rel. path : ..\..\tmp		
		
			enter path 1 : 
			/home/fred
			enter path 2 : 
			/tmp/barnie/log.txt
			rel. path : ..\..\tmp\barnie\log.txt		
		
			enter path 1 : 
			/tmp/dir
			enter path 2 : 
			/home/frank/file
			What do you think? : 
			../../home/frank
			..
			rel. path u() : ..\..\home\frank
			rel. path m() : ..\..\home\frank\file		
		
			enter path 1 : 
			/home/./../file
			enter path 2 : 
			/home/frank/file
			What do you think? : 
			../frank/file
			..
			rel. path u() : ..\frank\file
			rel. path m() : ..\..\..\frank\file		
		
			enter path 1 : 
			tmp1/file
			enter path 2 : 
			file
			What do you think? : 
			../file
			..
			rel. path u() : ..\file
			rel. path m() : ..\..\file		
		
			enter path 1 : 
			/home
			enter path 2 : 
			/home/file
			What do you think? : 
			file
			..
			rel. path u() : file
			rel. path m() : file
			same? : true		
		
		
		*/
	}
	
	
}
