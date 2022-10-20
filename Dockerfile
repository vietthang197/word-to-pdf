FROM linuxserver/libreoffice:latest
RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f
ADD /target/wordpdf.jar app.jar

ENTRYPOINT exec java -Xms128m -Xmx2048m -Dfile.encoding=utf-8 -jar app.jar
