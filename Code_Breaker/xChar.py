import random
import sys
fileName = sys.argv[1]
inp=open(fileName,'r')
prev = list(inp.read())
rand = random.randint(0,len(prev)-1)
rand2 = random.randint(0,len(prev)-1)
prev[rand],prev[rand2] = prev[rand2],prev[rand]
prev = "".join(prev)
if(fileName.endswith(".py")):
    out=open('pythonOut.py', 'w')
    out.write(prev)
    out.close()
if(fileName.endswith(".java")):
    out=open('IntMan.java', 'w')
    out.write(prev)
    out.close()
