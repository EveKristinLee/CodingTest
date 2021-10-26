#include <iostream>

using namespace std;

int main(void) {
	int tc;
	cin >> tc;
	for (int t = 0; t < tc; t++) {
		int a;
		char c;
		int b;
		cin >> a >> c >> b;
		cout << a + b << "\n";
	}
	return 0;
}