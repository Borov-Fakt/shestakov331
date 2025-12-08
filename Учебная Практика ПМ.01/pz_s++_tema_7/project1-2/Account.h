#include <iostream>
#include <string>
using namespace std;

class Account {
private:
	string name;
	double remainder;
	double expenses;
	double income;

public:
	Account();

	Account(string& aName, double inRemainder);

	bool addExpenses(double amount);
	bool addIncome(double amount);

	double getBalance();
	void printDetails();
};
