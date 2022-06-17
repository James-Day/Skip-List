////
// Author:       James Day	
// Section:    A
// Assignment:   Skiplist
// Description:   This program creates a skiplist data structure which is 
//				  A way of storing data. In this case we stored computerscientists
//				  and their ID numbers. This data structre creats an efficient route 
//				  for searching and finding data.
//
//			Problems: No problems to my knowledge.
////
#include "ComputerScientist.h"
#include <string>
#include <fstream>
#include <vector>
#include <algorithm>
#include <random>
#include <string>
#include <sstream>
#include <iostream>
#include "skipList.h"

using namespace std;
vector<string> split(const string& s, char delim) {
	stringstream ss(s);
	string item;
	vector<string> tokens;
	while (getline(ss, item, delim)) {
		tokens.push_back(item);
	}
	return tokens;
}

vector<ComputerScientist*> load(const string& filename) {
	vector<ComputerScientist*> list;
	std::ifstream file(filename);

	std::string str;
	while (std::getline(file, str)) {
		vector<string> tokens = split(str, ',');

		list.push_back(new ComputerScientist(tokens[1], tokens[2], tokens[3], stoi(tokens[0])));
	}

	auto rng = default_random_engine{};
	//I wonder why I need this?
	std::shuffle(std::begin(list), std::end(list), rng);
	return list;
}
int main() {
	skipList<int, ComputerScientist*> list2;
	vector<ComputerScientist*> list = load("csList.txt");
	for (int i = 0; i < list.size(); i++) {
		list2.insert(list[i], list[i]->getID());
	}
	list2.displayList(std::cout);
	cout << "After Deletions: " << endl;
	for (int i = 0; i < list.size();i++) {
		if (list[i]->getID() % 10 == 0) {
			list2.remove(list[i]->getID());
		}
	}
	list2.displayList(std::cout);

	cout << endl;
	system("pause");
}