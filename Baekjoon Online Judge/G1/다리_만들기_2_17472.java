package solving.solve_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리_만들기_2_17472 {

	static int n; //세로
	static int m; //가로
	static int[][] map;
	static int minLen = Integer.MAX_VALUE;
	static int totalLen = 0;
	static boolean[][] visit;
	static int idx = 0;
	static int[] p;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static int[] dx = {0, 1, 0, -1}; //우 -> 하 -> 좌 -> 상 
	static int[] dy = {1, 0, -1, 0};
	
	public static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) {
			return false;
		}
		return true;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visit = new boolean[n][m];
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//섬 번호 메기기
		idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1 && !visit[i][j]) {
					idx++;
					bfs(i, j);
				}
			}
		}
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		visit = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0) {
					makeBridge(new Integer[] {i, j}, map[i][j]);
				}
			}
		}
		p = new int[idx+1];
		for(int i=1; i<idx+1; i++) {
			p[i] = i;
		}
		
		int size = pq.size();
		int cnt = 0;
		for(int i=0; i<size; i++) {
			Edge tmp = pq.poll();
			
			if(find(tmp.start) != find(tmp.end)) {
				union(tmp.start, tmp.end);
				cnt++;
				totalLen += tmp.len;
				if(cnt == idx -1) {
					break;
				}
			}
		}
		
		if(cnt != idx-1 || totalLen == 0) {
			System.out.println("-1");
		}
		else {
			System.out.println(totalLen);
		}
	}

	private static void makeBridge(Integer[] dot, int num) {
		int x = dot[0];
		int y = dot[1];
		int len = 0;
		
		for(int i=0; i<4; i++) {
			while(true) {
				x = x+dx[i];
				y = y+dy[i];
				if(isInside(x, y)) {
					if(map[x][y] == num) { //같은 섬
						len = 0;
						x = dot[0];
						y = dot[1];
						break;
					}
					else if(map[x][y] == 0) {
						len++;
					}
					else {
						if(len > 1) {
							pq.offer(new Edge(num, map[x][y], len));
						}
						len = 0;
						x = dot[0];
						y = dot[1];
						break;
					}
				}
				else { //경계 밖이면
					len = 0;
					x = dot[0];
					y = dot[1];
					break;
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		map[x][y] = idx;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(isInside(nx, ny) && map[nx][ny] == 1 && !visit[nx][ny]) {
					q.offer(new Integer[] {nx, ny});
					map[nx][ny] = idx;
					visit[nx][ny] = true;
				}
			}
		}
 	}
	
	public static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int len;
		public Edge(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}
		@Override
		public int compareTo(Edge o) {
			return len - o.len;
		}
	}

}
