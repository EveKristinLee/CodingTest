package solving.solve_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//SWEA
public class 홈_방범_서비스_2117 {
	
	public static class Home {
		int x;
		int y;
		public Home(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int n; //도시의 크기
	static int m; //하나의 집이 지불할 수 있는 비용
	static ArrayList<Home> home;
	//static int k;
	static int maxProfit = Integer.MIN_VALUE;
	static int maxHomeCnt = Integer.MIN_VALUE;
	static int nowHomeCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			//map을 다 커버할 수 있는 k의 최대 수
			//k = n+2;
			home = new ArrayList<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					int tmp =  Integer.parseInt(st.nextToken());
					if(tmp == 1) {
						home.add(new Home(i, j));
					}
				}
			}
			
			maxProfit = Integer.MIN_VALUE;
			maxHomeCnt = Integer.MIN_VALUE;
			
			int k = 1;
			while(k<=n+1) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(getProfit(i, j, k) >= 0) {
							maxHomeCnt = Math.max(maxHomeCnt, nowHomeCnt);
						}
					}
				}
				k++;
			}
			sb.append("#"+t+" "+maxHomeCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static int getProfit(int x, int y, int k) {
		nowHomeCnt = 0;
		for(int i=0; i<home.size(); i++) {
			int dist = Math.abs(x - home.get(i).x) + Math.abs(y - home.get(i).y);
			if(dist < k) {
				nowHomeCnt++;
			}
		}
		int total = nowHomeCnt * m;
		int cost = (k * k) +  ((k-1) * (k-1));
		return total - cost;
	}

}
