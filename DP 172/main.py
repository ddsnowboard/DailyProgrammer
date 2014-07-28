from urllib import request

f = list(request.urlopen('https://gist.githubusercontent.com/anonymous/0ce707518d9e581499f5/raw/1c01ded3d29a9838ea48a5a878cb746f49ef61cf/font.txt'))
for i, j in enumerate(f):
	f[i] = j.decode('UTF-8').replace(' ','')
letters = {}
for i in range(26):
	letters[f[8*i].replace('\n', '')] = f[(8*i)+1:8*(i+1)]
letters[' '] = []
for i in range(7):
	letters[' '].append('0 0 0 0 0')
for i, j in letters.items():
	for k, p in enumerate(j):
		j[k] = p.replace(" ", '').replace('\n','').replace('0', ' ')
inp = input("What do you want to write? ")
with open('output.pbm', 'w') as o:
	o.write("P1 \n{0} {1}\n".format(len(inp)*5, 6))
	for i in range(7):
		for j in inp:
			o.write(letters[j.upper()][i])
			o.write('  ')
		o.write('\n')