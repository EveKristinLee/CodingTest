#include<iostream>

using namespace std;

int main(void) {
	int N;
	int sum = 0;
	cin >> N;
	for (int i = 0; i < N; i++) {
		char a;
		cin >> a;
		sum += a - '0';
	}
	cout << sum << "\n";
	return 0;
}