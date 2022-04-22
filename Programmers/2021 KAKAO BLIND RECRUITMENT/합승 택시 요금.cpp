#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#define INF 987654321

using namespace std;

int N;
vector<int> d(201);
vector<int> dA(201);
vector<int> dB(201);
vector<pair<int, int>> graph[201];

void init() {
	for (int i = 1; i <= N; i++) {
		d[i] = INF;
		dA[i] = INF;
		dB[i] = INF;
	}
}

void dijkstra(int start, vector<int> &dist) {
	priority_queue < pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ d[start], start });

	while (!pq.empty()) {
		int node = pq.top().second;
		int cost = pq.top().first;
		pq.pop();
		for (int i = 0; i < graph[node].size(); i++) {
			int next = graph[node][i].first;
			int nextCost = graph[node][i].second;
			if (dist[next] > dist[node] + nextCost) {
				dist[next] = dist[node] + nextCost;
				pq.push({ dist[next], next });
			}
		}
	}
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
	int answer = 987654321;
	N = n;
	init();
	for (int i = 0; i < fares.size(); i++) {
		graph[fares[i][0]].push_back(make_pair(fares[i][1], fares[i][2]));
		graph[fares[i][1]].push_back(make_pair(fares[i][0], fares[i][2]));
	}
	for (int i = 1; i <= N; i++) {
		cout << i << " : ";
		for (int j = 0; j < graph[i].size(); j++) {
			cout << graph[i][j].first << ", " << graph[i][j].second << " ";
		}
		cout << "\n";
	}

	d[s] = 0;
	dA[a] = 0;
	dB[b] = 0;

	dijkstra(s, d);
	cout << "d : ";
	for (int i = 1; i <= N; i++) { 
		cout << d[i] << " ";
	}
	cout << "\n";
	
	dijkstra(a, dA);
	cout << "dA : ";
	for (int i = 1; i <= N; i++) {
		cout << dA[i] << " ";
	}
	cout << "\n";

	dijkstra(b, dB);
	cout << "dB : ";
	for (int i = 1; i <= N; i++) {
		cout << dB[i] << " ";
	}
	cout << "\n";

	for (int i = 1; i <= N; i++) {
		if (d[i] == INF || dA[i] == INF || dB[i] == INF) {
			continue;
		}
		else {
			answer = min(answer, d[i] + dA[i] + dB[i]);
		}
	}

	return answer;
}

int main(void) {
	int n = 6;
	int s = 4;
	int a = 6;
	int b = 2;
	vector<vector<int>> fares = { {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25} };
	int result = solution(n, s, a, b, fares);
	cout << result << "\n";
	return 0;
}