#include <iostream>
#include <fstream>
#include <Windows.h>
#include "File.h"
using namespace std;

int main() {
    SetConsoleCP(1251);
    SetConsoleOutputCP(1251);
    setlocale(LC_ALL, "Russian");

    char fio[100];

    cout << "Введите ФИО: ";
    cin.getline(fio, 100);

    writeFile1(fio);   
    copyFile1inFile2(); 
    showFiles();   

    return 0;
}