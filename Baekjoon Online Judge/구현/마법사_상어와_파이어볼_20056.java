package solving.solve_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


//BOJ 구현
public class 마법사_상어와_파이어볼_20056 {
	
	public static class FireBall {
		int r;
		int c;
		int m;
		int d;
		int s;
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	static int n; //격자 크기
	static int m; //파이어볼 개수
	static int k; //명령 수
	static ArrayList<FireBall> fireball;
	static ArrayList<FireBall>[][] map;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	private static void rotate(FireBall f) {
		f.r = (n + f.r + dx[f.d] * (f.s%n)) % n;
		f.c = (n + f.c + dy[f.d] * (f.s%n)) % n;
		map[f.r][f.c].add(f);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new ArrayList[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		
		fireball = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			fireball.add(new FireBall(Integer.parseInt(st.nextToken()) -1, 
					Integer.parseInt(st.nextToken()) -1,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		for(int t=0; t<k; t++) {
			for(int i=0; i<fireball.size(); i++) {
				rotate(fireball.get(i));
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j].size() >= 2) {
						int totalM = 0;
						int totalS = 0;
						
						boolean isEven = map[i][j].get(0).d %2 == 0 ? true : false;
						boolean isOdd = map[i][j].get(0).d %2 == 1 ? true : false;
						
						for(FireBall f : map[i][j]) {
							totalM += f.m;
							totalS += f.s;
							isEven = isEven & f.d % 2 == 0 ? true : false;
							isOdd = isOdd & f.d % 2 == 1 ? true : false;
							fireball.remove(f);
						}
						
						int nowM = totalM / 5;
						int size = map[i][j].size();
						map[i][j].clear();
						
						if(nowM == 0) {
							continue;
						}
						int nowS = totalS/size;
						if(isEven | isOdd) { //모두 홀수거나 모두 짝수면
							for(int k=0; k<8; k+=2) {
								fireball.add(new FireBall(i, j, nowM, nowS, k));
							}
						}else {
							for(int k=1; k<8; k+=2) {
								fireball.add(new FireBall(i, j, nowM, nowS, k));
							}
						}
					}
					else {
						map[i][j].clear();
					}
				}
			}
		}
		int res = 0;
		for(int i=0; i<fireball.size(); i++) {
			res += fireball.get(i).m;
		}
		System.out.println(res);
	}
}
