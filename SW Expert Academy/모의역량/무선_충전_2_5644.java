package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//SWEA 모의
public class 무선_충전_2_5644 {

	private static class BC implements Comparable<BC>{
		int x;
		int y;
		int c; //충전 범위
		int p; //처리량
		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		@Override
		public int compareTo(BC o) {
			return o.p - p;
		}
	}
	
	static int M; //총 이동시간
	static int A; //BC의 개수
	static int[] rootA;
	static int[] rootB;
	static BC[] charge;
	static int res;
	static List<BC> charA;
	static List<BC> charB;
	static int ax, ay;
	static int bx, by;
	
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			rootA = new int[M];
			rootB = new int[M];
			charge = new BC[A];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				rootA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				rootB[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				charge[i] = new BC(x, y, c, p);
			}
			
			ax = 1;
			ay = 1;
			bx = 10;
			by = 10;
			
			res = 0;
			go();
			
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}
	
	private static void go() {
		
		for(int i=0; i<=M; i++) {
			charA = new ArrayList<>();
			charB = new ArrayList<>();
			//현위치 BC확인
			for(int j=0; j<A; j++) {
				if(getDist(ax, ay, charge[j].x, charge[j].y) <= charge[j].c) {
					charA.add(charge[j]);
				}
				if(getDist(bx, by, charge[j].x, charge[j].y) <= charge[j].c) {
					charB.add(charge[j]);
				}
			}
			
			Collections.sort(charA);
			Collections.sort(charB);
			//연결되어 있는 충전기 확인
			if(charA.isEmpty() && !charB.isEmpty()) { //b만 충전 연결
				res += charB.get(0).p;
			}
			else if(!charA.isEmpty() && charB.isEmpty()) { //a만 충전 연결
				res += charA.get(0).p;
			}
			else if(!charA.isEmpty() && !charB.isEmpty()) { //둘다 충전기 있을때 확인
				//배터리 연결 경우의 수 찾기
				res += getMaxCharge();
			}
			
			if(i < M) {
				//위치 이동
				ax += dx[rootA[i]];
				ay += dy[rootA[i]];
				bx += dx[rootB[i]];
				by += dy[rootB[i]];
			}
		}
		
	}

	private static int getMaxCharge() {
		int tmp = 0;
		for(int k=0; k<charA.size(); k++) {
			for(int z=0; z<charB.size(); z++) {
				if(charA.get(k) != charB.get(z)) {
					tmp = Math.max(tmp, charA.get(k).p + charB.get(z).p);
				}
				else {
					tmp = Math.max(tmp, charA.get(k).p);
				}
			}
		}
		return tmp;
	}

	private static int getDist(int sx, int sy, int ex, int ey) {
		return Math.abs(sx - ex) + Math.abs(sy - ey);
	}
}
