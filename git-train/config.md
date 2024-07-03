#### 查看git生效配置文件和用户名邮箱
git config --show-origin --get user.name
git config --show-origin --get user.email

#### 修改配置文件
git生效的配置文件主要有两种：
* 全局配置文件：C:\Users\Mi\.gitconfig
* 项目配置文件：.git/config

#### 通过配置指定某个文件夹下git配置
```gitexclude
[user]
        email = wuhongbin@xiaomi.com
        name = wuhongbin
[core]
        autocrlf = input
        longpaths = true
[includeIf "gitdir:D:/github/"]
        path = C:/Users/Mi/git/solano.gitconfig-personal
```
其中gitdir:D:/github/为指定的路径，C:/Users/Mi/git/solano.gitconfig-personal为配置内容，如下：
```gitexclude
[user]
	name = solano-m
	email = solano33@163.com
```