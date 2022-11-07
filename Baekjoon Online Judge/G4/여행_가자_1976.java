package solving.solve_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ G4
public class 여행_가자_1976 {
	
	static int N; //도시의 수
	static int M; //여행계획에 속한 도시들의 수
	static int[][] map;
	static int[] root;
	static int[] p;
	
	private static void make() {
		p = new int[N+1];
		for(int i=1; i<=N; i++) {
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
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb) {
			return false;
		}
		p[pb] = pa;
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		make();
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					union(i, j);
				}
			}
		}
		
		root = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			root[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		for(int i=1; i<M; i++) {
			if(find(root[i-1]) != find(root[i])) {
				flag = false;
				break;
			}
		}

		if(flag) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
	}

}
