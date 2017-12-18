mini project reconstruct 

---module lkl-mini-api----



---module lkl-mini-client-core----



---module lkl-mini-server-core----



---module lkl-mini-webapp----

package command:

mvn clean package -Dmaven.test.skip=true

start command:

java -jar **.jar


ATTENTION:

dubbo publish services on servlet container,so the port of wsdl service must be the same as the  server port 