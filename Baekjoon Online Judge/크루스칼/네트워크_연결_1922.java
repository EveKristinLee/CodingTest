package algo_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 네트워크_연결_1922 {

	public static class Computer implements Comparable<Computer> {
		int start;
		int end;
		int cost;
		public Computer(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Computer o) {
			return cost - o.cost;
		}
	}
	
	static int n;
	static int m;
	static ArrayList<Computer> link;
	static int[] p;
	
	private static void make() {
		p = new int[n+1];
		for(int i=0; i<n; i++) {
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		link = new ArrayList<>();
		int totalSum = 0;
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			link.add(new Computer(from, to, cost));
		}
		
		Collections.sort(link);
		make();
		int cnt = 0;
		for(int i=0; i<m; i++) {
			if(find(link.get(i).start) != find(link.get(i).end)) {
				cnt++;
				union(link.get(i).start, link.get(i).end);
				totalSum += link.get(i).cost;
				if(cnt == n-1) {
					break;
				}
			}
		}
		System.out.println(totalSum);
	}


}
