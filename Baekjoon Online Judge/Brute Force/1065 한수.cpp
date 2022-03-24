#include <iostream>
#include <string>

using namespace std;

bool isHan(int x) {
	string st = to_string(x);
	int dif = st[0] - st[1];
	for (int i = 1; i < st.size() - 1; i++) {
		if (st[i] - st[i + 1] != dif) {
			return false;
		}
	}
	return true;
}

int main(void) {
	int n;
	int cnt = 0;
	cin >> n;
	if (n < 100) {
		cnt = n;
	}
	else {
		cnt = 99;
		for (int i = 100; i <= n; i++) {
			if (isHan(i)) {
				cnt++;
			}
		}
	}
	cout << cnt << "\n";
	return 0;
}