#include <iostream>
#include <vector>

using namespace std;

int num[21];
int N; //정수의 개수
int S; //정수
int cnt = 0;

void makeSum(int idx, int sum) {
	if (idx >= N) { //범위 초과시
		return;
	}
	if (sum + num[idx] == S) { //조건 성립시
		cnt++;
	}
	makeSum(idx + 1, sum); //해당 idx의 숫자를 안더했을 경우
	makeSum(idx + 1, sum + num[idx]); //해당 idx의 숫자를 더했을 경우
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