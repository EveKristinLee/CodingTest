#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>

using namespace std;

vector<string> combi;

long long calc(long long a, long long b, char op) {
	if (op == '+') {
		return a + b;
	}
	else if (op == '*') {
		return a * b;
	}
	else if (op == '-') {
		return a - b;
	}
}

//순열 만들기
void getComb(vector<char> oper, string now, vector<bool>& visit) {
	if (now.size() == oper.size()) {
		combi.push_back(now);
		return;
	}
	for (int i = 0; i < oper.size(); i++) {
		if (!visit[i]) {
			visit[i] = true;
			getComb(oper, now + oper[i], visit);
			visit[i] = false;
		}
	}

}

long long solution(string expression) {
	long long answer = 0;
	vector<long long> num;
	vector<char> oper;
	vector<char> allOper;

	string tmpNum = "";
	for (int i = 0; i < expression.size(); i++) {
		if (expression[i] >= '0' && expression[i] <= '9') {
			tmpNum += expression[i];
		}
		else {
			num.push_back(stoll(tmpNum));
			tmpNum = "";
			oper.push_back(expression[i]);
			allOper.push_back(expression[i]);
		}
	}
	num.push_back(stoll(tmpNum));
	sort(oper.begin(), oper.end());
	oper.erase(unique(oper.begin(), oper.end()), oper.end());

	vector<bool> visit(oper.size(), false);
	getComb(oper, "", visit);

	for (int i = 0; i < combi.size(); i++) {
		vector<long long> dupNum = num;
		vector<char> dupOper = allOper;
		for (int j = 0; j < combi[i].size(); j++) {
			for (int k = 0; k < dupOper.size(); k++) {
				if (dupOper[k] == combi[i][j]) { //해당 연산자 만나면
					//계산 후 저장
					dupNum[k] = calc(dupNum[k], dupNum[k + 1], dupOper[k]);
					//계산 한 값 삭제
					dupNum.erase(dupNum.begin() + (k + 1));
					//계산한 연산자 삭제
					dupOper.erase(dupOper.begin() + k);
					k -= 1;
				}
			}
		}
		dupNum[0] = abs(dupNum[0]);
		answer = max(answer, dupNum[0]);
	}
	return answer;
}

int main(void) {
	string expression = "100-200*300-500+20";
	long long result = solution(expression);
	cout << result << "\n";
	return 0;
}