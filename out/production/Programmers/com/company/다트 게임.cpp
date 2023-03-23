#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(string dartResult) {
	int answer = 0;
	vector<double> nums;
	vector<int> originNums;
	int tmpNum = 0;
	for (int i = 0; i < dartResult.size(); i++) {
		string tmpString = "";
		if ('0' <= dartResult[i] && '9' >= dartResult[i]) {
			tmpString += dartResult[i];
			if ('0' <= dartResult[i + 1] && '9' >= dartResult[i + 1]) {
				tmpString += dartResult[i + 1];
				i += 1;
			}
			tmpNum = stoi(tmpString);
		}
		else {//숫자가 아닐때
			if (dartResult[i] == 'S') {
				nums.push_back(tmpNum);
			}
			else if (dartResult[i] == 'D') {
				nums.push_back(tmpNum * tmpNum);
			}
			else if (dartResult[i] == 'T') {
				nums.push_back(tmpNum * tmpNum * tmpNum);
			}
			else if (dartResult[i] == '*') {
				if (nums.size() >= 2) {
					int cnt = nums.size() - 1;
					for (int j = cnt; j >= cnt -1; j--) {
						nums[j] *= 2;
					}
				}
				else {
					nums[0] *= 2;
				}
			}
			else if (dartResult[i] == '#') {
				nums[nums.size() - 1] *= (-1);
			}
		}
	}
	for (int i = 0; i < nums.size(); i++) {
		answer += nums[i];
	}
	return answer;
}

int main(void) {
	string dartResult = "1D2S3T*";
	int result = solution(dartResult);
	cout << result << "\n";
	return 0;
}