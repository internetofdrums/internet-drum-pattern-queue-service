FROM openjdk:9-slim
RUN apt-get update  
RUN apt-get install -y maven
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN sh install.sh
CMD ["sh", "run.sh"]
