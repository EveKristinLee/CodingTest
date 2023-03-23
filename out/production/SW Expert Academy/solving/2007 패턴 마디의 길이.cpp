#include <iostream>
#include <string>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		string s;
		cin >> s;
		string word = "";
		int len = 1;
		word += s[0];
		for (int i = 1; i < s.size(); i++) {
			if (s[i] != word[0]) {
				word += s[i];
				len++;
			}
			else if (s[i] == word[0]){
				bool same = true;
				for (int j = 0; j < word.size(); j++) {
					if (word[j] != s[i + j]) {
						same = false;
						break;
					}
				}
				if (same) {
					break;
				}
				else {
					word += s[i];
					len++;
				}
			}
		}
		cout << "#" << t << " " << len << "\n";
	}
	return 0;
}