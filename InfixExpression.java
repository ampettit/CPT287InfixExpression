package infix;

import java.util.Scanner;

public class Parser {
	
	public static String Parse (String problem) {
		String answer = "";
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println ("Enter your problem:");
		String problem = scan.next();
		
		// Remove whitespace and make letters lower case
		problem = problem.replaceAll("\\s+","");
		problem = problem.toLowerCase();
	
		// Run module and print answer
		System.out.print(Parse(problem));
		
		// Close scanner
		scan.close();
	}
}
