#include <iostream>
#include <cstring>

using namespace std;

int map[301][301] = { 0, };
bool visit[301][301] = { false, };

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int N; //행
int M; //열

int year = 0; //빙산이 분리되는 최초의 시간
int cnt = 0; //분리된 빙산의 개수

bool flag = false;

bool isInside(int x, int y) {
	return (x >= 0) && (x < N) && (y >= 0) && (y < M);
}

void printMap() {
	cout << "-------------printMap------------" << "\n";
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << map[i][j] << " ";
		}
		cout << "\n";
	}
	cout << "-------------printMap------------" << "\n";
}

void melt() {
	int tmp[301][301];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			tmp[i][j] = map[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int water = 0; //인접한 바다의 수
			if (tmp[i][j] > 0) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if (tmp[nx][ny] == 0) {
						water++;
					}
				}
				map[i][j] -= water;
				if (map[i][j] < 0) { //빙산의 높이는 0보다 작아지지 않음
					map[i][j] = 0;
				}
			}
		}
	}
}

void dfs(int x, int y) {
	visit[x][y] = true;

	if (map[x][y] == 0) {
		return;
	}

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && !visit[nx][ny]) {
			dfs(nx, ny);
		}
	}
}
 

int main(void) {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}
	//printMap();
	while (1) {
		memset(visit, false, sizeof(visit));
		flag = false;
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] && !visit[i][j]) {
					cnt++;
					if (cnt >= 2) { //떨어진 빙산의 개수가 2개보다 많다면
						flag = true;
						break;
					}
					dfs(i, j);
				}
			}
		}
		if (flag == true) {
			break;
		}
		if (cnt == 0) {
			year = 0;
			break;
		}
		melt();
		printMap();
		year++;
	}

	cout << year << "\n";
	return 0;
}
