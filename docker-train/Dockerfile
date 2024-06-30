FROM ubuntu:v1
MAINTAINER solano<solano33@163.com>

# 指定一个变量，设置工作路径
ENV mypath /usr/local
WORKDIR $mypath

# 安装ifconfig等网络命令
RUN apt update && \
    apt install net-tools && \
    apt install -y openjdk-11-jdk && \
    apt-get clean

# ADD将文件添加并解压至指定目录下，可使用相对路径
ADD ../../../docker/test/apache-maven-3.9.4-bin.tar.gz /usr/local/maven

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-arm64/
ENV PATH $JAVA_HOME/bin:$PATH

ENV MAVEN_HOME /usr/local/maven/apache-maven-3.9.4-bin
ENV CLASSPATH $MAVEN_HOME/lib:$CLASSPATH
ENV PATH $MAVEN_HOME/bin:$PATH

EXPOSE 80

CMD ["java", "-version"]
CMD echo $mypath
CMD echo "success build Dockerfile......ok"
CMD /bin/bash
