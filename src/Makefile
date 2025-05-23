all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

clean:
	rm */*.class *.txt

re:clean
	make all

run:all
	java simulator.Simulator ../scenarios/scenario.txt
	cat -e simulation.txt

.PHONY:all clean re run
