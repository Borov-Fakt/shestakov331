#include <iostream>
#include <string>
using namespace std;


int main()
{
    setlocale(LC_ALL, "Russian");
    string str = "sdkksdsdrrrsf3qq243a46ww32hgggdfjwess3633333zzzxxx7733jsssssss3eeeettyfcgvhulhhj998";
    char abs1;
    char abs2;
    bool count = false;

    cout << "������ = " << str << endl;
    cout << "������ ������ ��� ������: " << endl;
    cin >> abs1;
    cout << "������� ������ ��� ������: ";
    cin >> abs2;

    for (int i = 0; i < str.length(); ++i) {
        if (str[i] == abs1) {
            str[i] = abs2;
            count = true;
        }
    }

    cout << "������ = " << str << endl;

    if (count) {
        cout << "������ �������." << endl;
        cout << "������ = " << str << endl;
    }
    else
    {
        cout << "������ �� ������" << endl;
    }

    return 0;
}