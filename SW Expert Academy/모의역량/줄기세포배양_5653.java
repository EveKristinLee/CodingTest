package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 모의
public class 줄기세포배양_5653 {
	
	public static class Cell implements Comparable<Cell>{
		int x;
		int y;
		int time;
		int survive;
		int active;
		
		public Cell(int x, int y, int time, int survive, int active) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.survive = survive;
			this.active = active;
		}

		@Override
		public int compareTo(Cell o) {
			return o.time - this.time;
		}
	}
	
	static int N; //세포가 분포된 세로 크기
	static int M; //가로크기
	static int K; //배양 시간
	static int [][] map;
	static boolean[][] visit;
	static PriorityQueue<Cell> pq;
	static int res;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			N = (n + K) * 3;
			M = (m + K) * 3;
			map = new int[N][M];
			visit = new boolean[N][M];
			pq = new PriorityQueue<>();
			for(int i=(N/2)-1; i<(N/2)-1 + n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=(M/2)-1; j<(M/2)-1+m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {
						pq.offer(new Cell(i, j, map[i][j], map[i][j], map[i][j]));
						visit[i][j] = true;
					}
				}
			}
			
			res = bfs();
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static int bfs() {
		while(K-- > 0) {
			Queue<Cell> tmp = new ArrayDeque<>();
			while(!pq.isEmpty()) {
				Cell top = pq.poll();
				
				if(top.survive > 0 && top.active == 0) {
					for(int i=0; i<4; i++) {
						int nx = top.x + dx[i];
						int ny = top.y + dy[i];
						if((nx >= 0 && ny >= 0) && !visit[nx][ny]) {
							visit[nx][ny] = true;
							tmp.offer(new Cell(nx, ny, map[top.x][top.y], map[top.x][top.y], map[top.x][top.y]));
							map[nx][ny] = map[top.x][top.y];
						}
					}
					top.survive--;
				}
				if(top.active > 0) {
					top.active--;
				}
				if(top.survive > 0) {
					tmp.offer(new Cell(top.x, top.y, top.time, top.survive, top.active));
				}
			}
			pq.addAll(tmp);
		}
		
		return pq.size();
	}
}
