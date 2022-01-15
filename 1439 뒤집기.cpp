#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	string s;
	cin >> s;
	int cntZero = 0;
	int cntOne = 0;
	
	for (int i = 0; i < s.length(); i++) {
		if (s[i] != s[i + 1]) {
			if (s[i] == '0') {
				cntZero++;
			}
			else {
				cntOne++;
			}
		}
	}
	cout << min(cntZero, cntOne) << "\n";
	
	return 0;
}