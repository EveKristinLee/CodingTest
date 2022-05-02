#include <iostream>
#include <string>
#include <cctype>

using namespace std;

string solution(string s) {
	string answer = "";
	bool first = true;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == ' ') {
			first = true;
			answer += " ";
		}
		else {
			if (first) {
				if (isalpha(s[i])) {
					answer += toupper(s[i]);
				}
				else {
					answer += s[i];
				}
				first = false;
			}
			else {
				answer += tolower(s[i]);
			}
		}
	}
	return answer;
}

int main(void) {
	string s = "for the last week";
	string result = solution(s);
	cout << result << "\n";
	return 0;
}