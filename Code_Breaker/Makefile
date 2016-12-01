CLASSPATH = jUnit4.jar:hamCrest.jar:.

linP:
	python3 xLine.py pythonIn.py;\

charP:
	python3 xChar.py pythonIn.py;\

varP:
	python3 xVar.py pythonIn.py;\

pTest:
	cat pythonIn.py pythonTest.py > testerFile.py;\
        python3 testerFile.py 2> okTest.txt;\

linPTest:
	python3 xLine.py pythonIn.py
	cat pythonOut.py pythonTest.py > testerFile.py
	python3 testerFile.py 2> badTest.txt || exit 0

charPTest:
	python3 xChar.py pythonIn.py
	cat pythonOut.py pythonTest.py > testerFile.py;\
	python3 testerFile.py 2> badTest.txt || exit 0

varPTest:
	cat pythonIn.py pythonTest.py > testerFile.py;\
	python3 xVar.py testerFile.py 2> badTest.txt || exit 0

linPLog:
	make linPTest;\
	python3 pythonOut.py 2> outErr.txt;\
	make pTest;\
	echo Python,LineSwap > line.txt;\
	wc -l outErr.txt > len.txt;\
	wc -l badTest.txt > outLine.txt;\
	wc -l okTest.txt > inLine.txt;\
	python3 logGen.py >> testLog.csv;\
	rm *txt; rm pythonOut.py; rm testerFile.py;\

charPLog:
	make charPTest;\
	python pythonOut.py 2> outErr.txt;\
	make pTest;\
        echo Python,CharSwap > line.txt;\
        wc -l outErr.txt > len.txt;\
        wc -l badTest.txt > outLine.txt;\
        wc -l okTest.txt > inLine.txt;\
        python3 logGen.py >> testLog.csv
	rm *txt; rm pythonOut.py; rm testerFile.py;\

varPLog:
	make varPTest;\
	python3 xVar.py pythonIn.py 2> outErr.txt;\
	make pTest;\
        echo Python,VarSwap > line.txt;\
        wc -l outErr.txt > len.txt;\
	wc -l badTest.txt > outLine.txt;\
        wc -l okTest.txt > inLine.txt;\
        python3 logGen.py >> testLog.csv
	rm *txt; rm pythonOut.py; rm testerFile.py;\

linJ:
	python3 xLine.py j_fact/IntMan.java;\

charJ:
	python3 xChar.py j_fact/IntMan.java;\

jTest:
	cp j_fact/IntMan.java .;\
        cat JavaTest.java IntMan.java > IntManTester.java;\
        javac -cp $(CLASSPATH) IntManTester.java;\
        java -cp $(CLASSPATH) org.junit.runner.JUnitCore IntManTester > okTe\
st.txt;\


linJTest:
	make linJ;\
	javac IntMan.java 2> outErr.txt || exit 1;\
	cat JavaTest.java IntMan.java > IntManTester.java;\
	javac -cp $(CLASSPATH) IntManTester.java;\
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore IntManTester > badTest.txt;\

charJTest:
	make charJ;\
	javac IntMan.java 2> outErr.txt || exit 1;\
        cat JavaTest.java IntMan.java > IntManTester.java;\
	javac -cp $(CLASSPATH) IntManTester.java;\
        java -cp $(CLASSPATH) org.junit.runner.JUnitCore IntManTester > badTest.txt;\

varJTest:
	javac IntMan.java 2> outErr.txt || exit 1;\
	cat JavaTest.java IntMan.java > IntManTester.java;\
        javac -cp $(CLASSPATH) IntManTester.java;\
        java -cp $(CLASSPATH) org.junit.runner.JUnitCore IntManTester > badTest.txt;\

javaComp:
	javac IntMan.java 2> outErr.txt;\

linJLog:
	make linJTest;\
	rm IntManTester.class;\
	make jTest;\
	echo Java,LineSwap > line.txt;\
        wc -l outErr.txt > len.txt;\
	wc -l badTest.txt > outLine.txt;\
        wc -l okTest.txt > inLine.txt;\
        python3 logGen.py >> testLog.csv;\
	rm *.txt; rm IntMan*;\

charJLog:
	make charJTest;\
	rm IntManTester.class;\
	make jTest;\
	echo Java,CharSwap > line.txt;\
        wc -l outErr.txt > len.txt;\
	wc -l badTest.txt > outLine.txt;\
        wc -l okTest.txt > inLine.txt;\
        python3 logGen.py >> testLog.csv;\
	rm *.txt; rm IntMan*;\

varJLog:
	make varJTest;\
	rm IntManTester.class;\
	make jTest;\
	echo Java,VarSwap > line.txt;\
	wc -l outErr.txt > len.txt;\
        wc -l badTest.txt > outLine.txt;\
        wc -l okTest.txt > inLine.txt;\
        python3 logGen.py >> testLog.csv;\
	rm *.txt; rm IntMan*;\


