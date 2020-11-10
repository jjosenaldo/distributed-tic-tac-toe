all:
	mkdir -p bin
	find ./src/ -name "*.java" | xargs javac -d bin # Compile project to bin
	rmic -classpath ./bin/ client.Client -d bin

clear:
	rm bin/* -rf

run:
	java -classpath ../tictactoe_server/bin/:./bin/ main.ClientMain
