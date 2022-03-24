#include <iostream>
#include <queue>
#include <cstring>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int rain[101][101];
int map[101][101];
bool visit[101][101];
queue<pair<int, int>> q;
int maxRain = 0; //비의 최대 높이
int cnt = 0; // 안전영역 개수
vector<int> res;

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

bool isInside(int x, int y) {
	return (x >= 0) && (x < N) && (y >= 0) && (y < N);
}

void resetMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = 0;
			visit[i][j] = false;
		}
	}
}

void bfs(int x, int y) {
	visit[x][y] = true;
	q.push(make_pair(x, y));

	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isInside(nx, ny)) {
				continue;
			}
			if (map[nx][ny] && !visit[nx][ny]) {
				visit[nx][ny] = true;
				q.push(make_pair(nx, ny));
			}
		}
	}
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> rain[i][j];
			if (maxRain < rain[i][j]) {
				maxRain = rain[i][j];
			}
		}
	}

	for (int r = 1; r <= maxRain; r++) {
		resetMap();
		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (rain[i][j] < r) {
					//물에 잠김
					map[i][j] = 0;
				}
				else {
					map[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] && !visit[i][j]) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		res.push_back(cnt);
	}
	sort(res.begin(), res.end());
	cout << res[res.size()-1] << "\n";
	
	return 0;
}