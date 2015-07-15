#include <string.h>
#include <stdio.h>
int garland(char*);
int walkThrough(char*, int);

// Run through wordlist.txt or whatever with this and see how fast it is. 
// Also, find out how to do file input in C. 
int main(int argc, char** argv)
{
    printf("%d\n", garland("programmer"));
    printf("%d\n", garland("ceramic"));
    printf("%d\n", garland("onion"));
    printf("%d\n", garland("alfalfa"));
    return 0;
}

int garland(char* word)
{
    size_t length = strlen(word);
    if(length == 0)
    {
        return 0;
    }
    int offset = length - 1;
    int out = 0;
    int currentResult;
    for(;offset >= 1; offset--)
    {
        currentResult = walkThrough(word, offset);
        if(currentResult)
        {
            out = length - offset;
        }
    }
    return out;
}
int walkThrough(char* word, int offset)
{
    int i;
    int out = 1;
    size_t length = strlen(word);
    for(i = 0;i + offset < length;i++)
    {
        out = out & (word[i] == word[i + offset]);
    }
    return out; 
}
