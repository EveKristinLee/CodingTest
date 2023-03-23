#include<iostream>

using namespace std;
//0의 개수는 1부터 시작하는 피보나치 함수이고
//1의 개수는 0부터 시작하는 피보나치 함수이다
int num[41];
int fibo(int n) {
	if (n == 0) {
		num[0] = 0;
		return 0;
	}
	else if (n == 1) {
		num[1] = 1;
		return 1;
	}
	else {
		if (num[n] != 0) {
			return num[n];
		}
		return num[n] = fibo(n - 1) + fibo(n - 2);
	}
} 
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	for (int t = 0; t < T; t++) {
		int N;
		cin >> N; 
		fibo(N);
		if (N == 0) {
			cout << "1 0" << "\n";
		}
		else {
			cout << num[N-1] <<" "<< num[N] << "\n";
		}
	}
	return 0;
}