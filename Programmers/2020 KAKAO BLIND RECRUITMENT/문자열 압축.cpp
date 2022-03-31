#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string s) {
	int answer = 987654321;;
	int upTo = s.size() / 2; //�ڸ� ���� �ִ� ����
	int start = 1;
	if (s.size() == 1) {
		return 1;
	}
	while (start <= upTo) {
		vector<string> count; //�߶���� ���� ����
		string tmp = "";
		int minCnt = 0;
		for (int i = 0; i < s.size(); i++) {
			tmp = s.substr(i, start);
			count.push_back(tmp);
			i = i + start - 1;
		}
		int cnt = 1;
		for (int i = 0; i < count.size() - 1; i++) {
			if (count[i] == count[i + 1]) { //���ڰ� ������
				cnt++;
			}
			if (count[i] != count[i + 1] || (i + 1) == count.size() - 1){
				//���ڰ� �����ʰų� ������ �迭�϶�
				if (cnt >= 2) { //���� ���ڰ� 2�� �̻��϶�
					minCnt += to_string(cnt).size();
					minCnt += count[i].size();
					if (count[i] != count[i + 1] && (i + 1) == count.size() - 1) {
						//���� ���ڿ� �����ʰ� ������ �迭�̸�
						minCnt += count[i + 1].size();
					}
				}
				else {//�������ڰ� 1���϶�
					minCnt += count[i].size();
					if ((i + 1) == count.size() - 1) {
						//�������ڰ� �������϶�
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