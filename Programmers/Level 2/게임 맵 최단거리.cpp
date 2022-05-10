#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };
int N;
int M;
bool visit[101][101] = { false };

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < N) && (y < M);
}

int bfs(int x, int y, vector<vector<int>> maps) {
	queue<pair<int, int>> q;
	vector<vector<int>> tmp(N, vector<int>(M, 0));
	q.push({ x, y });
	visit[x][y] = true;
	tmp[x][y] = 1;

	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();

		if (x == N-1 && y == M-1) {
			return tmp[x][y];
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInside(nx, ny) && !visit[nx][ny] && maps[nx][ny] == 1) {
				visit[nx][ny] = true;
				tmp[nx][ny] = tmp[x][y] + 1;
				q.push({ nx, ny });
			}
		}
	}
	if (x != N-1 || y != M-1) {
		return -1;
	}
}

int solution(vector<vector<int> > maps) {
	int answer = 0;
	N = maps.size();
	M = maps[0].size();
	answer = bfs(0, 0, maps);
	return answer;
}

int main(void) {
	vector<vector<int>> maps = { {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0,1}, {0, 0, 0, 0, 1} };
	int result = solution(maps);
	cout << result << "\n";
	return 0;
}