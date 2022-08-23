package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 창용_마을_무리의_개수_7465 {

	static int n; //사람의 수
	static int m; //관계의 수
	static int[] p;
	
	private static void make() {
		p = new int[n+1];
		for(int i=1; i<=n; i++) {
			p[i] = i;
		}
	}
	
	private static int find(int a) {
		if(p[a] == a) {
			return a;
		}
		return p[a] = find(p[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		p[bRoot] = aRoot;
		return true;
	}
	 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			make();
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			int cnt = 0;
			Set<Integer> s = new HashSet<>();
			for(int i=1; i<=n; i++) {
				s.add(find(i));
			}
			sb.append("#"+t+" "+s.size()+"\n");
		}
		System.out.println(sb);
	}

}