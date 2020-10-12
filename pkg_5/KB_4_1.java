package pkg_5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 
 * */
public class KB_4_1 {

	public static void main(String[] args) {
		KB_4_1 obj = new KB_4_1();
		obj.now();
	}

	private void now() {
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.of(nowDate, nowTime);
		System.out.println("It is now: " + localDateTime);
		
	}
	
}
