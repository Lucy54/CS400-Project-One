all: data Movie backend frontend

Movie: Movie.java
	javac Movie.java
	java Movie

data: genre.txt movie.txt ratings.txt year.txt

backend: HashTableMap.java HashTableMapNode.java MainBackEnd.java MapADT.java projectOneADT.java
	data
	javac HashTableMap.java
	javac HashTableMapNode.java
	javac MainBackEnd.java
	javac MapADT.java
	javac projectOneADT.java

frontend: Commands.java backend
	javac Commands.java
	java Commands
