#### 想要移除某个文件夹被git忽略
如果这个文件夹没有提交过则直接配.gitignore即可;
如果之前提交过，则需要先移除git管理
```gitignore
git rm --cached -r .idea
git add .
git commit -m "refactor: 移除.idea管理"
```