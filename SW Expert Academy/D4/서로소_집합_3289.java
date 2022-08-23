package solving.solve_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA D4
public class 서로소_집합_3289 {

	static int n;
	static int m;
	static int[] p;
	
	public static void make() {
		for(int i=1; i<=n; i++)  {
			p[i] = i;
		}
	}
	
	public static int find(int a) {
		if(p[a] == a) {
			return a;
		}
		return p[a] = find(p[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		p[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			p = new int[n+1];
			StringBuilder res = new StringBuilder();
			
			make();
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(c == 0) {
					union(a, b);
				}
				else if(c == 1) {
					if(find(a) == find(b)) {
						res.append(1);
					}
					else {
						res.append(0);
					}
				}
			}
			sb.append("#"+t+" "+res).append("\n");
		}
		System.out.println(sb);
	}

}
