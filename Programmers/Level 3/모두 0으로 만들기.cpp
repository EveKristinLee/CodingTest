#include <iostream>
#include <vector>

using namespace std;

long long answer = 0;

void dfs(int now, int parent, vector<vector<int>>& tree, vector<long long>& sum) {
	for (int i = 0; i < tree[now].size(); i++) {
		if (tree[now][i] != parent) {
			dfs(tree[now][i], now, tree, sum);
		}
	}
	sum[parent] += sum[now];
	answer += abs(sum[now]);
}

long long solution(vector<int> a, vector<vector<int>> edges) {
	vector<long long> sum(a.size());
	for (int i = 0; i < a.size(); i++) {
		sum[i] = a[i];
	}
	
	vector<vector<int>> tree(a.size());
	for (int i = 0; i < edges.size(); i++) {
		tree[edges[i][0]].push_back(edges[i][1]);
		tree[edges[i][1]].push_back(edges[i][0]);
	}

	dfs(0, 0, tree, sum);
	if (sum[0] == 0) {
		return answer;
	}
	return -1;
}

int main(void) {
	vector<int> a = { -5,0,2,1,2 };
	vector<vector<int>> edges = { {0, 1}, {3, 4}, {2, 3}, {0, 3} };
	int result = solution(a, edges);
	cout << result << "\n";
	return 0;
}