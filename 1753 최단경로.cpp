#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;
#define INF 987654321
#define MAX 20001

priority_queue< pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
vector<pair<int, int>> map[20001];
int d[20001];
int v; //정점의 개수
int e; //간선의 개수
int start;

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> v >> e;
	cin >> start;
	for (int i = 0; i < e; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		map[u].push_back(make_pair(v, w));
	}
	for (int i = 1; i <= v; i++) {
		d[i] = INF;
	}
	d[start] = 0;
	pq.push(make_pair(d[start], start));
	while (!pq.empty()) {
		int dx = pq.top().first;
		int idx = pq.top().second;
		pq.pop();

		for (int i = 0; i < map[idx].size(); i++) {
			if (map[idx][i].second + d[idx] < d[map[idx][i].first]) {
				d[map[idx][i].first] = map[idx][i].second + d[idx];
				pq.push(make_pair(d[map[idx][i].first], map[idx][i].first));
			}
		}
	}

	for (int i = 1; i <= v; i++) {
		if (d[i] == INF) {
			cout << "INF" << "\n";
		}
		else {
			cout << d[i] << "\n";
		}
	}

	return 0;
}