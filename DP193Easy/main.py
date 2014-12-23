acronyms = {'imo': 'in my opinion', 'gl': 'good luck', 'gg': 'good game', 'wp': 'well played', 'brb': 'be right back', 'g2g': 'got to go', 'hf': 'have fun', 'dw': "don't worry", 'wtf': 'what the fuck', 'lol': 'laugh out loud'}
string = input()
for i, j in acronyms.items():
	if i+' ' in string:
		string = string.replace(i+' ', j+' ')
	elif ' '+i in string:
		string = string.replace(' '+i, ' '+j)
print(string)