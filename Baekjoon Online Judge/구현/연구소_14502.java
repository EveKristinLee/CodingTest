package solving.solve_0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_14502 {

	static int N;
	static int M;
	static int[][] map;
	static List<Integer[]> node;
	static int[] subset;
	static int res = Integer.MIN_VALUE;
	static Queue<Integer[]> virusPos;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		node = new ArrayList<>();
		subset = new int[3];
		virusPos = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					node.add(new Integer[] {i, j});
				}
				else if(map[i][j] == 2) {
					virusPos.add(new Integer[] {i, j});
				}
			}
		}
		makeCombi(0, 0);
		System.out.println(res);
	}

	private static void makeCombi(int cnt, int start) {
		if(cnt == 3) {
			makeWall();
			return;
		}
		
		for(int i=start; i<node.size(); i++) {
			subset[cnt] = i;
			makeCombi(cnt+1, i+1);		
		}
		
	}

	private static void makeWall() {
		//map깊은 복사
		int[][] tmpMap = new int[N][M];
		for(int i=0; i<map.length; i++) {
			System.arraycopy(map[i], 0, tmpMap[i], 0, tmpMap[i].length);
		}
		
		//선택한 위치 벽 생성
		for(int i=0; i<3; i++) {
			int tmpX = node.get(subset[i])[0];
			int tmpY = node.get(subset[i])[1];
			tmpMap[tmpX][tmpY] = 1;
		}
		
		virus(tmpMap);
		 
	}

	private static void virus(int[][] map) {
		Queue<Integer[]> q = new LinkedList<>(virusPos);
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(map[nx][ny] == 0) {
						map[nx][ny] = 2;
						q.offer(new Integer[] {nx, ny});
					}
				}
			}
		}
		
		//안전 영역 개수 세기
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					cnt++;
				}
			}
		}
		res = Math.max(res, cnt);
	}
}
