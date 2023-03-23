#include <iostream>
#include <cstring>

using namespace std;

int map[301][301] = { 0, };
bool visit[301][301] = { false, };

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int N; //��
int M; //��

int year = 0; //������ �и��Ǵ� ������ �ð�
int cnt = 0; //�и��� ������ ����

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
			int water = 0; //������ �ٴ��� ��
			if (tmp[i][j] > 0) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if (tmp[nx][ny] == 0) {
						water++;
					}
				}
				map[i][j] -= water;
				if (map[i][j] < 0) { //������ ���̴� 0���� �۾����� ����
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
					if (cnt >= 2) { //������ ������ ������ 2������ ���ٸ�
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
