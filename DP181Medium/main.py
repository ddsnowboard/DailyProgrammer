import re
from collections import defaultdict
import datetime as date
class Car:
	def __init__(self, factor):
		self.speeds = []
		self.factor = factor
	def add(self, distance, hour):
		self.speeds.append([distance, date.datetime.strptime(hour, "%H:%M:%S")])
	def averages(self):
		avgs = []
		for i in range(len(self.speeds)-1):
			avgs.append(((self.speeds[i+1][0]-self.speeds[i][0])/(self.speeds[i+1][1]-self.speeds[i][1]).total_seconds())*self.factor)
		return avgs
with open("input.txt", 'r') as f:
	speed_limit = 0
	conversion_factor = 0
	unit = ''
	current_distance = 0
	cars = defaultdict(lambda: Car(conversion_factor))
	cameras = {}
	for i in f:
		try:
			if re.search(r"\ASpeed limit", i):
				speed_limit = float(re.compile(r"\d+\.\d+").search(i).group())
				if re.search(r"km[/]h", i):
					conversion_factor = 3.6
					unit = r'km/h'
				else:
					conversion_factor = 2.237
					unit = 'mph'
			elif re.search(r"\ASpeed camera", i):
				cameras[int(re.search(r"number (?P<number>\d+) is", i).group('number'))] = int(re.search(r"is (?P<distance>\d+) metres down", i).group("distance"))
			elif re.search("Start of log", i):
				current_distance = cameras[int(re.search(r"camera (?P<number>\d+)\.", i).group("number"))]
			elif re.search("Vehicle", i):
				cars[re.search(r"Vehicle (?P<plate>.+? .+?) passed", i).group('plate')].add(current_distance, re.search(r"(?P<time>[0-9]{2}:[0-9]{2}:[0-9]{2})[.]$", i).group('time'))
		except:
			print(i)
			raise
	for i, j in cars.items():
		for p in j.averages():
			if p > speed_limit:
				print("Vehicle {0} broke the speed limit by {1} {2}".format(i, round(p-speed_limit, 1), unit))
	input()