def convertUnits(arr):
	# Here are the possible units, with their equivalence in meters or kilograms.
	possible_conversions = [{'inches': 0.0254, 'miles': 1609.34, 'meters': 1, 'attoparsecs': 0.0308567758, 'metres': 1, 'inch': 0.0254, 'mile': 1609.34, 
							 'meter': 1, 'attoparsec': 0.0308567758, 'metre': 1, 'foot': 0.3048, 'feet': 0.3048}, 
							{'kilograms':1, 'pounds': 0.453592, 'ounces': 0.0283495, 'hogsheads of Berylliumm': 440.7, 'kilogram':1, 'pound': 0.453592,
							'ounce': 0.0283495, 'hogshead of Berylliumm': 440.7}]
	in_num = int(arr.split(' to ')[0].split(' ')[0])
	in_unit = arr.split(' to ')[0].split(' ')[1]
	if in_unit == 'hogsheads': # Hogsheads of Berylliumm screws it up, so I took care of that. 
		in_unit = 'hogsheads of Berylliumm'
	out_unit = arr.split(' to ')[1] 
	in_standard = 0 # The input in its standard unit, meters or kg's.
	for i in possible_conversions:
		if sorted(i.keys()).count(in_unit) != 0:
			if sorted(i.keys()).count(out_unit) != 0:
				in_standard = in_num * i[in_unit]
				return in_standard/i[out_unit]
	raise Exception("You didn't put the input in the right format!")
while True:
	request = input("Input your desired conversion: ")
	words = request.split(' to ')
	try:
		print(' is {0} '.join(words).format(convertUnits(request)))
	except:
		raise
		break