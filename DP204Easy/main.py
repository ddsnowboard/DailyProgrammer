import re
class Line:
	def __init__(self, string, scene, speaker):
		self.string = string
		self.scene = scene
		self.speaker = speaker
	def __str__(self):
		return self.string
	def search(self, string):
		if string in self.string:
			return True
	def info(self):
		return """ACT {}
SCENE {}
Characters in scene: {}
Spoken by {}""".format(self.scene.act, self.scene.number, ", ".join(self.scene.characters), self.speaker)
class Scene:
	def __init__(self, number, act):
		if not act:
			raise Exception("The act is blank! The number is {}".format(number))
		self.number = number
		self.act = act
		self.characters = []
	def addCharacter(self, character):
		cleaned = character
		if cleaned not in self.characters and cleaned != "ALL":
			self.characters.append(cleaned)
line = input("What do you wish to search for? ")
with open('input.txt') as f:
	lines = []
	scenes = []
	currscene = None
	act = None
	for i in f:
		if i == "":
			continue
		elif re.match(r"^    .+?$", i):
			lines.append(Line(i, currscene, speaker))
		elif re.match(r"^ACT (?P<actnumber>[IVX]+)", i):
			act = re.match(r"ACT (?P<actnumber>[IVX]+)", i).group('actnumber')
		elif re.match(r"^SCENE (?P<scene>[IVX]+)", i):
			currscene = Scene(re.match(r"SCENE (?P<scene>[IVX]+)", i).group('scene'), act)
		elif re.match(r"^  (?P<character>[A-Z ]+)[.]$",i):
			speaker = re.match(r"^  (?P<character>[A-Z ]+)[.]$",i).group('character')
			currscene.addCharacter(re.match(r"^  (?P<character>[A-Z ]+)[.]$",i).group('character'))
	for i, j in enumerate(lines):
		if j.search(line):
			print(j.info())
			print("".join([str(lines[p]) for p in range(i-2, i+6)]))
			break
	else:
		print("I couldn't find that line")