package solving.solve_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로_1753 {
	public static class Node implements Comparable<Node>{
		int idx;
		int weight;
		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	static int v;
	static int e;
	static int start;
	static ArrayList<Node>[] link;
	static int[] d;
	static PriorityQueue<Node> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		link = new ArrayList[v+1];
		d = new int[v+1];
		q = new PriorityQueue<>();
		
		for(int i=1; i<=v; i++) {
			link[i] = new ArrayList<>();
		}

		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			link[from].add(new Node(to, weight));
		}
		Arrays.fill(d, Integer.MAX_VALUE);
		d[start] = 0;
		q.offer(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i=0; i<link[cur.idx].size(); i++) {
				int nextIdx = link[cur.idx].get(i).idx;
				int nextWeight = link[cur.idx].get(i).weight;
				if(d[nextIdx] > d[cur.idx] + nextWeight) {
					d[nextIdx] = d[cur.idx] + nextWeight;
					q.offer(new Node(nextIdx, d[nextIdx]));
				}
			}
		}
		for(int i=1; i<=v; i++) {
			if(d[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(d[i]);				
			}
		}
	}
}
