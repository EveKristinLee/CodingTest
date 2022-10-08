#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

//��, ��
int dx[2] = { 0, 1 };
int dy[2] = { 1, 0 };
int n;
char map[51][51];

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < n) && (y < n);
}

//���� �� ���� �κ� ã��
int findLongest() {
	int maxCnt = 0;

	//�� Ȯ��
	for (int i = 0; i < n; i++) {
		int tmpCnt = 1;
		for (int j = 1; j < n; j++) {
			if (map[i][j] == map[i][j - 1]) {
				tmpCnt++;
			}
			else {
				maxCnt = max(tmpCnt, maxCnt);
				tmpCnt = 1;
			}
		}
		maxCnt = max(tmpCnt, maxCnt);
	}

	//�� Ȯ��
	for (int j = 0; j < n; j++) {
		int tmpCnt = 1;
		for (int i = 1; i < n; i++) {
			if (map[i-1][j] == map[i][j]) {
				tmpCnt++;
			}
			else {
				maxCnt = max(tmpCnt, maxCnt);
				tmpCnt = 1;
			}
		}
		maxCnt = max(tmpCnt, maxCnt);
	}

	return maxCnt;
}


//������ ��ĭ ��ȯ
int change(int x, int y) {
	int maxCnt = 0;
	for (int i = 0; i < 2; i++) {
		int tmp = 0;
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (isInside(nx, ny)) {
			if (map[nx][ny] != map[x][y]) {
				//������ ��ĭ �ٲٱ�
				swap(map[x][y], map[nx][ny]);

				//���� �� �� Ȯ��
				tmp = findLongest();

				//�ٽ� ����ġ
				swap(map[x][y], map[nx][ny]);
			}
			else {
				continue;
			}
		}
		maxCnt = max(maxCnt, tmp);
	}
	return maxCnt;
}

int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}
	int maxCnt = 0;
	maxCnt = findLongest();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int tmpCnt = change(i, j);
			maxCnt = max(tmpCnt, maxCnt);
		}
	}
	cout << maxCnt << "\n";
	return 0;
}