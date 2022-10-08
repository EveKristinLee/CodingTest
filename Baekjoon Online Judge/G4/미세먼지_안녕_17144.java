package solving.solve_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미세먼지_안녕_17144 {

	static int r; //세로
	static int c; //가로
	static int t; //초
	static int[][] map;
	static boolean[][] visit;
	static int ux;
	static int bx;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=r || y>=c) {
			return false;
		}
		return true;
	}
//	
//	private static void printMap() {
//		for(int i=0; i<r; i++) {
//			for(int j=0; j<c; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		boolean first = true;
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(first) {
						ux = i;
						first = false;
					}
					else {
						bx = i;
					}
				}
			}
		}
		for(int tc=0; tc<t; tc++) {
			visit = new boolean[r][c];
			//미세먼지 퍼지기
			spread();
			//공기청정기 작동
			clean();
//			printMap();
		}
		System.out.println(count());
	}
	
	private static int count() {
		int total = 0;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] > 0) {
					total += map[i][j];
				}
			}
		}
		return total;
	}

	private static void clean() {
		//윗부분
		for(int i=ux-2; i>=0; i--) { //위에서 아래
			map[i+1][0] = map[i][0];
			map[i][0] = 0;
		}
		for(int i = 1; i<c; i++) { //왼쪽으로
			map[0][i-1] = map[0][i];
			map[0][i] = 0;
		}
		for(int i=1; i<=ux; i++) {
			map[i-1][c-1] = map[i][c-1];
			map[i][c-1] = 0;
		}
		for(int i = c-2; i>0; i--) { //오른쪽으로
			map[ux][i+1] = map[ux][i];
			map[ux][i] = 0;
		}
		
		//아랫부분
		
		for(int i=bx+1; i<r-1; i++) { //아래에서 위
			map[i][0] = map[i+1][0];
			map[i+1][0] = 0;
		}
		for(int i=0; i<c-1; i++) {
			map[r-1][i] = map[r-1][i+1];
			map[r-1][i+1] = 0;
		}
		for(int i=r-2; i>=bx; i--) {
			map[i+1][c-1] = map[i][c-1];
			map[i][c-1] = 0;
		}
		for(int i=c-2; i>0; i--) {
			map[bx][i+1] = map[bx][i];
			map[bx][i] = 0;
		}
	}

	private static void spread() {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		Queue<Integer> amount = new LinkedList<>();
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] > 0) {
					q.add(new Integer[] {i, j});
					amount.add(map[i][j]);
				}
			}
		}
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			int nowAmount = amount.poll();
			int cnt = 0;
			ArrayList<Integer[]> area = new ArrayList<>();
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny) && map[nx][ny] >= 0) {
					cnt++;
					area.add(new Integer[] {nx, ny});
				}
			}
			
			int sp = nowAmount/5;
			for(int i=0; i<area.size(); i++) {
				map[area.get(i)[0]][area.get(i)[1]] += sp;
			}
			map[x][y] -= (sp*cnt);
		}
	}
}
