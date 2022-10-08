#include <iostream>
#include <queue>
#include <cstring>
#include <algorithm>

using namespace std;

int T; //테스트 케이스
int I; //체스판의 크기

int dx[8] = { -2, -2, -1, -1, 1, 1, 2, 2 };
int dy[8] = { -1, 1, -2, 2, -2, 2, -1, 1 };

int chess[301][301];
bool visit[301][301];
int way[301][301]; //경로

int startX;
int startY;
int endX;
int endY;

bool isInside(int x, int y) {
	return (x >= 0) && (x < I) && (y >= 0) && (y < I);
}

void bfs(int x, int y) {
	queue<pair<int, int>> q;
	q.push(make_pair(x, y));
	visit[x][y] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		if (x == endX && y == endY) {
			cout << way[x][y];
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInside(nx, ny) && !visit[nx][ny]) {
				visit[nx][ny] = true;
				q.push(make_pair(nx, ny));
				way[nx][ny] = way[x][y] + 1;
			}
		}
	}
}

int main(void) {
	cin >> T;
	
	for (int t = 0; t < T; t++) {
		cin >> I;
		//초기화
		for (int i = 0; i < I; i++) {
			memset(chess[i], 0, sizeof(int) * I);
		}
		for (int i = 0; i < I; i++) {
			memset(visit[i], 0, sizeof(bool) * I);
		}
		for (int i = 0; i < I; i++) {
			memset(way[i], 0, sizeof(int) * I);
		}

		//테스트케이스 시작
		cin >> startX >> startY;
		cin >> endX >> endY;
		bfs(startX, startY);
		cout << "\n";
	}

	return 0;
}