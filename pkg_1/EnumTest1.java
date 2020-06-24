package pkg_1;
enum SyslogSeverity {
	EMERGENCY, ALERT, CRITICAL, ERROR, WARNING, NOTICE, INFORMATIONAL, DEBUG
}
class EnumTest1 {

	enum WEEKDAY { MON, TUE, WED, THU, FRI, SAT, SUN }

	public static void main(String[] args){

		for ( SyslogSeverity ss : SyslogSeverity.values() ){
			System.out.println("# " + ss.ordinal() + ", " + ss );

		}

	}

	static void log(String msg, SyslogSeverity ss){
		System.out.println( ss + " : " + msg );

		switch( ss ){
			case EMERGENCY: case ERROR:
			//case SyslogSeverity.NOTICE:
						// DNC 23: error: an enum switch case label must be the unqualified name of an enumeration constant
			default:
		}

	}

}
