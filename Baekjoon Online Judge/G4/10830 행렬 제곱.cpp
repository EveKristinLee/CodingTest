#include <iostream>

using namespace std;

long long n, b;
long long ans[6][6];
long long tmp[6][6];
long long a[6][6];

void printMat() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << ans[i][j] << " ";
		}
		cout << "\n";
	}
}

void mul(long long x[6][6], long long y[6][6]) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) { //가로
			tmp[i][j] = 0;
			for (int k = 0; k < n; k++) { //세로
				tmp[i][j] += (x[i][k] * y[k][j]);
			}
			tmp[i][j] %= 1000;
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			x[i][j] = tmp[i][j];
		}
	}
}

int main(void) {
	cin >> n >> b;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
		ans[i][i] = 1; //단위행렬
	}

	while (b > 0) {
		if (b % 2 != 0) {
			mul(ans, a);
		}
		mul(a, a);
		b /= 2;
	}
	printMat();
	return 0;
}