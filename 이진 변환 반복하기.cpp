#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int cnt0(string s) {
	int cnt = 0;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '0') {
			cnt++;
		}
	}
	return cnt;
}

string change(int c) {
	string s = "";
	while (c != 0) {
		if (c % 2 == 0) {
			s += "0";
		}
		else {
			s += "1";
		}
		c /= 2;
	}
	reverse(s.begin(), s.end());
	return s;
}

vector<int> solution(string s) {
	vector<int> answer;
	int cntZero = 0;
	int cnt = 0;
	while (s != "1") {
		cnt++; //변환 횟수
		cout << "cnt : " << cnt << "\n";
		int zero = cnt0(s);
		cout << "zero : " << zero << "\n";
		cntZero += zero;
		int c = s.size() - zero; //1의 길이
		s = change(c);
		cout << "s : " << s << "\n";
	}
	
	answer.push_back(cnt);
	answer.push_back(cntZero);

	return answer;
}

int main(void) {
	string s = "110010101001";
	vector<int> result = solution(s);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}