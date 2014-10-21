with open('WordList.txt', 'r') as f:
	handles = [i.replace("at", "@") for i in f if "at" in i]
handles.sort(key = len)
with open('output.txt', 'w') as w:
	w.write("Here are the 10 longest ones:\n")
	for i in handles[-10:]:
		w.write(i)
	w.write("And her are the 10 shortest\n")
	for i in handles[:10]:
		w.write(i)