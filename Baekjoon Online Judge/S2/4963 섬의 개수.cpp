#include <iostream>

using namespace std;

int w; // 너비
int h; // 높이

int map[51][51] = { 0, };
bool visit[51][51] = { false, };

int dx[8] = { 0, 0, 1, -1, 1, 1, -1, -1 };
int dy[8] = { 1, -1, 0, 0, 1, -1, 1, -1 };

int cnt = 0; //섬의 수

bool isInside(int x, int y) {
	return (x >= 0) && (x < h) && (y >= 0) && (y < w);
}

void init() {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			map[i][j] = 0;
			visit[i][j] = false;
		}
	}
}

void dfs(int x, int y) {
	visit[x][y] = true;

	if (map[x][y] == 0) {
		return;
	}

	for (int i = 0; i < 8; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && visit[nx][ny] == false) {
			dfs(nx, ny);
		}
	}

}

int main(void) {

	while (1) {
		cin >> w >> h;
		if (w == 0 && h == 0) {
			break;
		}

		else {
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					cin >> map[i][j];
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && visit[i][j] == false) {
						cnt++;
						dfs(i, j);
					}
				}
			}

			cout << cnt << "\n";
			init();
			cnt = 0;
		}

	}

	return 0;
}