package solving.solve_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구_이동_16234 {

	static int n; //map의 크기
	static int l; //ㅣ명 이상
	static int r; //r명 이하
	static int[][] map;
	static boolean[][] visit;
	static boolean flag;
	static ArrayList<Integer[]> list;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=n) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visit = new boolean[n][n];
		flag = true;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int date = 0;
		int sum = 0;
		while(true) {
			for(int i=0; i<n; i++) {
				Arrays.fill(visit[i], false);
			}
			boolean flag = false; //false면 인구이동 X
			date++;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visit[i][j]) {
						//cnt = 0; //사람 수
						list = new ArrayList<>();
						sum = bfs(i, j);
						if(list.size() > 1) {
							flag = true;
							int tmp = sum/list.size();
							for(int k=0; k<list.size(); k++) {
								map[list.get(k)[0]][list.get(k)[1]] = tmp;
							}
						}
					}
				}
			}
			if(!flag) {
				break;
			}
		}
		System.out.println(date-1);
	}
	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		int sum = 0;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			sum += map[x][y];
			list.add(new Integer[] {x, y});
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && Math.abs(map[x][y] - map[nx][ny]) >= l && Math.abs(map[x][y] - map[nx][ny]) <= r) {
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
				}
			} 
		}
		return sum;
	} 	 
}
