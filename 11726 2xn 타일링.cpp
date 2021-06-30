#include <iostream>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int tile[1001];
	int n;
	cin >> n;
	tile[0] = 1;
	tile[1] = 1;
	for (int i = 2; i <= n; i++) {
		//�迭�� ������ 10,007�� ������� �Ѵ�
		//int���� �迭�� �������� ����
		tile[i] = (tile[i - 2] + tile[i - 1]) % 10007;
	}
	cout << tile[n] << "\n";

	return 0;
}