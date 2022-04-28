#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int cnt = 0;

void dfs(int now, int w, int s, vector<int> nextNode, vector<int> info, vector<vector<int>> tree) {
	cnt = max(cnt, s);
	if (w >= s) {
		return;
	}

	for (int i = 0; i < nextNode.size(); i++) {
		vector<int> next = nextNode;
		next.erase(next.begin() + i);
		for (int j = 0; j < tree[nextNode[i]].size(); j++) {
			next.push_back(tree[nextNode[i]][j]);
		}
		if (!info[nextNode[i]]) {
			dfs(nextNode[i], w, s + 1, next, info, tree);
		}
		else {
			dfs(nextNode[i], w + 1, s, next, info, tree);
		}
	}
}

int solution(vector<int> info, vector<vector<int>> edges) {
	vector<vector<int>> tree(info.size());
	for (int i = 0; i < edges.size(); i++) {
		tree[edges[i][0]].push_back(edges[i][1]);
	}

	vector<int> nextNode;
	for (int i = 0; i < tree[0].size(); i++) {
		nextNode.push_back(tree[0][i]);
	}

	dfs(0, 0, 1, nextNode, info, tree);
	return cnt;
}

int main(void) {
	vector<int> info = { 0,0,1,1,1,0,1,0,1,0,1,1 };
	vector<vector<int>> edges = { {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9} };
	int result = solution(info, edges);
	cout << result << "\n";
	return 0;
}