package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로_탈출_로봇_1661 {

	static char[][] map;
	static boolean[][] visit;
	static int X; //가로
	static int Y; //세로
	static int startX, startY;
	static int endX, endY;
	static int time = 0;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int y, int x) {
		return y>=0 && x>=0 && y<Y && x<X;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		visit = new boolean[Y][X];
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		for(int i=0; i<Y; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
	
		bfs(startY-1, startX-1, 0);
	}

	private static void bfs(int nowY, int nowX, int nowTime) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		
		q.offer(new Integer[] {nowY, nowX, time});
		visit[nowY][nowX] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			nowY = top[0];
			nowX = top[1];
			nowTime = top[2];

			if(nowY == endY-1 && nowX == endX-1) {
				System.out.println(nowTime);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int newY = nowY + dy[i];
				int newX = nowX + dx[i];
				if(isInside(newY, newX)) {
					if(map[newY][newX] == '0' && !visit[newY][newX]) {
						visit[newY][newX] = true;
						q.offer(new Integer[] {newY, newX, nowTime+1});
					}
				}
			}
		}
	}
}
