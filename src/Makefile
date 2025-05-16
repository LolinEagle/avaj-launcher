all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

clean:
	rm */*.class sources.txt

re:clean
	make all

run:re
	java simulator.Simulator ../scenarios/scenario2.txt

.PHONY: all clean re run
