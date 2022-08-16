package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_4179 {
 
	static int r; //세로
	static int c; //가로
	static char[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static Queue<Integer[]> q;
	static List<Integer[]> jh;
	static boolean[][] visit;
	static int cnt = 0;
	
	static boolean isInside(int x, int y) {
		if( x<0||y<0||x>=r||y>=c) {
			return false;
		}
		return true;
	}
	
	static boolean isEdge(int x, int y) {
		if(x == 0 || y == 0 || x == r-1 || y == c-1) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visit = new boolean[r][c];
		q = new LinkedList<>();
		jh = new ArrayList<Integer[]>();
		
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
			for(int j=0; j<c; j++) {
				if(map[i][j] == 'J') {
					jh.add(new Integer[] {i, j, 1});
					visit[i][j] = true;
				}
				if(map[i][j] == 'F') {
					q.offer(new Integer[] {i, j});					
					visit[i][j] = true;
				}
			}
		}
		
		//불 먼저 큐에 넣고 지훈이 위치를 넣어야 불이 번지는 곳으로 지훈이가 이동하지 않는다. 
		for(int i=0; i<jh.size(); i++) {
			q.add(jh.get(i));
		}
		bfs();
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			int nc = -1;
			if(map[x][y] == 'J') {
				nc = top[2];
				
				//들어오자마자 나갈 수 있을때..체크 안해주는 방법은 없을까 싶다
				if(isEdge(x, y)) {
					System.out.println(nc);
					return;
				}
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				
				if(isInside(nx, ny) && !visit[nx][ny] && map[nx][ny] != '#') {
					if(map[x][y] == 'F') { 
						map[nx][ny] = 'F';
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
					if(map[x][y] == 'J') {
						cnt = nc;
						if(isEdge(nx, ny) && map[nx][ny] == '.') {
							System.out.println(cnt+1);
							return;
						}
						map[nx][ny] = 'J';
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny, nc+1});
					}
				}
			}
//			System.out.println("---------------");
//			for(int i=0; i<r; i++) {
//				for(int j=0; j<c; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		System.out.println("IMPOSSIBLE");
	}
}






























