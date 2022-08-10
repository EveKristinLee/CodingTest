package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로_탈출_로봇_1661_DFS {

	static char[][] map;
	static int[][] visit;
	static int X; //가로
	static int Y; //세로
	static int startX, startY;
	static int endX, endY;
	static int time = Integer.MAX_VALUE;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int y, int x) {
		if(y<0 || x<0 || y>=Y || x>=X) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		visit = new int[Y][X];
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		for(int i=0; i<Y; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
			for(int j=0; j<X; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
	
		visit[startY-1][startX-1] = 0;
		dfs(startY-1, startX-1, 0);
		System.out.println(time);
	}

	private static void dfs(int curY, int curX, int curTime) {
		if(curX == endX-1 && curY == endY-1) {
			time = Math.min(time, curTime);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int newY = curY + dy[i];
			int newX = curX + dx[i];
			if(isInside(newY, newX) && map[newY][newX] == '0') {
				if(visit[newY][newX] > curTime + 1) {
					visit[newY][newX] = curTime + 1;
					dfs(newY, newX, curTime+1);
				}
			}
		}
	}
}
