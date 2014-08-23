import collections
import tkinter as tk


with open("windfarm.dat", 'r') as f:
	file = [l.split(' ') for l in f]
	
table = collections.defaultdict(lambda: collections.defaultdict(int))

for i in file:
	table[i[0]][i[1]] += int(i[2])
	
# This whole bit down here is just for output. You can ignore it.  
window = tk.Tk()
left_frame = tk.Frame(window)
label_list = tk.Listbox(left_frame)
tk.Label(left_frame, text='Windmill #').pack()
for i in range(1000, 1010):
	label_list.insert('end', str(i))
label_list.pack()
left_frame.pack(side='left')
days = []
lists = []
for i in ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']:
	days.append(tk.Frame(window))
	tk.Label(days[-1], text=i).pack()
	lists.append(tk.Listbox(days[-1]))
	for j in range(1000, 1010):
		lists[-1].insert('end', table[str(j)][i])
	lists[-1].pack()
	days[-1].pack(side='left')
with open('output.txt', 'w') as o:
	o.write("#      Mon      Tue      Wed      Thu      Fri      Sat      Sun\n")
	for i in range(1000, 1010):
		o.write("{}      {}      {}      {}      {}      {}      {}      {}\n" .format(*tuple([i] + [table[str(i)][j] for j in ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']])))
window.mainloop()