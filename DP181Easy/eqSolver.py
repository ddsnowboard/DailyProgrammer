import re
class Equation:
	def __init__(self, eq):
		this.eq = re.subn(r"^y=|=y$", '', eq)[0]
		this.eq = this.eq.replace('^', '**')
		this.coefficients = defaultdict(float)
		# Do something to this line, maybe a list comprehension using an outputted match object or something, 
		# that will append the sign onto the term with it, so I can keep those. Because those are good. 
		this.terms = re.compile(r"\+|-").split(this.eq)
		# Use re.search() with "\+|-\d+[A-Za-z]\+|-", then use the returned match object things to split up the 
		# string, including the first character, because that'll be the sign, and that's good. But you'll have to
		# change the next few lines, ~14-20, to include the signs. But that's fine. Make sure you don't include 
		# the next sign though, that's no good. 
		for i in terms:
			if not re.compile(r"[A-Za-z]").search(i):
				this.coefficients[0] += float(i)
			elif re.compile(r"\d?[A-Za-z][+-$]").search(i):
				this.terms[1]+=float(re.compile(r"[A-Za-z]|\+|-").subn('',i)[0])
			elif re.compile(r"-{1}\d+[A-Za-z]\*\*\d+").match(i):
				this.coefficients[i[i.index("**")+2:]] += float(i[:re.compile("[A-Za-z]").search(i).span[1]])
	def evaluate(x):
		end = 0
		for i, j in this.coefficients:
			end+=j*x**i
		return end