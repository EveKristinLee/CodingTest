package solving.solve_0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 배열_돌리기_4_17406 {

	static int N;
	static int M;
	static int K;
	static int[][] map;
	static ArrayList<Integer[]> arr;
	static int[] sort;
	static int[][] copy;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1}; //하,우, 상, 좌
	
	public static boolean isInside(int x, int y) {
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
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		arr = new ArrayList<>();
		sort = new int[K];
		int res = Integer.MAX_VALUE;
		
		for(int i=0; i<K; i++) {
			sort[i] = i;
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			arr.add(new Integer[] {Integer.parseInt(st.nextToken())-1, 
									Integer.parseInt(st.nextToken())-1, 
									Integer.parseInt(st.nextToken())});
		}
		
		Arrays.sort(sort);
		do {
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, copy[i], 0, M);
			}
			//순서 완료
			for(int i=0; i<sort.length; i++) {
				//돌리기
				//돌리는 한 팀의 개수
				int r = arr.get(sort[i])[0];
				int c = arr.get(sort[i])[1];
				int s = arr.get(sort[i])[2];
				int sx = r-s, sy = c-s;
				int ex = r+s, ey = c+s;
				int cnt = Math.min(ex - sx + 1, ey - sy +1) /2;
				turn(cnt, sx, sy, ex, ey, copy);
			}
			
			res = Math.min(res, calcMin(copy));
		}while(np(sort));
		
		System.out.println(res);
	}

	private static int calcMin(int[][] map) {
		int minNum = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum += map[i][j];
			}
			minNum = Math.min(minNum, sum);
		}
		return minNum;
	}

	private static void turn(int cnt, int sx, int sy, int ex, int ey, int[][] map) {
		for(int i=0; i<cnt; i++) {
			int x = sx+i;
			int y = sy+i;
			int dir = 0;
			int tmp = map[sx+i][sy+i];
			while(dir < 4) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx>=sx+i && nx <= ex-i && ny>=sy+i && ny<=ey-i) {
					map[x][y] = map[nx][ny];
					x = nx;
					y = ny;
				}
				else {
					dir++;
				}
			}
			map[sx+i][sy+i+1] = tmp;
		}
	}

	private static boolean np(int[] num) {
		int n = num.length -1;
		int i= n;
		while(i>0 && num[i-1] >= num[i]) --i;
		if(i==0) {
			return false;
		}
		int j=n;
		while(num[i-1]>=num[j]) --j;
		swap(num, i-1, j);
		int k=n;
		while(i<k) swap(num, i++, k--);
		return true;
	}

	private static void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
