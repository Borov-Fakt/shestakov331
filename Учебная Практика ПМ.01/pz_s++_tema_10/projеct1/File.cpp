#include <iostream>
#include <fstream>
#include "File.h"

using namespace std;

void writeFile1(char fio[]) {
    ofstream file1;
    file1.open("file1.txt");
    file1 << fio;
    file1.close();
}


void copyFile1inFile2() {
    ifstream inFile;
    inFile.open("file1.txt");

    ofstream outFile;
    outFile.open("file2.txt", ios::app);

    char line[200];

    while (inFile.getline(line, 200)) {
        outFile << line << endl;
    }

    inFile.close();
    outFile.close();
}

void showFiles() {
    ifstream f1("file1.txt");
    ifstream f2("file2.txt");

    char line[200];

    cout << "\nСодержимое файла 1:\n";
    while (f1.getline(line, 200)) {
        cout << line << endl;
    }

    cout << "\nСодержимое файла 2:\n";
    while (f2.getline(line, 200)) {
        cout << line << endl;
    }

    f1.close();
    f2.close();
}