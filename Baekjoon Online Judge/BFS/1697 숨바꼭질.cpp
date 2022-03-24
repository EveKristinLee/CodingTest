#include <iostream>
#include <queue>

using namespace std;

int N; // ���� ��ġ
int K; // ���� ��ġ

int visit[100001] = { false, };

int sec = 0; //�ּ� �ð�

void bfs(int N, int K) {
	queue<pair<int, int>> q;
	q.push(make_pair(N, 0));
	visit[N] = true;

	while (!q.empty()) {
		int curPos = q.front().first;
		int cusSec = q.front().second;
		q.pop();

		//�������� ����
		if (curPos == K) {
			sec = cusSec;
			return;
		}

		//�̵��� �� �ִ� ������ ����� ��
		if (curPos - 1 >= 0 && !visit[curPos - 1]) {
			q.push(make_pair(curPos - 1, cusSec + 1));
			visit[curPos - 1] = true;
		}
		if (curPos + 1 < 100001 && !visit[curPos + 1]) {
			q.push(make_pair(curPos + 1, cusSec + 1));
			visit[curPos + 1] = true;
		}
		if (curPos * 2 < 100001 && !visit[curPos * 2]) {
			q.push(make_pair(curPos * 2, cusSec + 1));
			visit[curPos * 2] = true;
		}
	}

}

int main(void) {

	cin >> N >> K;
	bfs(N, K);

	cout << sec << "\n";

	return 0;
}