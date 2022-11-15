package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 모의
public class 원자_소멸_시뮬레이션_5648 {
	
	public static class ATOM {
		int x;
		int y;
		int dir;
		int power;
		
		public ATOM(int x, int y, int dir, int power) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}
	}
	
	static int N; //원자들의 수
	static int[][] map = new int[4002][4002];
	static Queue<ATOM> q;
	static int res;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; //상하좌우
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>4000 || y>4000) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			q = new ArrayDeque<>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2; //각 좌표를 양수로 바꿔서 *2
				int x = 4000 - (Integer.parseInt(st.nextToken()) + 1000) * 2; //x가 0부터 시작하므로 뒤집기
				int dir = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				q.offer(new ATOM(x, y, dir, power));
				map[x][y] = power;
			}
			res = 0;
			bfs();
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			ATOM top = q.poll();
			
			if(map[top.x][top.y] != top.power) { //현재 위치의 에너지와 해당 위치의 에너지가 다르면 충돌
				res += map[top.x][top.y];
				map[top.x][top.y] = 0;
				continue; //이미 충돌했으므로 다음턴으로
			}
			
			int nx = top.x + dx[top.dir];
			int ny = top.y + dy[top.dir];
			
			if(isInside(nx, ny)) {
				if(map[nx][ny] == 0) {
					map[nx][ny] = top.power;
					q.offer(new ATOM(nx, ny, top.dir, top.power));
				}
				else {
					map[nx][ny] += top.power;
				}
			}
			map[top.x][top.y] = 0;
		}
	}
}