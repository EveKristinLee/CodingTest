package solving.solve_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//SWEA 모의
public class 미생물_격리_2382 {

	public static class P implements Comparable<P>{
		int idx;
		int x;
		int y;
		int cnt;
		int dir;
		
		public P(int idx, int x, int y, int cnt, int dir) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(P o) {
			if(this.idx == o.idx) {
				return o.cnt - this.cnt;
			}
			else {
				return this.idx - o.idx;
			}
		}
	}
	
	static int N;
	static int M; //격리 시간
	static int K; //미생물 군집의 개수
	static List<P> pos;
	
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1}; //상, 하, 좌, 우
	
	private static boolean isEdge(int x, int y) {
		if(x==0 || y==0 || x==N-1 || y==N-1) {
			return true;
		}
		return false;
	}
	
	private static int turnDir(int dir) {
		int res = 0;
		if(dir == 1) {
			res = 2;
		}else if(dir == 2) {
			res = 1;
		}else if(dir == 3) {
			res = 4;
		}else if(dir == 4) {
			res = 3;
		}
		return res;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pos = new LinkedList<>();
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				pos.add(new P((x * N) + y, x, y, cnt, dir));
			}
			
			for(int i=0; i<M; i++) { //M시간 후
				move();
				chkDouble();
			}
			
			int res = 0;
			for (P p : pos) {
				res += p.cnt;
			}
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void chkDouble() {
		Collections.sort(pos);
		for(int i=1; i<pos.size(); i++) {
			if(pos.get(i-1).idx == pos.get(i).idx) {
				pos.get(i-1).cnt += pos.get(i).cnt;
				pos.remove(i);
				i--;
			}
		}
	}

	private static void move() {
		for(int i=0; i<pos.size(); i++) {
			P p = pos.get(i);
			p.x = p.x + dx[p.dir];
			p.y = p.y + dy[p.dir];
			p.idx = p.x * N + p.y;
			
			if(isEdge(p.x, p.y)) {
				if(p.cnt/2 == 0) {
					pos.remove(i);
					i--;
				}
				else {
					p.cnt /=2;
					p.dir = turnDir(p.dir);
				}
			}
		}
	}
}
