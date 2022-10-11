package solving.solve_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//BOJ P5
public class 찾기_1786 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tlen = text.length;
		int plen = pattern.length;
		
		int[] fail = new int[plen];
		
		for(int i=1, j=0; i<plen; i++) {
			
			while(j>0 && pattern[i] != pattern[j]) {
				j = fail[j-1];
			}
			if(pattern[i] == pattern[j]) {
				fail[i] = ++j;
			}
		}
		
		int cnt = 0;
		List<Integer> list = new ArrayList<>();
		for(int i=0, j=0; i<tlen; i++) {
			
			while(j>0 && text[i] != pattern[j]) {
				j = fail[j-1];
			}
			
			if(text[i] == pattern[j]) {
				if(j == plen-1) {
					cnt++;
					list.add((i+1) - plen + 1);
					j = fail[j];
				}
				else {
					j++;
				}
			}
		}
		
		System.out.println(cnt);
		if(cnt >0) {
			for (Integer i : list) {
				System.out.println(i);
			}
		}
	}

}
