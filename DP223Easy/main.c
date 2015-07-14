#include <string.h>
#include <stdio.h>
int garland(char*);

int main(int argc, char** argv)
{
    printf("%d\n", garland(""));
    printf("%d\n", garland("test"));
    printf("%d\n", garland("ceramic"));
    printf("%d\n", garland("alfalfa"));
    return 0;
}

int garland(char* word)
{
    size_t length = strlen(word);
    printf("%s\n", word);
    if(length == 0)
    {
        return 0;
    }
    int out = length + 1;
    int i;
    int breaking = 0;
    // I'm trying to use these loops to walk through the string together. Unfortunately, that's
    // not going too well for various reasons. Anyway, here's the link. 
    // https://www.reddit.com/r/dailyprogrammer/comments/3d4fwj/20150713_challenge_223_easy_garland_words/
    for(i = length - 1; i > 0;i--)
    {
        --out;
        int l;
        for(l = 0;l<length; l++)
        {
            printf("The front letter is %c and the back letter is %c\n", word[l], word[i + l]);
            printf("i is %d and l is %d\n", i, l);
            if(word[l] != word[i + l])
            {
                break;
                breaking = 1;
            }
        }
        if(breaking == 1)
        {
            continue;
            breaking = 0;
        }
    }
    return out;
}
