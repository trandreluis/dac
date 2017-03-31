Exemplo de como criar um projeto java convencional usando o maven.

1) Comando para criar um projeto maven via linha de comando

mvn archetype:generate -DgroupId=br.edu.ifpb.mt.dac -DartifactId=2017.1-hello-world-maven -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

2) Importar o projeto no Eclipse usando a opção File -> Import... -> Existing Maven Projects ...

3) Configurar o pom.xml com o plugin "maven-jar-plugin".

4) Empacotar a aplicação:

mvn package

5) Rodar a aplicação:

java -jar target\2017.1-hello-world-maven-1.0-SNAPSHOT.jar
