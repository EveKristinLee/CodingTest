#include <iostream>

using namespace std;

int R; //����
int C; //����

char map[21][21];
int visit[27] = { false, }; //���ĺ� = 26

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int cnt = 0; //���� ���� ĭ��
int MaxCnt = 1; //���� ������ �ִ� �ִ� ĭ��

bool isInside(int x, int y) {
	return (x >= 1) & (x <= R) && (y >= 1) && (y <= C);
}

void dfs(int x, int y, int cnt) {
	if (cnt > MaxCnt) {
		MaxCnt = cnt;
	}

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && !visit[map[nx][ny] - 'A']) {
			//��Ʈ��ŷ�� �ʿ��� ����
			//�̹� ������ ĭ���� �� ���� �� �� �ִ� ��ΰ� ���� �� �ֱ⶧��
			visit[map[nx][ny] - 'A'] = true;
			dfs(nx, ny, cnt + 1);
			visit[map[nx][ny] - 'A'] = false;
		}
	}
}

int main(void) {
	cin >> R >> C;
	for (int i = 1; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			cin >> map[i][j];
		}
	}
	visit[map[1][1] - 'A'] = true; //�����ϴ� ĭ ����
	dfs(1, 1, 1);

	cout << MaxCnt << "\n";

	return 0;
}