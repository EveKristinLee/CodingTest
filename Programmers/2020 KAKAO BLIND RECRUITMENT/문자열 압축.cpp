#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string s) {
	int answer = 987654321;;
	int upTo = s.size() / 2; //자를 문자 최대 길이
	int start = 1;
	if (s.size() == 1) {
		return 1;
	}
	while (start <= upTo) {
		vector<string> count; //잘라놓은 문자 저장
		string tmp = "";
		int minCnt = 0;
		for (int i = 0; i < s.size(); i++) {
			tmp = s.substr(i, start);
			count.push_back(tmp);
			i = i + start - 1;
		}
		int cnt = 1;
		for (int i = 0; i < count.size() - 1; i++) {
			if (count[i] == count[i + 1]) { //문자가 같으면
				cnt++;
			}
			if (count[i] != count[i + 1] || (i + 1) == count.size() - 1){
				//문자가 같지않거나 마지막 배열일때
				if (cnt >= 2) { //같은 문자가 2개 이상일때
					minCnt += to_string(cnt).size();
					minCnt += count[i].size();
					if (count[i] != count[i + 1] && (i + 1) == count.size() - 1) {
						//다음 문자와 같지않고 마지막 배열이면
						minCnt += count[i + 1].size();
					}
				}
				else {//같은문자가 1개일때
					minCnt += count[i].size();
					if ((i + 1) == count.size() - 1) {
						//다음문자가 마지막일때
						minCnt += count[i + 1].size();
					}
				}
				cnt = 1;
			}
		}
		answer = min(minCnt, answer);
		start++;
	}
	return answer;
}

int main(void) {
	string s = "a";
	int result = solution(s);
	cout << "result : " << result << "\n";
	return 0;
}