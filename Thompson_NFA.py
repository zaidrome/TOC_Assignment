import re

postfix = []
tempo = []
opertr = -10
operd = -20
lparentheses = -30
rparentheses = -40
empty = -50
 
def typeof(val):
	if val is '(':
		return lparentheses
	elif val is ')':
		return rparentheses
	elif val is '+' or val is '*' or val is '|':
		return opertr
	elif val is ' ':
		return empty    
	else :
		return operd 

def precede(val):
	if val is '(':
		return 0
	elif val is '+' or '|':
		return 1
	elif val is '*':
		return 2
	else:
		return 99
 
infix = input("Please enter the infix notation : ")
for i in infix :
	typ = typeof(i)
	if typ is lparentheses :
		tempo.append(i)
	elif typ is rparentheses :
		nxt = tempo.pop()
		while nxt is not '(':
			postfix.append(nxt)
			nxt = tempo.pop()
	elif typ is operd:
		postfix.append(i)
	elif typ is opertr:
		pr = precede(i)
		while len(tempo) is not 0 and pr <= precede(tempo[-1]) :
			postfix.append(tempo.pop())
		tempo.append(i)
	elif typ is empty:
		continue
				 
while len(tempo) > 0 :
	postfix.append(tempo.pop())
	 
print("The postfix notation of the infix notation is ",''.join(postfix))



