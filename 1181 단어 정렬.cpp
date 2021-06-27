#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

bool compare(string a, string b)
{
	if (a.length() == b.length())
	{
		return a < b;
	}
	else
	{
		return a.length() < b.length();
	}
}

int main(void) {
	int N;
	cin >> N;
	vector<string> v;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		v.push_back(s);
	}
	sort(v.begin(), v.end(), compare);
	cout << v[0] << "\n";
	for (int i = 1; i < N; i++)
	{
		if (v[i - 1] == v[i])
		{
			continue;
		}
		cout << v[i] << "\n";
	}
	return 0;
}