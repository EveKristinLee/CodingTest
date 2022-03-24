#include <iostream>
#include <algorithm>

using namespace std;

bool comp(int x, int y) {
	return x > y;
}

int main(void) {
	int N; //������ ����
	int K; //�������ϴ� ��
	int total = 0; //���� ������ �ּڰ�
	int coin[11];
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> coin[i];
	}
	sort(coin, coin + N, comp);

	for (int i = 0; i < N; i++) {
		while (K >= coin[i]) {
			K -= coin[i];
			total++;
		}
	}
	cout << total << "\n";
	return 0;
}