package solving.solve_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 나무_재테크_16235 {
	
	public static class Tree implements Comparable<Tree>{
		int x;
		int y;
		int age;
		
		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}

	static int N;
	static int M; //나무의 개수
	static int K; //년 수
	static int[][] food;
	static int[][] map;
	static Deque<Tree> tree;
	
	static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
	static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>N || y>N) {
			return false;
		}
		return true;
	}
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		food = new int[N+1][N+1];
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; //처음에 양분은 5
			}
		}
		
		tree = new LinkedList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			tree.offer(new Tree(
								Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort((List<Tree>) tree);
		for(int i=0; i<K; i++) { //K년 반복
			springSummer();
			fall();
			winter();
		}
		System.out.println(tree.size());			
	}

	private static void springSummer() {
		List<Tree> dead = new ArrayList<>();
		
		//봄
		int size = tree.size();
		while(size-- > 0) {
			Tree t = tree.poll();
			if(map[t.x][t.y] < t.age) {
				dead.add(new Tree(t.x, t.y, t.age/2));
			}
			else {
				map[t.x][t.y] -= t.age;
				tree.offer(new Tree(t.x, t.y, t.age+1));
			}
		}
		
		//여름
		for (Tree t : dead) {
			map[t.x][t.y] += t.age;
		}
	}
	
	private static void fall() {
		Deque<Tree> origin = new LinkedList<>();
		origin.addAll(tree);
		for (Tree t : origin) {
			if(t.age % 5 == 0) { //5의 배수
				for(int i=0; i<8; i++) {
					int nx = t.x + dx[i];
					int ny = t.y + dy[i];
					if(isInside(nx, ny)) {
						tree.addFirst(new Tree(nx, ny, 1));
					}
				}
			}
		}
	}
	
	private static void winter() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += food[i][j];
			}
		}
	}
}
