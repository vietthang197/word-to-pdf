FROM linuxserver/libreoffice:latest

ADD /target/wordpdf.jar app.jar

ENTRYPOINT exec java -Xms128m -Xmx2048m -Dfile.encoding=utf-8 -jar app.jar
