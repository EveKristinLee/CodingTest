package solving.solve_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 낚시왕_17143 {

	public static class Shark implements Comparable<Shark> {
		int r; //가로
		int c; //세로
		int s; //속력
		int d; //이동 방향
		int z; //크기
		int move; //움직인 횟수
		
		public Shark(int r, int c, int s, int d, int z, int move) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.move = move;
		}
		@Override
		public int compareTo(Shark o) {
			if(move == o.move) {
				return o.z - z;
			}
			else {
				return move - o.move;
			}
		}
		
		public void move(int cnt) {
			for(int i=0; i<cnt; i++) {
				int nx = r + dx[d];
				int ny = c + dy[d];
				if(!isInside(nx, ny)) {
					d = turn(d);
					nx = r + dx[d];
					ny = c + dy[d];
				}
				r = nx;
				c = ny;
			}
		}
		
		public int turn(int dir) {
			switch(dir) {
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 4; 
			case 4:
				return 3;
			}
			return -1;
		}
	}
	
	static int r; //세로
	static int c; //가로
	static int m; //상어의 수
	static int king = 0; //낚시왕의 열의 수
	static int sumSize = 0;
	static PriorityQueue<Shark>[][] map;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 1, -1};
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>r || y >c) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new PriorityQueue[r+1][c+1];
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				map[i][j] = new PriorityQueue<>();
			}
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			Shark shark = new Shark(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					0);
			map[shark.r][shark.c].add(shark);
		}

		while(king < c) {
			king++; //낚시왕이 오른쪽으로 한칸
			for(int i=1; i<=r; i++) { //제일 위에 열에 있는 상어 삭제
				if(map[i][king].size() >= 1) {
					sumSize += map[i][king].poll().z;
					break;
				}
			}
			
			//상어 이동
			int xRound = (r-1)*2; // 위 아래 이동
			int yRound = (c-1)*2; //오른 왼 이동
			for(int i=1; i<=r; i++) {
				for(int j=1; j<=c; j++) {
					if(map[i][j].size() == 0) {
						continue;
					}
					if(map[i][j].size() > 0) { //상어가 있으면
						for(int s=0; s<map[i][j].size(); s++) {
							Shark tmp = map[i][j].peek();
							if(tmp.move >= king) {
								break;
							}
							tmp = map[i][j].poll();
							if(tmp.d == 1 || tmp.d == 2) {
								tmp.move(tmp.s % xRound);
							}
							else if(tmp.d == 3 || tmp.d == 4){
								tmp.move(tmp.s % yRound);
							}	
							tmp.move++;
							map[tmp.r][tmp.c].offer(tmp);
						}
					}
				}
			}
			
			for(int i=1; i<=r; i++) {
				for(int j=1; j<=c; j++) {
					//옮기고 나서 상어가 여러마리면
					if(map[i][j].size() == 0) {
						continue;
					}
					Shark tmp = map[i][j].poll();
					map[i][j].clear();
					map[i][j].offer(tmp); //한마리만 남기고 삭제
				}
			}
		}
		System.out.println(sumSize);
	}
}
