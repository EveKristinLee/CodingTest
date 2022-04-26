#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <map>

using namespace std;

vector<string> split(string s, char delim) {
	vector<string> result;
	stringstream ss(s);
	string buffer;

	while (getline(ss, buffer, delim)) {
		result.push_back(buffer);
		cout << buffer << "/\n";
	}
	return result;
}

int getTime(string s) {
	int sum = 0;
	string tmp = s.substr(0, 2);
	cout << tmp << "\n";
	sum += stoi(tmp) * 60;
	tmp = s.substr(3, 2);
	sum += stoi(tmp);
	cout << "sum : " << sum << "\n";
	return sum;
}

int calc(vector<int> fees, int time) {
	if (time <= fees[0]) {
		return fees[1];
	}
	time -= fees[0];
	int total = fees[1];
	total += (time / fees[2]) * fees[3];
	if (time % fees[2] != 0) {
		total += fees[3];
	}
	return total;
}

vector<int> solution(vector<int> fees, vector<string> records) {
	vector<int> answer;
	map<string, vector<int>> car;
	map<string, vector<int>> time;
	for (int i = 0; i < records.size(); i++) {
		vector<string> tmp = split(records[i], ' ');
		car[tmp[1]].push_back(getTime(tmp[0]));
	}

	for (auto c : car) {
		if (c.second.size() % 2 != 0) {
			c.second.push_back(getTime("23:59"));
		}
		for (int i = 1; i < c.second.size(); i+=2) {
			int rest = c.second[i] - c.second[i - 1];
			cout <<"rest : " << rest << "\n";
			time[c.first].push_back(rest);
		}
	}

	for (auto t : time) {
		int totalTime = 0;
		for (int i = 0; i < t.second.size(); i++) {
			totalTime += t.second[i];
		}
		answer.push_back(calc(fees, totalTime));
	}
	return answer;
}

int main(void) {
	vector<int> fees = { 180, 5000, 10, 600 };
	vector<string> records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
	vector<int> result = solution(fees, records);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";;
	}
	return 0;
}