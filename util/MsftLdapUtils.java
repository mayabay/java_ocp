/**
 * Utility methods for retrieving information for microsofts ActiveDirectory
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MsftLdapUtils {

//	public static final int UF_ACCOUNT_DISABLE = 2;
//	public static final int UF_HOMEDIR_REQUIRED = 4;
//	public static final int UF_LOCKOUT = 16;
//	public static final int UF_PASSWD_NOTREQD = 32;
//	public static final int UF_PASSWD_CANT_CHANGE = 64;
//	public static final int UF_ENCRYPTED_TEXT_PASSWORD_ALLOWED = 128;
//	public static final int UF_NORMAL_ACCOUNT = 512;
//	public static final int UF_INTERDOMAIN_TRUST_ACCOUNT = 2048;
//	public static final int UF_WORKSTATION_TRUST_ACCOUNT = 4096;
//	public static final int UF_SERVER_TRUST_ACCOUNT = 8192;
//	public static final int UF_DONT_EXPIRE_PASSWD = 65536 ;
//	public static final int UF_MNS_LOGON_ACCOUNT = 131072;
//	public static final int UF_SMARTCARD_REQUIRED = 262144;
//	public static final int UF_TRUSTED_FOR_DELEGATION = 524288 ;
//	public static final int UF_NOT_DELEGATED = 1048576 ;
//	public static final int UF_USE_DES_KEY_ONLY = 2097152 ;
//	public static final int UF_DONT_REQUIRE_PREAUTH = 4194304 ;
//	public static final int UF_PASSWORD_EXPIRED = 8388608  ;
//	public static final int UF_TRUSTED_TO_AUTHENTICATE_FOR_DELEGATION = 16777216 ;
//	public static final int UF_NO_AUTH_DATA_REQUIRED = 33554432;
//	public static final int UF_PARTIAL_SECRETS_ACCOUNT = 67108864;
	
	/**
	 * Converts decimal value of userAccountControl to a List of USER_ACCOUNT_CONTROL_FLAG instances
	 * @param userAccountControlDecValue decimal value of userAccountControl
	 * @return List<USER_ACCOUNT_CONTROL_FLAG>
	 * */
	public static List<USER_ACCOUNT_CONTROL_FLAG> getUserAccountFlagList( int userAccountControlDecValue ) {
		List<USER_ACCOUNT_CONTROL_FLAG> list = new ArrayList<>();
		
		for ( USER_ACCOUNT_CONTROL_FLAG f : USER_ACCOUNT_CONTROL_FLAG.values() ) {
			if ( hasFlag( userAccountControlDecValue, f ) )
				list.add(f);
		}
		
		return list;
	}
	
	/*
	 * https://drumcoder.co.uk/blog/2010/jan/06/bitmasks-java/
	 * L-AXO -> AND XOR OR  
	 */
	private static boolean hasFlag( int v, USER_ACCOUNT_CONTROL_FLAG flag ) {
		int flagValue = flag.getDecimalValue();
		return ( (v & flagValue) == flagValue );
	}
	
	/**
	 * Checks list of flags for a given value
	 * @param list of USER_ACCOUNT_CONTROL_FLAG value
	 * @param USER_ACCOUNT_CONTROL_FLAG constant
	 * @return true, if flag is set
	 * */
	public static boolean isSet(USER_ACCOUNT_CONTROL_FLAG f,  List<USER_ACCOUNT_CONTROL_FLAG> list ) {
		return list.contains(f);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("512 : " +  getUserAccountFlagList(512) );
		System.out.println("514 : " +  getUserAccountFlagList(514) );
		System.out.println("592 : " +  getUserAccountFlagList(592) );
		System.out.println("1 : " +  getUserAccountFlagList(1) );
		System.out.println("67108864 : " +  getUserAccountFlagList(67_108_864) );
		System.out.println("8389136 : " +  getUserAccountFlagList(8_389_136) );
		
		
		System.out.println("...");
		
		System.out.println("8389136");
		printBitPatternsOfFlags( 8_389_136, getUserAccountFlagList(8_389_136)  );
		
		System.out.println("...");
		
		if ( isSet(USER_ACCOUNT_CONTROL_FLAG.UF_ACCOUNT_DISABLE, getUserAccountFlagList(514)) ) {
			System.out.println("514 : " + "this account is disabled.");
		}
		
	}

	private static void printBitPatternsOfFlags(int v,  List<USER_ACCOUNT_CONTROL_FLAG> list) {
		list.stream()
		.forEach( f -> {
			System.out.println(f.name());
			System.out.printf("pattern1 : %1$32s\n", Integer.toBinaryString(v) );
			System.out.printf("pattern2 : %1$32s\n", Integer.toBinaryString(f.getDecimalValue()) );		
			System.out.println();
		} );
	} 
	
}
