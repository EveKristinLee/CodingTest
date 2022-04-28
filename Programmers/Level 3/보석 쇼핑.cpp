#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(vector<string> gems) {
	vector<int> answer(2);
	map<string, int> jewels;
	for (string i : gems) {
		jewels[i] += 1;
	}
	int total = jewels.size();
	cout << total << "\n";
	jewels.clear();

	int dist = 987654321;
	int startIdx = 0;
	int endIdx = 0;

	while (true) {
		if (jewels.size() == total) { //보석을 다 구했을때
			if (endIdx - startIdx < dist) {
				dist = endIdx - startIdx;
				answer[0] = startIdx + 1;
				answer[1] = endIdx;
			}

			if (jewels[gems[startIdx]] == 1) { //보석개수가 1개일때
				jewels.erase(gems[startIdx]);
				startIdx++;
			}
			else { //보석개수가 여러개일때
				jewels[gems[startIdx]]--;
				startIdx++;
			}
		}
		else if (endIdx == gems.size()) {
			break;
		}
		else {//보석을 다 못구했을때
			jewels[gems[endIdx]]++;
			endIdx++; //endIdx값 증가시키면서 gems[endIdx]에 해당하는 보석 갯수 증가
		}
	}
	return answer;
}

int main(void) {
	vector<string> gems = { "ZZZ", "YYY", "NNNN", "YYY", "BBB" };
	vector<int> result = solution(gems);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}