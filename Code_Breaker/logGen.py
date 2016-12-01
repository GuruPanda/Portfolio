import os
with open('line.txt', 'r') as c:
    words = [line.split(None, 1)[0] for line in c]
begin = (words[0])

with open('len.txt', 'r') as a:
    wordlist = [line.split(None, 1)[0] for line in a]
error = (wordlist[0])

with open('inLine.txt', 'r') as b:
    incount = [line.split(None, 1)[0] for line in b]
inc = (incount[0])

with open('outLine.txt', 'r') as b:        
        outcount = [line.split(None, 1)[0] for line in b]
if len(outcount)==0:
    diff = 0
else:
    out = (outcount[0])
    if int(out) > int(inc):
        diff = int(out)-int(inc)
    else:
        diff = 0
        
print ("{},{},{}".format(begin,error,diff))
                                    
