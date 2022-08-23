package solving.solve_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//SWEA D4
public class 최소_스패닝_트리_3124 {
	
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}
	
	static int v;
	static int e;
	static int[] p;
	static ArrayList<Edge> edge;
	
	public static void make() {
		for(int i=1; i<=v; i++) {
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
		
		if(aRoot == bRoot) return false;
		
		p[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			p = new int[v+1];
			edge = new ArrayList<>();
			
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				edge.add(new Edge(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()), 
									Integer.parseInt(st.nextToken())));
			}
			Collections.sort(edge);
			
			make();
			long sum = 0;
			int cnt = 0;
			for (Edge e : edge) {
				if(union(e.from, e.to)) {
					sum += e.weight;
					cnt++;
					if(cnt == v-1) {
						break;
					}
				}
			}
			sb.append("#"+t+" "+sum).append("\n");
		}
		System.out.println(sb);
	}
}
