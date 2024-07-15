#### 操作系统命令
top：展示当前操作系统中占用CPU最高的进程
    top -Hp <PID> 或者 shift+h切换成线程模式，可以查看占用CPU最高的线程

jps：查看当前执行中的java进程

jinfo <PID>：查看某java进程的属性详情，包含启动参数、JVM参数

jstat -gc <PID> 5000：打印内存里的占用情况，每5000毫秒刷新一次

jstack <PID>：把java进程里的所有线程详情打印出来，包含名称、优先级、调用战

jmap -histo <PID> | head -20：会打印出当前内存中前20个每种对象占用的个数和占用内存大小
    jmap -dump:format=b,file=case/dump.hprof <PID>
    生产环境中可以随便执行jmap吗？**当前不行，会导致STW，然后进行对象扫描，这显然是不能接受的**