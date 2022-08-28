package solving.solve_모의_A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 낚시터_자리잡기_15170 {
	
	static int n; //낚시터의 개수
	static int[][] enSeq = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}}; 
	static int minLen;
	static int[][] gate;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			gate = new int[3][2];
			minLen = Integer.MAX_VALUE;
			visit = new boolean[n];
			for(int i=0; i<3; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int gateNum = Integer.parseInt(st.nextToken()) -1;
				int person = Integer.parseInt(st.nextToken());
				gate[i][0] = gateNum;
				gate[i][1] = person;
			}
			
			for(int i=0; i<6; i++) {
				visit = new boolean[n];
				go(i, 0, 0, visit);
			}
			sb.append("#"+t+" "+minLen).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void go(int seq, int nowSeq, int sumDist, boolean[] visited) {
		if(nowSeq == 3) { //게이트 3개 배치 완료
			minLen = Math.min(minLen, sumDist);
			return;
		}
		
		boolean[] visit = Arrays.copyOf(visited, n);
		int door = enSeq[seq][nowSeq]; //해당 게이트 idx
		int gateNum = gate[door][0];
		int personNum = gate[door][1];
		int leftPos = gateNum - 1;
		int rightPos = gateNum + 1;
		int distL = 2;
		int distR = 2;
		
		for(int i=1; i<=personNum; i++) { //사람 한명씩 배치
			if(!visit[gateNum]) { //게이트 바로앞이 비었으면
				visit[gateNum] = true;
				sumDist +=1;
				continue;
			}
			
			//양옆으로 가장 가까운 빈 곳 찾기
			while(leftPos >= 0 && visit[leftPos]) { 
				leftPos--;
				distL++;
			}
			while(rightPos < n && visit[rightPos]) { 
				rightPos++;
				distR++;
			}
			
			if(leftPos >=0 && rightPos < n) {
				if(i == personNum && distL == distR)  { //마지막 사람이고 양옆 거리가 같으면 양쪽 다 보내기
					visit[leftPos] = true;
					go(seq, nowSeq+1, sumDist+distL, visit);
					visit[leftPos] = false;
					
					visit[rightPos] = true;
					go(seq, nowSeq+1, sumDist+distR, visit);
					visit[rightPos] = false;
					
					return;
				}
				if(distL <= distR) { // 왼쪽이 더 가까우거나 같으면
					visit[leftPos] = true;
					sumDist += distL;
				}
				else {
					visit[rightPos] = true;
					sumDist += distR;
				}
			}
			else { //낚시터가 꽉 차서 못 앉는 경우는 없으므로 둘 중 하나만 경계를 벗어남
				if(leftPos >= 0) {
					visit[leftPos] = true;
					sumDist += distL;
				}
				else {
					visit[rightPos] = true;
					sumDist += distR;
				}
			}
		}
		go(seq, nowSeq+1, sumDist, visit);
	}

}
