#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;
#define INF 987654321
#define MAX 10001

int n; //ÇÐ»ý ¼ö
int m;
int x;
int d[1001];
int tmpD[1001];
vector<pair<int, int>> map[MAX];

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

		for (int i = 0; i < map[idx].size(); i++) {
			if (d[idx] + map[idx][i].second < d[map[idx][i].first]) {
				d[map[idx][i].first] = d[idx] + map[idx][i].second;
				pq.push(make_pair(d[map[idx][i].first], map[idx][i].first));
			}
		}
	}
}

int main(void) {
	cin >> n >> m >> x;
	for (int i = 0; i < m; i++) {
		int start, end, time;
		cin >> start >> end >> time;
		map[start].push_back(make_pair(end, time));
	}

	dijkstra(x);
	for (int i = 1; i <= n; i++) {
		tmpD[i] = d[i];
	}

	int result = 0;
	for (int i = 1; i <= n; i++) {
		if (i != x) {
			dijkstra(i);
			result = max(result, tmpD[i] + d[x]);
		}
	}
	cout << result << "\n";

	return 0;
}