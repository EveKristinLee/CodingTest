#include <iostream>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	string quize;
	
	for (int tc = 0; tc < T; tc++) {
		int cnt = 0;
		int sum = 0;
		cin >> quize;
		for (int i = 0; i < quize.size(); i++) {
			if (quize[i] == 'O') {
				cnt++;
				sum += cnt;
			}
			else {
				cnt = 0;
			}
		}
		cout << sum << "\n";
	}
	return 0;
}