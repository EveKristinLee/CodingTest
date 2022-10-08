package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가르침_1062 {
	
	static int n;
	static int k;
	static String[] words;
	static boolean[] alpha;
	static char[] rest;
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		words = new String[n];
		alpha = new boolean[26];
		maxCnt = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			s.replace("anta", "");
			s.replace("tica", "");
			words[i] = s;
		}
		
		if(k < 5) {
			System.out.println(0);
			return;
		}
		if(k >= 26) {
			System.out.println(n);
			return;
		}
		
		alpha['a' - 'a'] = true;
		alpha['n' - 'a'] = true;
		alpha['t' - 'a'] = true;
		alpha['i' - 'a'] = true;
		alpha['c' - 'a'] = true;
		
		combi(0, 0);
		System.out.println(maxCnt);
	}

	private static void combi(int cnt, int idx) {
		if(cnt == k-5) {
			int nowCnt = 0;
			//읽을 수 있는 단어 개수 세기
			for(int i=0; i<n; i++) {
				boolean flag = true;
				for(int j=0; j<words[i].length(); j++) {
					if(!alpha[words[i].charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}
				if(flag) {
					nowCnt++;
				}
			}
			maxCnt = Math.max(maxCnt, nowCnt);
			return;
		}
		
		for(int i=idx; i<26; i++) {
			if(!alpha[i]) {
				alpha[i] = true;
				combi(cnt+1, i+1);
				alpha[i] = false;
			}
		}
	}
}
