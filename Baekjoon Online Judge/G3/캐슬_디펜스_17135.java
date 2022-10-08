package solving.solve_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ
public class 캐슬_디펜스_17135 {
	
	public static class Enemy implements Cloneable, Comparable<Enemy>{
		int x;
		int y;
		boolean survive;
		int dist;
		public Enemy(int x, int y, boolean survive, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.survive = survive;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Enemy o) {
			int dif = dist - o.dist;
			if(dif == 0) {
				dif = y - o.y;
			}
			return dif;
		}
	}

	static int n; //세로
	static int m; //가로
	static int d; //공격 거리
	static ArrayList<Enemy> pos; //적의 위치
	static int[] lo; //궁수 위치 -> 열의 idx만 저장
	static int maxCnt = Integer.MIN_VALUE; //제거할 수 있는 최대 적의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		pos = new ArrayList<>();
		lo = new int[3];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) {
					pos.add(new Enemy(i, j, true, 0));
				}
			}
		}
		makeLo(0, 0); //궁수 자리배치 -> 공격 -> 적 이동 
		System.out.println(maxCnt);
	}

	private static void makeLo(int cnt, int start) {
		if(cnt == 3) {
			ArrayList<Enemy> tmpPos = copyEnemy();
			int deadCnt = 0;
			while(!allDead(tmpPos)) { //적이 다 죽은게 아니라면
				//궁수 공격
				deadCnt += attack(tmpPos);
				//적 이동
				move(tmpPos);
			}
			//제거한 최대 적의 수 갱신
			maxCnt = Math.max(maxCnt, deadCnt);
			return;
		}
		
		for(int i=start; i<m; i++) {
			lo[cnt] = i;
			makeLo(cnt+1, i+1);
		}
		
	}
	
	private static void move(ArrayList<Enemy> tmpPos) {
		for (Enemy enemy : tmpPos) {
			enemy.x = enemy.x+1;
			if(enemy.x == n) { //성의 위치에 도달하면 없애기
				enemy.survive = false;
			}
		}
	}

	private static int attack(ArrayList<Enemy> tmpPos) {
		Set<Enemy> target = new HashSet<>(); //궁수가 공격할 수 있는 적 저장
		//궁수가 공격할 수 있는 적 확인
		for(int i=0; i<3; i++) {
			PriorityQueue<Enemy> each = new PriorityQueue<>();
			for(int j=0; j<tmpPos.size(); j++) {
				if(tmpPos.get(j).survive) {
					int dist = Math.abs(n - tmpPos.get(j).x) + Math.abs(lo[i] - tmpPos.get(j).y);
					if(dist <= d) {
						tmpPos.get(j).dist = dist;
						each.offer(tmpPos.get(j));
					}
				}
			}
			if(!each.isEmpty()) {
				target.add(each.peek()); //해당 궁수가 죽일 수 있는 가장 짧은 거리의 적 저장			
			}
		}
		for (Enemy enemy : target) {
			enemy.survive = false; //적 제거
		}
		return target.size();
	}

	private static boolean allDead(ArrayList<Enemy> tmpPos) {
		for (Enemy enemy : tmpPos) {
			if(enemy.survive) {
				return false;
			}
		}
		return true;
	}

	private static ArrayList<Enemy> copyEnemy() {
		ArrayList<Enemy> tmpPos = new ArrayList<>();
		
		for (Enemy enemy : pos) {
			tmpPos.add(new Enemy(enemy.x, enemy.y, enemy.survive, enemy.dist));
		}
		return tmpPos;
	}
}
