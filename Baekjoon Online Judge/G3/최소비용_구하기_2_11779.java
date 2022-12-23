package solving_2.solve_12.solve_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G3
public class 최소비용_구하기_2_11779 {

	static int N; //도시의 개수
	static int M; //버스의 개수
	static List<Integer[]>[] graph;
	static int start, end;
	static int[] d;
	static boolean[] visit;
	static int[] route; //직전 노드 저장
	static List<Integer> routes;
	
	
	private static void init() {
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		d = new int[N+1];
		visit = new boolean[N+1];
		route = new int[N+1];
		routes = new ArrayList<>();
		init();
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[s].add(new Integer[] {d, c});
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		getRoute();
		System.out.println(d[end]);
		System.out.println(routes.size());
		for(int i=routes.size()-1; i>=0; i--) {
			System.out.print(routes.get(i)+" ");
		}
	}

	private static void getRoute() {
		int cur = end;
		while(cur != 0) {
			routes.add(cur);
			cur = route[cur];
		}
	}

	private static void dijkstra() {
		Queue<Integer[]> q = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		q.offer(new Integer[] {start, 0});
		d[start] = 0;
		route[start] = 0;
		
		while(!q.isEmpty()) {
			Integer[] now = q.poll();
			
			if(!visit[now[0]]) {
				visit[now[0]] = true;
			}
			else {
				continue;
			}
			
			for(Integer[] next : graph[now[0]]) {
				if(d[next[0]] > d[now[0]] + next[1]) {
					d[next[0]] = d[now[0]] + next[1];
			 		q.offer(new Integer[] {next[0], d[next[0]]});
			 		route[next[0]] = now[0];
				}
			}
		}
	}
}
