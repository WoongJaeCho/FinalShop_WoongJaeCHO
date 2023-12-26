package util;

import java.util.Scanner;

public class Util {
	private static Scanner scan = new Scanner(System.in);

	public static void closeScanner() {
		scan.close();
	}

	public static String getValue(String msg) {
		System.out.printf("▶ %s입력 : ",msg); 
		return scan.next();
	}
	
	public static int getValue(String msg, int start, int end) {
		while(true) {
			System.out.printf("▶ %s[%d-%d] 입력 : ",msg,start,end);
			try {
				int input = scan.nextInt();
				if(input<start||input>end) {
					continue;
				}
				return input;
			} catch (Exception e) {
				continue;
			} finally {
				scan.nextLine();
			}
		
		}
	}
}
