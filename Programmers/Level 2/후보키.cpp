#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int column = 0;
int row = 0;
vector<int> combi;
bool visit[9] = { false };
vector<string> Combination; //모든 조합 저장

//조합 만들기
void combination(int idx, int nowCnt, int totalCnt) { 
	if (totalCnt == nowCnt) {
		string tmp = "";
		for (int i = 0; i < combi.size(); i++) {
			tmp += to_string(combi[i]);
		}
		Combination.push_back(tmp);
		return;
	}

	for (int i = idx; i < column; i++) {
		if (!visit[i]) {
			visit[i] = true;
			combi.push_back(i);
			combination(i, nowCnt + 1, totalCnt);
			visit[i] = false;
			if (!combi.empty()) {
				combi.pop_back();
			}
		}
	}
}

//유일성 확인
bool checkUni(string s, vector<vector<string>> relation) {
	vector<string> uni;
	for (int i = 0; i < row; i++) {
		string tmp = "";
		for (int j = 0; j < s.size(); j++) {
			tmp += relation[i][s[j] - '0'];
		}
		if (find(uni.begin(), uni.end(), tmp) != uni.end()) {
			return false;
		}
		else {
			uni.push_back(tmp);
		}
	}
	return true;
}

//최소성 확인
void checkMin() {
	for (int i = 0; i < Combination.size(); i++) {
		for (int j = i + 1; j < Combination.size(); j++) {
			bool notMin = true;
			for (int k = 0; k < Combination[i].size(); k++) {
				if (Combination[j].find(Combination[i][k]) == string::npos) {
					notMin = false;
					break;
				}
				if (notMin && k == Combination[i].size() -1) {
					Combination.erase(Combination.begin() + j);
					j -= 1;
				}
			}
		}
	}
}

int solution(vector<vector<string>> relation) {
	int answer = 0;
	column = relation[0].size();
	row = relation.size();
	
	for (int i = 1; i <= column; i++) {
		combination(0, 0, i);
	}

	for (int i = 0; i < Combination.size(); i++) {
		if (!checkUni(Combination[i], relation)) {
			Combination.erase(Combination.begin() + i);
			i -= 1;
		}
	}

	checkMin();
	answer = Combination.size();
	return answer;
}

int main(void) {
	vector<vector<string>> relation = { {"100","ryan","music","2"}, {"200","apeach","math","2"},
	{"300","tube","computer","3"}, {"400","con","computer","4"},
	{"500","muzi","music","3"}, {"600","apeach","music","2"} };
	int result = solution(relation);
	cout << result << "\n";
	return 0;
}