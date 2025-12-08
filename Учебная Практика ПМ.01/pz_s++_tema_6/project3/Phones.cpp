#include <iostream>
#include <string>
#include "Phones.h"
using namespace std;

IPhone::IPhone(double diago, double cpuS)
{
	diagonal = diago;
	cpu = cpuS;
}

string IPhone::ShowIphone()
{
	cout << diagonal << "  " << cpu << endl;
	return " ";
}

Xiaomi::Xiaomi(double diago, double cpuS)
{
	diagonal = diago;
	cpu = cpuS;
}

string Xiaomi::ShowXiaomi()
{
	cout << diagonal << "  " << cpu << endl;
	return " ";
}
