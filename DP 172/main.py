from urllib import request

f = list(request.urlopen('https://gist.githubusercontent.com/anonymous/0ce707518d9e581499f5/raw/1c01ded3d29a9838ea48a5a878cb746f49ef61cf/font.txt'))
for i, j in enumerate(f):
	f[i] = j.decode('UTF-8').replace(' ','')
letters = {}
for i in range(26):
	letters[f[8*i].replace('\n', '')] = ''.join(f[(8*i)+1:8*(i+1)]).replace(' ', '')

inp = input("What do you want to write? ")
with open('output.pbm', 'w') as o:
	o.write("P1 \n%d 6" % len(inp) * 5)
	for i in inp:
		o.write(letters[i.upper()])