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
		//배열에 넣을때 10,007로 나눠줘야 한다
		//int형인 배열이 감당하지 못함
		tile[i] = (tile[i - 2] + tile[i - 1]) % 10007;
	}
	cout << tile[n] << "\n";

	return 0;
}