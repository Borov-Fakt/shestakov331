#include <iostream>
#include <string>
using namespace std;


int main()
{
    setlocale(LC_ALL, "Russian");
    string str = "sdkksdsdrrrsf3qq243a46ww32hgggdfjwess3633333zzzxxx7733jsssssss3eeeettyfcgvhulhhj998";
    char abs;
    int count = 0;

    cout << "������ ������ ��� ������: " << endl;
    cin >> abs;

    for (char i : str) {

        if (i == abs) {
            count++;
        }
    }

    cout << "������ = " << str << endl;

    if (count > 0) {
        cout << "������ �����������: " << count << endl;
    }
    else
    {
        cout << "������ �� �����������" << endl;
    }

    return 0;
}
