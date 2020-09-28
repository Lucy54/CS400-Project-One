all: start data Movie backend frontend
        @echo "FIXME: add goals to run and compile your application"
clean:
        $(RM) *.class
start: Start.java Movie.java Loader.java
        javac Start.java
        javac Movie.java
        java Start
Movie: Movie.java
        javac Movie.java
        java Movie
data: genre.txt movie.txt ratings.txt year.txt
backend: HashTableMap.java MainBackEnd.java MapADT.java projectOneADT.java data
        javac HashTableMap.java
        javac HashTableMapNode.java
        javac MainBackEnd.java
        javac MapADT.java
        javac projectOneADT.java
frontend: Commands.java backend
        javac Commands.java
        java Commands
