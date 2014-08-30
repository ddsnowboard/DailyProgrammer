import random
many = int(input("How many?\n"))
with open('input.txt', 'w') as f:
    f.write('%d\n' % many)
    for i in range(many):
        f.write(str(random.randint(0,1000000))+'\n')