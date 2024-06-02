
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



