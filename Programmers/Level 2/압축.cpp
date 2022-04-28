#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

map<string, int> Dic;
int num = 1;

void makeDic() {
	for (char c = 'A'; c <= 'Z'; c++) {
		string s = "";
		s += c;
		Dic[s] = num++;
	}
}

vector<int> solution(string msg) {
	vector<int> answer;
	makeDic();
	for (int i = 0; i < msg.size(); i++) {
		string tmp = "";
		tmp += msg[i];
		int idx = 0;
		int nextIdx = i; 
		//사전에 있으면
		while (Dic.find(tmp) != Dic.end()) {
			idx++;
			nextIdx++;
			tmp += msg[nextIdx];
		}
		//사전에 없으면
		i += idx -1; //idx수 만큼 더 앞으로 움직여 줘야함 + 반복문에서 더해지는 1 빼기
		Dic.emplace(tmp, num++);
		tmp.pop_back();//뒤에 글자 하나 빼고
		answer.push_back(Dic[tmp]);
	}
	return answer;
}

int main(void) {
	string msg = "ABABABABABABABAB";
	vector<int> result = solution(msg);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}