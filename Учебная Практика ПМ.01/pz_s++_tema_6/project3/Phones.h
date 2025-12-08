#include <iostream>
#include <string>
using namespace std;

class IPhone {
private:
	double diagonal;
	double cpu;

public:
	IPhone(double diagonal, double cpu);
	string ShowIphone();
};

class Xiaomi {
private:
	double diagonal;
	double cpu;

public:
	Xiaomi(double diagonal, double cpu);
	string ShowXiaomi();
};
