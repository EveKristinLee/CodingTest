#include <iostream>
#include <string>
#include <stack>

using namespace std;

bool isAlright(string s) {
	stack<char> st;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '(') {
			st.push(s[i]);
		}
		else {
			if (!st.empty()) {
				st.pop();
			}
		}
	}
	if (st.empty()) {
		return true;
	}
	else {
		return false;
	}
}

int splitSt(string s) {
	int cntRight = 0; //)
	int cntLeft = 0; //(

	for (int i = 0; i < s.size(); i++) {
		if ((cntRight == cntLeft && cntRight != 0)) {
			return i;
		}
		if (s[i] == '(') {
			cntLeft++;
		}
		else {
			cntRight++;
		}
	}
}

string recur(string p) {
	if (p.size() == 0) {
		return "";
	}
	int idx = splitSt(p);
	string u = p.substr(0, idx);
	string v = "";
	if (u != p) {
		v = p.substr(idx);
	}

	//u가 올바른 문자열이 아니라면
	if (!isAlright(u)) {
		string tmp = "(";
		tmp += recur(v);
		tmp += ")";
		u = u.substr(1, u.size() - 2); //앞뒤 자르기
		for (int i = 0; i < u.size(); i++) {
			if (u[i] == '(') {
				tmp += ')';
			}
			else {
				tmp += '(';
			}
		}
		return tmp;
	}
	else { //u가 올바른 문자열이라면
		//nowString += recur(v, "");
		return u + recur(v);
	}
}

string solution(string p) {
	string answer = "";
	if (isAlright(p)) {
		return p;
	}
	else {
		answer = recur(p);
	}

	return answer;
}

int main(void) {
	string p = ")(";
	string result = solution(p);
	cout <<"result : " << result << "\n";
	return 0;
}