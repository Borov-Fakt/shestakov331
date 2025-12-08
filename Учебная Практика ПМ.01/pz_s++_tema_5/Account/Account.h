#include <iostream>
#include <string>
using namespace std;

class Account {
public:
	string name;
	double remainder;
	double expense;
	double income;

	Account(string names, double remainders);
	void print();
	Account operator++();
	Account operator--();
	void operator+(double value);

};