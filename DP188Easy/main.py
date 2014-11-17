import re
def writeFormatted(match):
	# This, ladies and gentlemen, is the depth of my laziness. 
	months = {i[1]:i[0]+1 for i in enumerate("Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(' '))}
	if len(match.group('year')) == 2:
		if int(match.group('year'))>=50:
			year = 1900+int(match.group('year'))
		else:
			year = 2000+int(match.group('year'))
	else:
		year = match.group('year')
	if re.match(r'[A-Za-z]{3}', match.group("month")):
		month = months[match.group("month")]
	else:
		month = match.group('month')
	return "{0}-{1:02d}-{2:02d}\n".format(int(year), int(month), int(match.group("day")))
	
regexes = [ re.compile(r'(?P<year>[0-9]{4})[-](?P<month>[0-9]{2})[-](?P<day>[0-9]{2})'), 
			re.compile(r'(?P<month>[0-9]{2})[/](?P<day>[0-9]{2})[/](?P<year>[0-9]{2})'),
			re.compile(r'(?P<month>[0-9]{2})#(?P<year>[0-9]{2})#(?P<day>[0-9]{2})'),
			re.compile(r'(?P<day>[0-9]{2})[*](?P<month>[0-9]{2})[*](?P<year>[0-9]{4})'),
			re.compile(r'(?P<month>[A-Za-z]{3}) (?P<day>[0-9]{2}), (?P<year>[0-9]{4})'),
			re.compile(r'(?P<month>[A-Za-z]{3}) (?P<day>[0-9]{2}), (?P<year>[0-9]{2})')]
with open('input.txt', 'r') as i:
	with open('output.txt', 'w') as o:
		for l in i:
			for regex in regexes:
				if regex.match(l):
					o.write(writeFormatted(regex.match(l)))
					break