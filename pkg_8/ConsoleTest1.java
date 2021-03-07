/**
 * 
 */
package pkg_8;

import java.io.Console;
import java.util.Arrays;

/**
 * 
 * Test the console class
 */
public class ConsoleTest1 {

	private Console console = System.console();
	{
		if ( console == null ) {
			System.out.println("Console not available!");
			System.exit(-1);
		}
	}

	private boolean authenticated = false;
	private String userNameStored = "admin";
	private char[] passwordStored = new char[] { 'P','a','$','$','w','0','r','d' };
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleTest1 ct1 = new ConsoleTest1();
		System.out.println("--ConsoleTest1--------");
		ct1.commandLoop();

	}

	private void commandLoop() {
		cloop: while( true ) {
			console.flush();
			String cmd = console.readLine();
			switch (cmd) {
				case "help" : printMenu(); 
					break;
				 
				case "authenticate" : authenticate(); 
					break;
					
				case "exit" : break cloop;
				default: System.out.println("Unknown command, use help to see available commands.");
			}
		}
	
		System.out.println("end.");
	}
	
	private void printMenu() {
		System.out.println("--Help--------");
		System.out.println("help: Prints this Menu.");
		System.out.println("authenticate: Login with your credentials.");
		System.out.println("exit: Exits the progamm.");
	}
	
	private void authenticate() {
		console.flush();
		System.out.println("Username:	");
		String userName = console.readLine();
		System.out.println("Password:	");
		char[] password = console.readPassword();
		
		if ( userName.equals(userNameStored) && Arrays.equals(password, passwordStored) ) {
			this.authenticated = true;
			System.out.println("authentication succeeded!");
		}else {
			System.out.println("authentication failed!");
		}
		
	} 
	
}
