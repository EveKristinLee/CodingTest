#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int V; //������ ����
int E; //������ ����

vector<int> link[20001];
int visit[20001] = { 0, };

void clear() {
	for (int i = 1; i <= V; i++) {
		link[i].clear();
		visit[i] = 0;
	}
}

void bfs(int start) {
	queue<int> q;
	q.push(start);
	visit[start] = 1; //1 = Red, 2 = Blue
	
	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < link[x].size(); i++) {
			//�����̶��
			if (visit[x] == 1) {
				//����� ���� �Ķ����� ��ĥ
				if (visit[link[x][i]] == 0) {
					q.push(link[x][i]);
					visit[link[x][i]] = 2;
				}
			}
			//�Ķ��̶��
			else if (visit[x] == 2) {
				//����� ���� �������� ��ĥ
				if (visit[link[x][i]] == 0) {
					q.push(link[x][i]);
					visit[link[x][i]] = 1;
				}
			}
		}
	}
}

bool isBinary() {
	//������ ���� ���� �ٸ��� Ȯ��
	for (int i = 1; i <= V; i++) {
		for (int j = 0; j < link[i].size(); j++) {
			//������ ���� ���� ������ �̺� �׷��� x
			if (visit[i] == visit[link[i][j]]) {
				return false;
			}
		}
	}
	return true;
}

int main(void) {
	int K; //�׽�Ʈ ���̽�
	cin >> K;
	for (int k = 0; k < K; k++) {
		cin >> V >> E;
		//�ʱ�ȭ
		clear();
		for (int i = 1; i <= E; i++) {
			int a, b;
			cin >> a >> b;
			link[a].push_back(b);
			link[b].push_back(a);
		}

		for (int i = 1; i <= V; i++) {
			if (visit[i] == 0) {
				bfs(i);
			}
		}

		if (isBinary()) {
			cout << "YES" << "\n";
		}
		else {
			cout << "NO" << "\n";
		}

	}

	return 0;
}