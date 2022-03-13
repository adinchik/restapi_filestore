#include <iostream>

using namespace std;

int n, a[600][600];

int main()
{
    freopen("a.out", "w", stdout);
    cin >> n;
    int i = n - 1, j = 0;
    int k = 1;
    while (k <= n * n) {
        while (j < n && a[i][j] == 0) {
            a[i][j] = k; k++;
            j++;
        }
        j--; i--;
        while (i >= 0 && a[i][j] == 0) {
            a[i][j] = k; k++;
            i--;
        }
        i++; j--;
        while (j >= 0 && a[i][j] == 0) {
            a[i][j] = k; k++;
            j--;
        }
        j++; i++;
        while (i < n && a[i][j] == 0) {
            a[i][j] = k; k++;
            i++;
        }
        i--; j++;
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            cout << a[i][j] << ' ';
        cout << endl;
    }
}


