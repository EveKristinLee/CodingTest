package solving_2.solve_12.solve_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G3
public class 연구소_3_17142 {
	
	private static class VIRUS {
		int x;
		int y;
		boolean active;
		
		public VIRUS(int x, int y, boolean active) {
			super();
			this.x = x;
			this.y = y;
			this.active = active;
		}
	}
	
	static int N; //연구소 크기
	static int M; //놓을 수 있는 바이러스의 개수
	static int[][] map;
	static List<VIRUS> virus;
	static VIRUS[] select;
	static int res;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	//0=빈칸, 1=벽, 2=바이러스
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		select = new VIRUS[M];
		res = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new VIRUS(i, j, false));
				}
			}
		}
		
		//활성화 시킬 바이러스 M개 뽑기
		combi(0, 0);
		
		if(res == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			//선택 완료
			//바이러스 퍼뜨리기
			int tmp = goVirus();
			if(tmp != -1) {
				res = Math.min(tmp, res);
			}
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			select[cnt] = virus.get(i);
			combi(cnt+1, i+1);
		}
	}

	private static int goVirus() {
		int[][] tmp = copy(); //원본 복사
		Queue<VIRUS> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];
		
		//선택한 바이러스 활성화
		for(int i=0; i<M; i++) {
			int x = select[i].x;
			int y = select[i].y;
			tmp[x][y] = 3;
			q.offer(new VIRUS(x, y, true));
			visit[x][y] = true;
		}
		
		int time = 0;
		boolean notClear = true;
		//바이러스 퍼뜨리기
		while(true) {
			int size = q.size();
			if(size == 0) {
				break;
			}
			if(allChk(tmp)) {
				notClear = false;
				break;
			}
			for(int s=0; s<size; s++) {
				VIRUS top = q.poll();
				
				for(int i=0; i<4; i++) {
					int nx = top.x + dx[i];
					int ny = top.y + dy[i];
					if(isInside(nx, ny) && !visit[nx][ny]) {
						if(tmp[nx][ny] != 1) {
							visit[nx][ny] = true;
							q.offer(new VIRUS(nx, ny, true));
							tmp[nx][ny] = 3;
						}
					}
				}
			}
			time++;
		}
		
		if(!notClear) {
			return time;
		}
		else {
			return -1;
		}
	}

	private static boolean allChk(int[][] tmp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tmp[i][j]==0) {//바이러스 못 퍼뜨림
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] copy() {
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
		}
		return tmp;
	}
}
