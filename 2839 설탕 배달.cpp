#include <iostream>

using namespace std;

//5kg 짜리를 최대한 많이 써야 하므로
//처음의 수가 5의 배수가 아니면 5의 배수가 될때까지 -3
//-3을 하다가 음수가 된다면 -1출력
int main(void) {
	int num3 = 0;
	int num5 = 0;
	int N;
	cin >> N;
	while (N % 5 != 0 && N >= 0) {
		N -= 3;
		num3++;
	}
	if (N < 0) {
		cout << "-1" << "\n";
	}
	else {
		num5 = N / 5;
		cout << num3 + num5 << "\n";
	}
	return 0;
}