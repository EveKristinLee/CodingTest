package solving.solve_0823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//SWEA D4
public class 하나로_1251 {
	static int n;
	static int[] x;
	static int[] y;
	static double e;
	static double[] p;
	static ArrayList<Double[]> fee;
	
	private static void make() {
		p = new double[n];
		for(int i=0; i<n; i++) {
			p[i] = i;
		}
	}
	
	private static double find(double a) {
		if(p[(int) a] == a) {
			return a;
		}
		return p[(int) a] = find(p[(int) a]);
	}
	
	private static boolean union(double a, double b) {
		int aRoot = (int) find(a);
		int bRoot = (int) find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		p[bRoot] = aRoot;
		return true;
	}
	
	private static double getDist(int x1, int y1, int x2, int y2) {
		double dist = 0;
		double dx, dy;
		dx = Math.pow(x2 - x1, 2.0);
		dy = Math.pow(y2 - y1, 2.0);
		dist = Math.sqrt(dx + dy);
		return dist;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			fee = new ArrayList<>(n*(n-1)>>1);
			x = new int[n];
			y = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			e = Double.parseDouble(br.readLine());
			
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					double dist = getDist(x[i], y[i], x[j], y[j]);
					fee.add(new Double[] {dist, (double) i, (double) j});
				}
			}
			make();
			Collections.sort(fee, new Comparator<Double[]>() {
				@Override
				public int compare(Double[] o1, Double[] o2) {
					return o1[0].compareTo(o2[0]);
				}
			});
			double total = 0;
			int cnt = 0;
			for (Double[] f : fee) {
				if(union(f[1], f[2])) {
					total += f[0] * f[0] * e;
					cnt++;
					if(cnt == n-1) {
						break;
					}
				}
			}
			sb.append("#"+t+" "+Math.round(total)).append("\n");
		}
		System.out.println(sb);
	}
}
