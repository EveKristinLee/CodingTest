#include <iostream>
#include <vector>

using namespace std;

int num[21];
int N; //������ ����
int S; //����
int cnt = 0;

void makeSum(int idx, int sum) {
	if (idx >= N) { //���� �ʰ���
		return;
	}
	if (sum + num[idx] == S) { //���� ������
		cnt++;
	}
	makeSum(idx + 1, sum); //�ش� idx�� ���ڸ� �ȴ����� ���
	makeSum(idx + 1, sum + num[idx]); //�ش� idx�� ���ڸ� ������ ���
}

int main(void) {
	cin >> N >> S;
	for (int i = 0; i < N; i++) {
		cin >> num[i];
	}

	makeSum(0, 0);
	cout << cnt << "\n";

	return 0;
}