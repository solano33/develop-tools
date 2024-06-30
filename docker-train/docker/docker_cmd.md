
#### 查询相关

docker images
docker ps -an 1


#### 运行容器
docker run -it redis:6.0.6 /bin/bash
启动并进入容器
docker run -d ubuntu:20.04 /bin/bash
启动并进入容器，这里要求镜像存在进程不中断，不然会直接终止

docker exec -it e71fee01038a /bin/bash
会启动新的进程
使用exec进入容器后，使用exit后不会终止容器
docker attach e71fee01038a
不会启动新的进程
使用attach进入容器后，使用exit后会终止容器

#### 导入导出
docker export e71fee01038a > ubuntu_export.tar
导出容器为tar镜像文件
cat ubuntu_export.tar | docker import - solano/ubuntu 3.7
导入tar镜像文件为镜像
docker save -o hello.tar hello-world:latest
导出容器为tar镜像文件
docker load -i hello.tar
导入tar镜像文件

#### 制作镜像
docker commit -m="添加vim命令” -a="solano"  e71fee01038a solano/ubuntu:20.05
首先docker run运行容器，然后在容器内部进行一些操作，比如新的命令工具安装
然后将其进行commit，打包成一个新的镜像
docker login --username=aliyun2622701252 registry.cn-hangzhou.aliyuncs.com
登陆阿里云docker仓库
docker pull registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
可以从远端仓库拉镜像
docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
docker push registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
也可以将本地提交后新的镜像推送到远端仓库

#### 容器卷挂载
docker run -it --privileged=true -v /Users/wuhongbin/Documents/docker/ubuntu:/home/work ubuntu:v1 /bin/bash
--privileged=true表示进行权限共享，可以使容器类拥有root权限
-v 宿主机目录:容器内目录:rw/ro：表示将宿主机目录挂在到容器内，同时可指定读写模式
docker run -it --privileged=true --volumes-from u1 --name u2 ubuntu /bin/bash


Docker基础命令
查询相关
docker images
docker ps -an 1
运行容器
docker run -it redis:6.0.6 /bin/bash
启动并进入容器
docker run -d ubuntu:20.04 /bin/bash
启动并进入容器，这里要求镜像存在进程不中断，不然会直接终止

docker exec -it e71fee01038a /bin/bash
会启动新的进程
使用exec进入容器后，使用exit后不会终止容器
docker attach e71fee01038a
不会启动新的进程
使用attach进入容器后，使用exit后会终止容器

导入导出
docker export e71fee01038a > ubuntu_export.tar
导出容器为tar镜像文件
cat ubuntu_export.tar | docker import - solano/ubuntu 3.7
导入tar镜像文件为镜像
docker save -o hello.tar hello-world:latest
导出容器为tar镜像文件
docker load -i hello.tar
导入tar镜像文件

制作镜像
commit 构建新镜像
docker commit -m="添加vim命令” -a="solano"  e71fee01038a solano/ubuntu:20.05
首先docker run运行容器，然后在容器内部进行一些操作，比如新的命令工具安装
然后将其进行commit，打包成一个新的镜像
docker login --username=aliyun2622701252 registry.cn-hangzhou.aliyuncs.com
登陆阿里云docker仓库
docker pull registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
可以从远端仓库拉镜像
docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
docker push registry.cn-hangzhou.aliyuncs.com/solano/ubuntu:[镜像版本号]
也可以将本地提交后新的镜像推送到远端仓库
Dockerfile构建新镜像
docker build -t ubuntu-jdk11:v1 .
要求本路径下有个Dockerfile文件

容器卷挂载
docker run -it --privileged=true -v /Users/wuhongbin/Documents/docker/ubuntu:/home/work ubuntu:v1 /bin/bash
--privileged=true表示进行权限共享，可以使容器类拥有root权限
-v 宿主机目录:容器内目录:rw/ro：表示将宿主机目录挂在到容器内，同时可指定读写模式
docker run -it --privileged=true --volumes-from u1 --name u2 ubuntu /bin/bash


常用中间件安装
mysql
docker run -d -p 3307:3306 --privileged=true -v /Users/wuhongbin/Documents/docker/mysql/log:/var/log/mysql -v /Users/wuhongbin/Documents/docker/mysql/data:/var/lib/mysql -v /Users/wuhongbin/Documents/docker/mysql/conf:/etc/mysql/conf -e MYSQL_ROOT_PASSWORD=root --name mysql mysql
docker exec -it mysql /bin/bash
mysql -uroot -proot

mysql主从分离案例
master
docker run -d -p 3307:3306 --privileged=true -v /Users/wuhongbin/Documents/docker/mysql/master-slave/master/log:/var/log/mysql -v /Users/wuhongbin/Documents/docker/mysql/master-slave/master/data:/var/lib/mysql -v /Users/wuhongbin/Documents/docker/mysql/master-slave/master/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root --name mysql-master mysql
slave
docker run -d -p 3308:3306 --privileged=true -v /Users/wuhongbin/Documents/docker/mysql/master-slave/slave/log:/var/log/mysql -v /Users/wuhongbin/Documents/docker/mysql/master-slave/slave/data:/var/lib/mysql -v /Users/wuhongbin/Documents/docker/mysql/master-slave/slave/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root --name mysql-slave mysql


CREATE USER 'salve'@'%' IDENTIFIED BY 'root';
GRANT REPLICATION SLAVE ON *.* TO 'salve'@'%';
flush privileges;

Dockerfile
构建命令
docker build -t ubuntu-jdk11:v1 .
需要在当前目录下有个Dockerfile

FORM
表示当前构建镜像的基础镜像环境
RUN
RUN <命令行命令>
RUN yum -y install vim
RUN ["可执行文件", "参数1", "参数2"]
RUN ["./test.php", "dev", "offline"]等价于RUN ./test.php dev offline
WORKDIR
WORKDIR <默认基础路径>
WORKDIR /usr/local/tomcat 表示启动容器后默认进入该目录
ENV
ENV CATALINA /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH表示申明一个变量
VOLUME
容器数据卷，用于数据保存和持久化工作

ADD
将宿主机目录下的文件拷贝进镜像且会自动处理URL和解压tar压缩包
COPY
类似ADD，拷贝文件和目录到镜像中。dest为容器内制定的路径，不用事先创建
COPY src dest
COPY ["src", "dest"]

CMD
指定容器启动后要干的事情，如果有多个CMD的话docker build会正常执行，但是docker run只会指定最后一个CMD
CMD <命令>
CMD ["可执行文件", "参数1", "参数2"]
CMD ["参数1", "参数2", …]在指定了ENTRYPOINT指令后，用CMD指定具体的参数
注意和前面RUN命令的区别
RUN：docker build时运行
CMD：docker run时运行

ENTRYPOINT
指定容器启动时要运行的命令
类似于CMD指令，但是ENTRYPOINT不会被docker run后面的命令覆盖，而且这些命令行参数会被当作参数送给ENTRYPOINT指定指定的程序

EXPOSE
EXPOSE 8080: 暴露8080端口


Kubernetes：K8S
k8s集群架构