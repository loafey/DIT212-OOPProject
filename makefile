run: 
	mvn clean package -DskipTests=true
	java -jar ./target/DIT212-project-1.0-SNAPSHOT.jar 

build: 
	mvn clean package -DskipTests=true

run-non:
	java -jar ./target/DIT212-project-1.0-SNAPSHOT.jar

run-non2:
	java -jar ./target/DIT212-project-1.0-SNAPSHOT.jar
