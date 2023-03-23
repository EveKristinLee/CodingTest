#include<iostream>

using namespace std;

int com; //��ǻ���� ��
int n; //����� ��ǻ�ͽ��� ��

int map[100][100] = { 0, };
int visit[100] = { false, };
int cnt = 0;

void dfs(int x) {
	visit[x] = true;


	for (int i = 1; i <= com; i++) {
		if (!visit[i] && map[x][i] == 1) {
			cnt++; //���⿡�� +�� ����� 1�� ��ǻ���� ������ �������� ����
			dfs(i);
		}
	}
}


int main(void) {
	cin >> com;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		map[a][b] = 1;
		map[b][a] = 1;
	}

	dfs(1);

	cout << cnt << "\n";
	return 0;
}