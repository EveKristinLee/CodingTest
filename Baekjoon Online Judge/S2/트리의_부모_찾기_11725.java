package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의_부모_찾기_11725 {

	static int n;
	static List<ArrayList<Integer>> tree;
	static boolean[] visit;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList<ArrayList<Integer>>();
		visit = new boolean[n+1];
		parent = new int[n+1];
		
		for(int i=0; i<=n; i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		
		dfs(1);
		
		for(int i=2; i<=n; i++) {
			System.out.println(parent[i]);
		}
	}

	private static void dfs(int now) {
		visit[now] = true;
		
		for(int i=0; i<tree.get(now).size(); i++) {
			if(!visit[tree.get(now).get(i)]) {
				parent[tree.get(now).get(i)] = now;
				dfs(tree.get(now).get(i));
			}
		}
	}
}
