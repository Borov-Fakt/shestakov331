#include <iostream>
#include "Account.h"
#include <string>
using namespace std;

const int maxAccount = 10;

class BankSystem {
private:
	double totalReg;
	Account accounts[maxAccount];
	int correntAccount;

public:
	BankSystem();

	void createAccount(string aName, double inRemainder);
	void processIncome(int index, double amount);
	void processExpenses(int index, double amount);

	double getTotal();
	void printAccountDetails();
};
