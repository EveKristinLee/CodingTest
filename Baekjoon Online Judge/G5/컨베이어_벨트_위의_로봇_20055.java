package solving.solve_1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


//BOJ G5
public class 컨베이어_벨트_위의_로봇_20055 {
	public static class Robot implements Comparable<Robot>{
		int cnt;
		int idx;
		public Robot(int cnt, int idx) {
			super();
			this.cnt = cnt;
			this.idx = idx;
		}
		@Override
		public int compareTo(Robot o) {
			return cnt - o.cnt;
		}
	}

	static int N;
	static int K;
	static int[] belt;
	static int cnt;
	static List<Robot> robot;
	static boolean[] visit;
	static int robotCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2*N+1];
		visit = new boolean[2*N+1];
		robot = new LinkedList<>();
		robotCnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		cnt = 0;
		while(!isEnd()) {
			rotation();
			moveRobot();
			cnt++;
		}
		
		System.out.println(cnt);
	}

	//로봇 옮기기 & 새로운 로봇 추가
	private static void moveRobot() {
		Collections.sort(robot);
		for(int i=0; i<robot.size(); i++) {
			Robot r = robot.get(i);
			if(!visit[r.idx+1] && belt[r.idx+1] > 0) {
				r.idx += 1;
				visit[r.idx-1] = false;
				visit[r.idx] = true;
				belt[r.idx]--;;
				
				if(r.idx == N) {
					visit[r.idx] = false;
					robot.remove(r);
					i--;
				}
			}
		}
		
		if(belt[1] > 0) {
			visit[1] = true;
			belt[1]--;
			Robot n = new Robot(robotCnt++, 1);
			robot.add(n);
		}
	}

	private static void rotation() {
		int tmp = belt[2*N];
		for(int i=2*N-1; i>=1; i--) {
			belt[i+1] = belt[i];
		}
		belt[1] = tmp;
		
		Collections.sort(robot);
		for(int i=0; i<robot.size(); i++) {
			Robot r = robot.get(i);
			r.idx++;
			visit[r.idx - 1] = false;
			visit[r.idx] = true;
			if(r.idx == N) {
				visit[r.idx] = false;
				robot.remove(r);
				i--;
			}
		}
	}

	private static boolean isEnd() {
		int cnt = 0;
		for(int i=1; i<=2*N; i++) {
			if(belt[i] ==0) {
				cnt++;
				if(cnt >= K) {
					return true;
				}
			}
		}
		return false;
	}
}
