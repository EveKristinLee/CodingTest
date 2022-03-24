#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;
#define INF 987654321

bool time_travel(int n, vector<pair<pair<int, int>, int>> map) {
	int d[501];
	memset(d, INF, sizeof(INF));
	d[1] = 0;

	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < map.size(); j++) {
			int s = map[j].first.first;
			int e = map[j].first.second;
			int t = map[j].second;
			if (d[s] != INF) {
				if (d[e] > d[s] + t) {
					d[e] = d[s] + t;
					
					if (i == n) {
						return true;
					}
				}
			}
		}
	}
	return false;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int TC;
	cin >> TC;
	while (TC--) {
		int n, m, w;
		cin >> n >> m >> w;

		vector<pair<pair<int, int>, int>> map;

		for (int i = 0; i < m; i++) {
			int s, e, t;
			cin >> s >> e >> t;
			map.push_back(make_pair(make_pair(s, e), t));
			map.push_back(make_pair(make_pair(e, s), t));
		}
		for (int i = 0; i < w; i++) {
			int s, e, t;
			cin >> s >> e >> t;
			map.push_back(make_pair(make_pair(s, e), -t));
		}
		if (time_travel(n, map)) {
			cout << "YES\n";
		}
		else {
			cout << "NO\n";
		}
	}
	return 0;
}