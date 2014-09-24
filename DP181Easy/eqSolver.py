import re
from collections import defaultdict
class Equation:
	def __init__(self, eq):					# y=2x^2-3x+5
		self.coefficients = defaultdict(float)
		self.eq = re.subn(r"^y=|=y$", '', eq)[0]			# 2x^2-3x+5
		self.eq = self.eq.replace('^', '**').replace("+", " +").replace("-", ' -')  # 2x**2 -3x +5
		# Do something to self line, maybe a list comprehension using an outputted match object or something, 
		# that will append the sign onto the term with it, so I can keep those. Because those are good. 
		self.terms = self.eq.split(" ")			# "2x**2", "-3x", "+5"
		# Use re.search() with "[\+-]\d+[A-Za-z]", then use the returned match object things to split up the 
		# string, including the first character, because that'll be the sign, and that's good. But you'll have to
		# change the next few lines, ~14-20, to include the signs. But that's fine. Make sure you don't include 
		# the next sign though, that's no good. 
		self.terms = [i for i in self.terms if i != ''] # Just to make sure there is nothing problematic in it. 
		for i in self.terms:
			if not re.compile(r"[A-Za-z]").search(i):
				self.coefficients[0] += float(i)  # "+5"
			elif re.compile(r"[\+-]?[\d\.]+[A-Za-z]").search(i):
				self.coefficients[1]+=float(re.compile(r"[A-Za-z]").subn('',i)[0])  	#"-3" 
			elif re.compile(r"[\+-]?[\d\.]+[A-Za-z]\*\*\d+").match(i):
				self.coefficients[i[i.index("**")+2:]] += float(i[:re.compile("[A-Za-z]").search(i).span[1]]) # '2'
	def evaluate(self, x):
		end = 0
		for i, j in self.coefficients.items():
			end+=j*x**i
		return end