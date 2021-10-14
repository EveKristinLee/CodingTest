#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(string s) {
	string answer = "";
	for (int i = 0; i < s.length(); i++) {
		if (s[i] >= '0' && s[i] <= '9') {
			answer += s[i];
		}
		else {
			if (s[i] == 'z') {
				answer += '0';
				i += 3;
			}
			else if (s[i] == 'o') {
				answer += '1';
				i += 2;
			}
			else if (s[i] == 't') {
				if (s[i + 1] == 'w') {
					answer += '2';
					i += 2;
				}
				else {
					answer += '3';
					i += 4;
				}
			}
			else if (s[i] == 'f') {
				if (s[i + 1] == 'o') {
					answer += '4';
					i += 3;
				}
				else {
					answer += '5';
					i += 3;
				}
			}
			else if (s[i] == 's') {
				if (s[i + 1] == 'i') {
					answer += '6';
					i += 2;
				}
				else {
					answer += '7';
					i += 4;
				}
			}
			else if (s[i] == 'e') {
				answer += '8';
				i += 4;
			}
			else if (s[i] == 'n') {
				answer += '9';
				i += 3;
			}
		}
	}
	return stoi(answer);
}

int main(void) {
	int ans = 0;
	ans = solution("123");
	cout << ans << "\n";
	return 0;
}



///////////////////////////////////
/*
#include <bits/stdc++.h>
using namespace std;

int solution(string s) {
	s = regex_replace(s, regex("zero"), "0");
	s = regex_replace(s, regex("one"), "1");
	s = regex_replace(s, regex("two"), "2");
	s = regex_replace(s, regex("three"), "3");
	s = regex_replace(s, regex("four"), "4");
	s = regex_replace(s, regex("five"), "5");
	s = regex_replace(s, regex("six"), "6");
	s = regex_replace(s, regex("seven"), "7");
	s = regex_replace(s, regex("eight"), "8");
	s = regex_replace(s, regex("nine"), "9");
	return stoi(s);
}

*/