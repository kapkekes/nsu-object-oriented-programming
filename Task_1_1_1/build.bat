javadoc -d .\batch\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages io.github.kapkekes

javac -sourcepath .\src\main\java\ -d .\batch\bin\ .\src\main\java\io\github\kapkekes\HeapSort.java

mkdir .\batch\jar\
jar cf .\batch\jar\HeapSort.jar -C .\batch\bin\ .