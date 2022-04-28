#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#define INF 987654321

using namespace std;

int d[20001] = { 0 };
queue<int> q;
vector<vector<int>> node;

void dijkstra(int now) {

	while (!q.empty()) {
		int idx = q.front();
		q.pop();

		for (int i = 0; i < node[idx].size(); i++) {
			if (d[node[idx][i]] > d[idx] + 1) {
				q.push(node[idx][i]);
				d[node[idx][i]] = min(d[node[idx][i]], d[idx] + 1);
			}
		}
	}
}

int solution(int n, vector<vector<int>> edge) {
	int answer = 0;
	node = vector<vector<int>>(n + 1, vector<int>());
	for (int i = 0; i < edge.size(); i++) {
		node[edge[i][0]].push_back(edge[i][1]);
		node[edge[i][1]].push_back(edge[i][0]);
	}
	for (int i = 0; i <= n; i++) {
		d[i] = INF;
	}
	d[1] = 0;
	q.push(1);
	dijkstra(1);
	/*for (int i = 1; i <= n; i++) {
		cout << d[i] << " ";
	}
	cout << "\n";*/

	sort(d + 1, d + n + 1);
	for (int i = 1; i <= n; i++) {
	cout << d[i] << " ";
	}
	cout << "\n";

	int maxNum = d[n];
	for (int i = 1; i <= n; i++) {
		if (d[i] == maxNum) {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	int n = 6;
	vector<vector<int>> edge = { {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2} };
	int result = solution(n, edge);
	cout << result << "\n";
	return 0;
}