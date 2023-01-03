package solving_2.solve_01.solve_10;

import java.io.*;
import java.util.*;

//BOJ S3
public class 단어_뒤집기_2_17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> ch = new Stack<>();
		Stack<Character> tag = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '<') {
				while(!ch.isEmpty()) {
					sb.append(ch.pop());
				}
				tag.add(s.charAt(i));
				sb.append('<');
			}
			else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				if(!tag.isEmpty() && tag.peek() == '<') {
					sb.append(s.charAt(i));
				}
				else {
					ch.add(s.charAt(i));
				}
			}
			else if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				ch.add(s.charAt(i));
			}
			else if(s.charAt(i) == ' ') {
				if(!tag.isEmpty() && tag.peek() == '<') {
					sb.append(s.charAt(i));
				}
				else {
					while(!ch.isEmpty()) {
						sb.append(ch.pop());
					}
					sb.append(' ');
				}
			}
			else if(s.charAt(i) == '>') {
				sb.append('>');
				tag.pop();
			}
		}
		while(!ch.isEmpty()) {
			sb.append(ch.pop());
		}
		System.out.println(sb);
	}
}
