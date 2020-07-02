package infix;

import java.util.Scanner;
import java.util.Stack;

public class Parser {
	
	public static String Parse (String problem) {
		String answer = "";
		
		// Make a stack from the problem
        	Stack<Character> problemStack = new Stack<>();
        	for (int i = 0; i < problem.length(); i++) { 
        		problemStack.push(problem.charAt(i));}
        	
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Get problem from user
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
