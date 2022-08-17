package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 무선_충전_5644 {
	
	public static class Charger implements Comparable<Charger> {
		int idx; //입력받은 순서
		int x; //좌표
		int y; //좌표
		int c; //충전 범위
		int p; //처리량
		public Charger(int idx, int y, int x, int c, int p) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
		@Override
		public int compareTo(Charger o) { //처리량 기준 내림차순 -> 처리량이 같아면 충전범위 내림차순
			return o.p - p;
		}
	}
	
	static int m; //총 이동 시간
	static int a; //bc의 개수
	static int ax, ay, bx, by; //a와 b의 좌표
	static int[] mA; //A의 이동거리
	static int[] mB; //B의 이동거리
	static Charger[] bc; //충전기
	static ArrayList<Charger> charA; //a가 사용 가능한 충전기
	static ArrayList<Charger> charB; //b가 사용 가능한 충전기
	static boolean[] visit; //사용한 충전기 확인
	static int maxCharge;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			
			maxCharge = 0;
			mA = new int[m];
			mB = new int[m];
			bc = new Charger[a];
			visit = new boolean[a];
			ax = 1;
			ay = 1;
			bx = 10;
			by = 10;
			charA = new ArrayList<>();
			charB = new ArrayList<>();
			
			//사용자 이동 위치 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				mA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				mB[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<a; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i] = new Charger(i, 
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()));
			}
			
			for(int i=0; i<=m; i++) {
				//사용 가능한 충전기 확인
				chkAvailable(ax, ay, bx, by);
				Collections.sort(charA);
				Collections.sort(charB);
				if(charA.isEmpty() && !charB.isEmpty()) {
					maxCharge += charB.get(0).p;
				}
				else if(charB.isEmpty() && !charA.isEmpty()) {
					maxCharge += charA.get(0).p;
				}
				
				if (!charA.isEmpty() && !charB.isEmpty()){
					int tmpMax = 0;
					for(int j=0; j<charA.size(); j++) {
						for(int k=0; k<charB.size(); k++) {
							if(charA.get(j).idx != charB.get(k).idx) {
								tmpMax = Math.max(tmpMax, charA.get(j).p + charB.get(k).p);
							}
							else {
								tmpMax = Math.max(tmpMax, charA.get(j).p);
							}
						}
					}
					maxCharge += tmpMax;
				}
				charA.clear();
				charB.clear();
				Arrays.fill(visit, false);
				
				//사용자 이동
				if(i < m) {
					if(mA[i] == 1) {
						ax -= 1;
					}
					else if(mA[i] == 2) {
						ay += 1;
					}
					else if(mA[i] == 3) {
						ax += 1;
					}
					else if(mA[i] == 4) {
						ay -= 1;
					}
					
					if(mB[i] == 1) {
						bx -= 1;
					}
					else if(mB[i] == 2) {
						by += 1;
					}
					else if(mB[i] == 3) {
						bx += 1;
					}
					else if(mB[i] == 4) {
						by -= 1;
					}
				}
			}
			sb.append("#"+t+" " + maxCharge).append("\n");
		}
		System.out.println(sb);
	}


	private static void chkAvailable(int xa, int ya, int xb, int yb) {
		for(int i=0; i<a; i++) {
			if((Math.abs(xa - bc[i].x) + Math.abs(ya - bc[i].y)) <= bc[i].c) {
				charA.add(bc[i]);
			}
			if((Math.abs(xb - bc[i].x) + Math.abs(yb - bc[i].y)) <= bc[i].c) {
				charB.add(bc[i]);
			}
		}
	}
}
