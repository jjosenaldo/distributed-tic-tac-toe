all:
	mkdir -p bin
	find ./src/ -name "*.java" | xargs javac -d bin # Compile project to bin
	rmic -classpath ./bin/ server.Server -d bin

clear:
	rm bin/* -rf

run:
	java -classpath ../tictactoe_client/bin/:./bin/ main.ServerMain
