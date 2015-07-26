#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char** argv)
{
    srand(time(NULL));
    char* numbers[argc - 1];
    for(int i = 0; i < argc - 1; i++)
    {
        numbers[i] = argv[i + 1];
    }
    char* output[argc - 1];
    int currentRandom;
    for(int i = 0; i < argc - 1; i++)
    {
        do{
            currentRandom = rand() % (argc - 1);
            output[i] = numbers[currentRandom];
        }while(strcmp("-1", numbers[currentRandom]) == 0);
        numbers[currentRandom] = "-1";
    }
    for(int i = 0; i < argc - 1; i++)
    {
        printf("%s ", output[i]);
    }
    printf("\n");
}
