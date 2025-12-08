#include <iostream>
#include <string>
using namespace std;

class Bank {
private:
	double bankPercent;
public:
	Bank();
	virtual ~Bank() = default;
	Bank(double precent);
	void setupBank(double percent);
	double getBankPercent();
	virtual void GetPercent(double sum);

};

class MicroBank : public Bank {
private:
	double microPercent;

public:
	MicroBank(double miniPersent, double pasePersent);
	void GetPercent(double sum);
};
