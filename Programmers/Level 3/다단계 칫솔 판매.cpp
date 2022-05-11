#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

unordered_map<string, string> person;
unordered_map<string, int> profit;

void calcProfit(string name, int money) {
	if (name == "minho") {
		return;
	}

	int forRefer = money * 0.1;
	profit[name] += (money - forRefer);
	if (forRefer < 1) {
		return;
	}
	calcProfit(person[name], forRefer);
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
	vector<int> answer;
	for (int i = 0; i < enroll.size(); i++) {
		if (referral[i] != "-") {
			person[enroll[i]] = referral[i];
		}
		else {
			person[enroll[i]] = "minho";
		}
	}

	for (int i = 0; i < seller.size(); i++) {
		calcProfit(seller[i], amount[i] * 100);
	}

	for (int i = 0; i < enroll.size(); i++) {
		answer.push_back(profit[enroll[i]]);
	}

	return answer;
}

int main(void) {
	vector<string> enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
	vector<string> referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
	vector<string> seller = { "young", "john", "tod", "emily", "mary" };
	vector<int> amount = { 12, 4, 2, 5, 10 };
	vector<int> result = solution(enroll, referral, seller, amount);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}