
compile: bin
	find src/oop/ass7game -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:src/oop/ass7game:resources -d bin @sources.txt
	rm sources.txt
jar:
	cp MANIFEST.MF bin
	cp -R resources bin
	(cd bin; jar -cfm ass7game.jar MANIFEST.MF oop/ass7game/*.class resources)
	cp bin/ass7game.jar .
	rm bin/ass7game.jar bin/MANIFEST.MF
run:
	java -jar ass7game.jar
clean: 
	rm -r bin
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/oop/ass7game/*.java
bin:
	mkdir bin
