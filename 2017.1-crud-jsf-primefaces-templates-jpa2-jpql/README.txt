Para que a aplicação funcione corretamente:
* Adicione o Driver do banco de dados que você está utilizando na pasta do Tomcat/Glassfish (<TOMCAT_HOME>\lib ou <GLASSFISH_HOME>\glassfish\domains\domain1\lib)
* Adicionar o pool de conexões no Glassfish, de modo que os dados de conexão sejam gerenciados pelo Glassfish e não pela aplicação web (ver pasta \docs).

### Novidades ###

* Adição do atributo showDetail="true" nos componentes "p:messages" para fazer com que o detalhe da mensagem seja apresentado
* Parâmetro "javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE"
	- web.xml
