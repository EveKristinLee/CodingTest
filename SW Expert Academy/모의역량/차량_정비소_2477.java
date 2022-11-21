package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//SWEA 모의
public class 차량_정비소_2477 {

	private static class Customer {
		int customerNo;
		int receptNo;
		int repairNo;
		int arriveTime;
		int receptStartTime;
		int repairStartTime;
		int receptEndTime;
		
		public Customer(int customerNo, int arriveTime) {
			super();
			this.customerNo = customerNo;
			this.arriveTime = arriveTime;
		}
	}
	
	static int N; //접수 창구의 개수
	static int M; //정비 창구의 개수
	static int K; //고객의 수
	static int A; //지갑을 잃어버린 고객이 이용한 접수 창구 번호
	static int B; //지갑을 잃어버린 고객이 이용한 정비 창구 번호
	static int res;
	
	static int[] recept; //접수 시간
	static int[] repair; //정비 시간
	
	static Customer[] receptCounter;
	static Customer[] repairCounter;

	static PriorityQueue<Customer> receptWait;
	static PriorityQueue<Customer> repairWait;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			recept = new int[N+1];
			repair = new int[M+1];
			receptCounter = new Customer[N+1];
			repairCounter = new Customer[M+1];
			//접수 창구 정렬 = 고객 번호가 낮은 순서대로
			receptWait = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.customerNo - o2.customerNo;
				}
				
			});
			//정비 창구 정렬 = 먼저 기다린 고객 -> 동시에 도착일 경우 접수 창구가 작은 순서대로
			repairWait = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.receptEndTime == o2.receptEndTime) {
						return o1.receptNo - o2.receptNo;
					}
					else {
						return o1.receptEndTime - o2.receptEndTime;
					}
				}
			});
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				recept[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				receptWait.offer(new Customer(i, Integer.parseInt(st.nextToken())));
			}
			
			res = 0;
			
			int time = 0;
			int cnt = 0;
			while(true) {
				//접수창구 비우기
				for(int i=1; i<=N; i++) {
					if(receptCounter[i] == null) continue;
					
					Customer tmp = receptCounter[i];
					if(tmp.receptStartTime + recept[i] <= time) { //접수창구 비울 수 있음
						tmp.receptEndTime = time;
						repairWait.offer(tmp);
						receptCounter[i] = null;
					}
				}
				
				//접수창구에 고객 배치
				for(int i=1; i<=N; i++) {
					if(receptCounter[i] == null) {
						if(!receptWait.isEmpty()) {
							if(receptWait.peek().arriveTime <= time) {
								Customer tmp = receptWait.poll();
								tmp.receptStartTime = time;
								tmp.receptNo = i;
								receptCounter[i] = tmp;
							}
						}
					}
				}
				
				//정비창구 비우기
				for(int i=1; i<=M; i++) {
					if(repairCounter[i] == null) continue;
					
					Customer tmp = repairCounter[i];
					if(tmp.repairStartTime + repair[i] <= time) { 
						if(tmp.receptNo == A && tmp.repairNo == B) {
							res += tmp.customerNo;
						}
						repairCounter[i] = null;
						cnt++;
					}
				}
				
				if(cnt == K) { //고객 끝
					break;
				}
				
				//정비창구에 고객 배치
				for(int i=1; i<=M; i++) {
					if(repairCounter[i] == null) {
						if(!repairWait.isEmpty()) {
							Customer tmp = repairWait.poll();
							tmp.repairStartTime = time;
							tmp.repairNo = i;
							repairCounter[i] = tmp;
						}
					}
				}
				time++;
			}
			if(res == 0) {
				sb.append("#"+t+" "+-1+"\n");
			}
			else {
				sb.append("#"+t+" "+res+"\n");
			}
		}
		System.out.println(sb);
	}

}
