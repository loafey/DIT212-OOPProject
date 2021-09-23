run: 
	mvn clean package -DskipTests=true
	java -jar ./target/DIT212-project-1.0-SNAPSHOT.jar
