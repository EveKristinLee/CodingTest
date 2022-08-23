package solving.solve_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//SWEA D4
public class 최소_스패닝_트리_Prim_3124 {
	
	static int v;
	static int e;
	static ArrayList<ArrayList<Integer[]>> link;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			link = new ArrayList<>();
			visit = new boolean[v+1];
			
			for(int i=0; i<=v; i++) {
				link.add(new ArrayList<>());
			}
			
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				link.get(a).add(new Integer[] {b, c});
				link.get(b).add(new Integer[] {a, c});				
			}
			long sum = 0;
			PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
				@Override
				public int compare(Integer[] o1, Integer[] o2) {
					return o1[0].compareTo(o2[0]);
				}
			});
			pq.offer(new Integer[] {0, 1});
			while(!pq.isEmpty()) {
				Integer[] top = pq.poll();
				int weight = top[0];
				int node = top[1];
				if(visit[node]) {
					continue;
				}
				visit[node] = true;
				sum += weight;
				
				for(int i=0; i<link.get(node).size(); i++) {
					pq.offer(new Integer[] {link.get(node).get(i)[1], link.get(node).get(i)[0]});
				}
			}
			sb.append("#"+t+" "+sum).append("\n");
		}
		System.out.println(sb);
	}
}
