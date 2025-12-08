#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {
    setlocale(LC_ALL, "Russian");

    string num;
    cout << "Введите число: ";
    cin >> num;

    ofstream outFile;
    outFile.open("primer1.txt");
    outFile << num;
    outFile.close();

    ifstream inFile;
    inFile.open("primer1.txt");

    double readNum;
    inFile >> readNum;

    if (inFile.fail()) {
        cout << "В файле строка. Необходимо число" << endl;
        inFile.close();
        return 0;
    }

    double result = readNum * 0.5;

    ofstream outFile2;
    outFile2.open("primer2.txt");
    outFile2 << result;
    outFile2.close();

    cout << "50% от числа сохранено в primer2.txt" << endl;

    inFile.close();
    return 0;
}