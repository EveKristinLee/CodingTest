package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문서_검색_1543 {

	static String input;
	static String find;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		find = br.readLine();
		cnt= 0; 
		
		chkCnt();
		System.out.println(cnt);
	}
	
	private static void chkCnt() {
		if(input.length() >= find.length()) {
			String tmp = input.substring(0, find.length());
			if(tmp.equals(find)) {
				cnt++;
				input = input.substring(find.length());
				input = input.replace(tmp, "");
			}
			else {
				input = input.substring(1);
			}
			chkCnt();
		}
	}
}
