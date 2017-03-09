//Made by: Avi Sundriyal (1410110083), Aviral Soni (1410110085), Ayush Arora(1410110087)

#include<stdio.h>
#include<string.h>
#define NO_OF_CHARS 256
#define MAX_LEN 256
 
int getNextState(char *pattern, int mn, int sta, int xy)
{
 
  if (sta < mn && xy == pattern[sta])
  return sta+1;
 
  int ns, i; //  storing the result of next state

  for (ns = sta; ns > 0; ns--)
  {
  if(pattern[ns-1] == xy)
  {
  for(i = 0; i < ns-1; i++)
  {
  if (pattern[i] != pattern[sta-ns+1+i])
  break;
  }
  if (i == ns-1)
  return ns;
  }
  }
 
  return 0;
}
 
/* Building the TF table, representing Finite Automata for a given pattern */
void computeTF(char *pattern, int mn, int TF[][NO_OF_CHARS])
{
  int sta, xy;
  for (sta = 0; sta<= mn; ++sta)
  for (xy = 0; xy< NO_OF_CHARS; ++xy)
  TF[sta][xy] = getNextState(pattern, mn, sta, xy);
}
 
/* Prints all occurrences of pattern in the text */
void search(char *pattern, char *text)
{
  int mn = strlen(pattern);
  int N = strlen(text);
 
  int TF[mn+1][NO_OF_CHARS];
 
  computeTF(pattern, mn, TF);
 
  // Processing  text over FiniteAutomata.
  int i, sta=0;
  for (i = 0; i < N; i++)
  {
  sta = TF[sta][text[i]];
  if (sta == mn)
  {
  printf ("Pattern found at index %d\n", i-mn+1);
  }
  }
}
 

int main()
{
  char *text=(char *)malloc(sizeof(char)*NO_OF_CHARS);
  char *pattern=(char *)malloc(sizeof(char)*NO_OF_CHARS);

  printf("Please enter a text with different patterns\n");
  scanf("%s",text);
//printf("Text is %s",text);
  printf("Please enter a pattern you wish to search for?\n");
  scanf("%s",pattern);
//printf("%s pattern\n",pattern);
  search(pattern, text);
  return 0;
}


