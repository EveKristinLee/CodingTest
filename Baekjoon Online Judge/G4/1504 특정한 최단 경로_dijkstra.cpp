#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
#define INF 987654321
#define MAX 801

int n;
int e;
int v1, v2;
vector<pair<int, int>> map[MAX];
int d[MAX];


void dijkstra(int start) {
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	for (int i = 1; i <= n; i++) {
		d[i] = INF;
	}
	d[start] = 0;
	pq.push(make_pair(d[start], start));

	while (!pq.empty()) {
		int idx = pq.top().second;
		pq.pop();
		for (int i = 0; i < map[idx].size(); i++){
			if (d[idx] + map[idx][i].second < d[map[idx][i].first]) {
				d[map[idx][i].first] = d[idx] + map[idx][i].second;
				pq.push(make_pair(d[map[idx][i].first], map[idx][i].first));
			}
		}
	}
}

int main(void) {
	cin >> n >> e;
	for (int i = 0; i < e; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		map[a].push_back(make_pair(b, c));
		map[b].push_back(make_pair(a, c));
	}
	cin >> v1 >> v2;

	int result1 = 0; //1 -> 2 -> 3 -> 4
	int result2 = 0; //1 -> 3 -> 2 -> 4

	dijkstra(1);
	if (d[v1] == INF || d[v2] == INF) {
		cout << -1 << "\n";
	}
	else {
		result1 = d[v1];
		result2 = d[v2];

		dijkstra(v1);
		if (d[v2] == INF || d[n] == INF) {
			cout << -1 << "\n";
		}
		else {
			result1 += d[v2];
			result2 += d[n];

			dijkstra(v2);
			if (d[v1] == INF || d[n] == INF) {
				cout << -1 << "\n";
			}
			else {
				result1 += d[n];
				result2 += d[v1];

				cout << min(result1, result2) << "\n";
			}
		}
	}
	return 0;
}