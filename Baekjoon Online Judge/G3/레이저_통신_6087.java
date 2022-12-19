package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ G3
public class 레이저_통신_6087 {
	
	private static class Node implements Comparable<Node>{
		int x;
		int y;
		int dir;
		int cnt;
		
		public Node(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}

	static int W; //가로
	static int H; //세로
	static int sx, sy;
	static char[][] map;
	static int[][] visit;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=H || y>=W) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visit = new int[H][W];
		
		for(int i=0; i<H; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
			for(int j=0; j<W; j++) {
				if(map[i][j] == 'C') {
					sx = i;
					sy = j;
				}
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		bfs();
	}

	private static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(sx, sy, -1, 0));
		visit[sx][sy] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if((now.x != sx || now.y != sy) && map[now.x][now.y] == 'C') {
				System.out.println(now.cnt -1);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(isInside(nx, ny) && map[nx][ny] != '*') {
					int tmp = now.cnt;
					if(now.dir != i) {
						tmp++;
					}
					if(visit[nx][ny] >= tmp) {
						visit[nx][ny] = tmp;
						pq.offer(new Node(nx, ny, i, tmp));
					}
				}
			}
		}
	}
}
