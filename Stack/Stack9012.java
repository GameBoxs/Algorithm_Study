package practice.Backjoon.Control;

import java.util.Scanner;
import java.util.Stack;
/*
 * Question Name 	:	괄호
 * Question Number 	:	9012
 * Question Tier	:	Silver4
 * Question Link	:	https://www.acmicpc.net/problem/9012
 */
public class Stack9012 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Character> s = new Stack<Character>();
		Scanner sc = new Scanner(System.in);
		String str;
		int T = sc.nextInt();
		for(int t=0; t<T; t++)
		{
			str = sc.next();
			String result = "YES";
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '(') {
					s.push('(');
				}
				else {
					if(s.isEmpty()) {
						result = "NO";
						break;
					}
					else {
						s.pop();
					}
				}
			}
			if(!s.isEmpty()) {
				result = "NO";
				while(!s.isEmpty()) {
					s.pop();
				}
			}
			
			System.out.println(result);
		}
	}

}
