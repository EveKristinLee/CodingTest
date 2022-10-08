#include <iostream>
#include <cstring>

using namespace std;

int N; //ũ��
char map[101][101];
bool visit[101][101] = { false, };
char tmp[101][101] = { 0, }; //���ϻ��� ������ ���� �׷���

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int cnt = 0; //���ϻ����� ���
int notCnt = 0; //���ϻ����� �ƴ� ���

bool isInside(int x, int y) {
	return (x >= 0) && (x < N) && (y >= 0) && (y < N);
}

void dfs(int x, int y) {
	visit[x][y] = true;
	char color = map[x][y];

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && !visit[nx][ny]) {
			if (map[nx][ny] == color) {
				dfs(nx, ny);
			}
		}
	}
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}

	//���ϻ����� �ƴ� ����� �� ����
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visit[i][j]) {
				dfs(i, j);
				notCnt++;
			}
		}
	}

	//���ϻ����� ��� �׷��� �����
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 'G') {
				map[i][j] = 'R';
			}
		}
	}

	memset(visit, false, sizeof(visit));

	//���ϻ����� ����� �� ����
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visit[i][j]) {
				dfs(i, j);
				cnt++;
			}
		}
	}
	cout << notCnt << " " << cnt << "\n";
	
	return 0;
}