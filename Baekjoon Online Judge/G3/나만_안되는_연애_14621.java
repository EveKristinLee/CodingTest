package solving.solve_1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ G3
public class 나만_안되는_연애_14621 {
	
	public static class Node implements Comparable<Node>{
		int from;
		int to;
		int dist;
		
		public Node(int from, int to, int dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return dist - o.dist;
		}
	}
	
	static int N; //학교의 수
	static int M; //학교를 연결하는 도로의 개수
	
	static char[] sch; //학교 저장 배열
	static Node[] edges;
	static int res;
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
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) {
			return false;
		}
		
		p[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sch = new char[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sch[i] = st.nextToken().charAt(0);
		}
		edges = new Node[M];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Node(Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()));
		}
		
		int res = 0;
		make();
		Arrays.sort(edges);
		int cnt = 0;
		for (Node n : edges) {
			if(sch[n.from] != sch[n.to]) {
				if(union(n.from, n.to)) {
					
					res += n.dist;
					cnt++;
					if(cnt == N-1) {
						break;
					}
				}
			}
		}
		if(cnt < N-1) {
			System.out.println(-1);
		}
		else {
			System.out.println(res);
		}
	}

}
