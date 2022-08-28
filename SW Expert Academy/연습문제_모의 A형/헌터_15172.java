package solving.solve_모의_A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 헌터_15172 {
	private static class Target implements Comparable<Target>{
		int x;
		int y;
		int dist;
		int idx;
		boolean isMonster;
		
		public Target(int x, int y, int dist, int idx, boolean isMonster) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.idx = idx;
			this.isMonster = isMonster;
		}
		
		@Override
		public int compareTo(Target o) {
			int d = dist - o.dist;
			return d;
		}
	}
	
	static int n; //맵의 한변의 길이
	static int[][] map; 
	static boolean[][] visit;
	static ArrayList<Target> target;
	static ArrayList<Target> get;
	static int firstSize;
	static int nowx;
	static int nowy;
	static int minTotalDist = 0;
	static int[] num;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n+1][n+1];
			visit = new boolean[n+1][n+1];
			target = new ArrayList<>();
			nowx = 1;
			nowy = 1;
			minTotalDist = Integer.MAX_VALUE;
			int idx = 0;
			for(int i=1; i<=n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] < 0) {
						target.add(new Target(i, j, 0, map[i][j], false));
						idx++;
					}
					else if(map[i][j] > 0) {
						target.add(new Target(i, j, 0, map[i][j], true));						
						idx++;
					}
				}
			}
			num = new int[idx];
			idx = idx/2;
			int tmpIdx = -idx;
			int nowIdx = 0;
			while(tmpIdx <= idx) {
				if(tmpIdx != 0) {
					num[nowIdx] = tmpIdx;	
					nowIdx++;
					tmpIdx++;
				}
				else if(tmpIdx == 0) {
					tmpIdx++;
				}
			}
			do {
				boolean work = true;
				nowx = 1;
				nowy = 1;
				//순서대로 잡는 거 확인
				int totalDist = 0;
				get = new ArrayList<>(); 
				if(num[0] < 0) {
					continue;
				}
				for(int i=0; i<num.length; i++) {
					getDist(nowx, nowy);
					for(int j=0; j<target.size(); j++) {
						if(target.get(j).idx == num[i]) {
							Target tar = target.get(j);
							
							if(tar.isMonster) {
								get.add(tar);
								totalDist += tar.dist;
								nowx = tar.x;
								nowy = tar.y;
							}
							else if(!tar.isMonster) {
								boolean catchMonstar = false;
								for (Target g : get) {
									if(Math.abs(map[g.x][g.y]) == Math.abs(map[tar.x][tar.y])) {
										totalDist += tar.dist;
										catchMonstar = true;
										nowx = tar.x;
										nowy = tar.y;
										break;
									}
								}
								if(!catchMonstar) { //몬스터 못잡았어요
									work = false;
									break;
								}
							}
						}
					}
					if(!work) {
						break;
					}
				}
				if(work) {
					minTotalDist = Math.min(minTotalDist, totalDist);
				}
			}while(np(num));
			sb.append("#"+t+" "+minTotalDist).append("\n");
		}
		System.out.println(sb);
	}


	private static boolean np(int[] num) {
		int n = num.length -1;
		int i = n;
		while(i>0 && num[i-1] >= num[i]) --i;
		if(i==0) {
			return false;
		}
		int j = n;
		while(num[i-1]>=num[j]) --j;
		swap(num, i-1, j);
		int k = n;
		while(i<k) swap(num, i++, k--);
		
		return true;
	}


	private static void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}


	private static void getDist(int x, int y) {
		for(int i=0; i<target.size(); i++) {
			int dist = Math.abs(x - target.get(i).x) + Math.abs(y - target.get(i).y);
			target.get(i).dist = dist;
		}
	}
}
