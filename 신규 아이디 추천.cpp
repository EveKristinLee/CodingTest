#include <string>
#include <vector>
#include <iostream>

using namespace std;

string first(string id) {
	for (int i = 0; i < id.length(); i++) {
		if (isalpha(id[i])) {
			if (isupper(id[i])) {
				id[i] = tolower(id[i]);
			}
		}
	}
	return id;
}

string second(string id) {
	for (int i = 0; i < id.length();) {
		if (isalpha(id[i]) || isdigit(id[i])
			|| id[i] == '-' || id[i] == '_' || id[i] == '.')  {
			i++;
			continue;
		}
		else {
			//erase로 해당 위치가 사라졌으니까 i++하면 안됨
			id.erase(id.begin() + i);
		}
	}
	return id;
}
string third(string id) {
	for (int i = 1; i < id.length();) {
		if (id[i] == '.' && id[i - 1] == '.') {
			id.erase(id.begin() + i);
			continue;
		}
		else {
			i++;
			continue;
		}
	}
	return id;
}
string fourth(string id) {
	if (id.front() == '.') {
		id.erase(id.begin());
	}
	else if (id.back() == '.') {
		id.erase(id.end() - 1);
	}
	return id;
}
string fifth(string id) {
	if (id.empty()) {
		id = "a";
	}
	return id;
}
string sixth(string id) {
	if (id.length() >= 16) {
		id.erase(id.begin() + 15, id.end());
	}
	if(id.back() == '.') {
		id.erase(id.end() - 1);
	}
	return id;
}
string seventh(string id) {
	while (id.length() <= 2) {
		id.insert(id.end()-1, id.back());
	}
	return id;
}

string solution(string new_id) {
	string answer = "";
	answer = first(new_id);
	answer = second(answer);
	answer = third(answer);
	answer = fourth(answer);
	answer = fifth(answer);
	answer = sixth(answer);
	answer = seventh(answer);
	return answer;
}

int main(void) {
	string ans = "";
	ans = solution("=.=");
	cout << ans << "\n";
	return 0;
}