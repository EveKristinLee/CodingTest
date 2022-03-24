#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
#define MAX 100001

int parent[MAX];
vector < pair<int, pair<int, int>>> map;
int n, m;
vector<int> cost;

int find(int x) {
	if (parent[x] == x) {
		return x;
	}
	else {
		return parent[x] = find(parent[x]);
	}
}

bool sameParent(int x, int y) {
	x = find(x);
	y = find(y);
	if (x == y) {
		return true;
	}
	else {
		return false;
	}
}

void Union(int x, int y) {
	x = find(x);
	y = find(y);
	if (x != y) {
		parent[y] = x;
	}
}

int main(void) {
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int start, to, cost;
		cin >> start >> to >> cost;
		map.push_back(make_pair(cost, make_pair(start, to)));
	}

	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}
	sort(map.begin(), map.end());
	for (int i = 0; i < map.size(); i++) {
		if (!sameParent(map[i].second.first, map[i].second.second)) {
			Union(map[i].second.first, map[i].second.second);
			cost.push_back(map[i].first);
		}
	}

	int result = 0;
	//마지막 연결부분(가장 큰 cost) 제외하고 더하면 두개의 도시
	for (int i = 0; i < cost.size() - 1; i++) {
		result += cost[i];
	}
	cout << result << "\n";

	return 0;
} 