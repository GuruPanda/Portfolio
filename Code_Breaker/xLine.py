import random
import sys
fileName = sys.argv[1]
inp=open(fileName,'r')
prev = inp.readlines()
rand = random.randint(0,len(prev)-1)
rand2 = random.randint(0,len(prev)-1)
x=prev[rand]
prev[rand]=prev[rand2]
prev[rand2]=x
if(fileName.endswith(".py")):
    out=open('pythonOut.py', 'w')
    out.writelines(prev)
    out.close()
if(fileName.endswith(".java")):
    out=open('IntMan.java', 'w')
    out.writelines(prev)
    out.close()
                                
