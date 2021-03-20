package util;

/**
 * List of account status flags for user objects in active directory
 * http://www.selfadsi.org/ads-attributes/user-userAccountControl.htm
 * 
 * */
public enum USER_ACCOUNT_CONTROL_FLAG {
	
	UF_ACCOUNT_DISABLE(2),
	UF_HOMEDIR_REQUIRED(4),
	UF_LOCKOUT(16),
	UF_PASSWD_NOTREQD(32),
	UF_PASSWD_CANT_CHANGE(64),
	UF_ENCRYPTED_TEXT_PASSWORD_ALLOWED(128),
	UF_NORMAL_ACCOUNT(512),
	UF_INTERDOMAIN_TRUST_ACCOUNT(2048),
	UF_WORKSTATION_TRUST_ACCOUNT(4096),
	UF_SERVER_TRUST_ACCOUNT(8192),
	UF_DONT_EXPIRE_PASSWD(65536),
	UF_MNS_LOGON_ACCOUNT(131072),
	UF_SMARTCARD_REQUIRED(262144),
	UF_TRUSTED_FOR_DELEGATION(524288),
	UF_NOT_DELEGATED(1048576),
	UF_USE_DES_KEY_ONLY(2097152),
	UF_DONT_REQUIRE_PREAUTH(4194304),
	UF_PASSWORD_EXPIRED(8388608),
	UF_TRUSTED_TO_AUTHENTICATE_FOR_DELEGATION(16777216),
	UF_NO_AUTH_DATA_REQUIRED(33554432),
	UF_PARTIAL_SECRETS_ACCOUNT(67108864); 	
	
	private int decimalValue;
	
	/**
	 * @return the decimal value of the flag
	 * */
	public int getDecimalValue() {
		return decimalValue;
	}
	
	private USER_ACCOUNT_CONTROL_FLAG( int decimalValue ) {
		this.decimalValue = decimalValue;
	}
	
}
