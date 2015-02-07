# Work Analysis #
----------

### 背景 ###
读取excel统计员工在项目中的工时

### 技术选型 ###
- 版本控制：Git
- jar包管理：maven3.2

### 开发分支 ###
- master：主分支
- develop：开发分支，用于合并各种版本
- release：用于发布版本
- hotfix：用于修复bug
- feature：用于开发新功能
- tag（只读）：用于存放各发布的版本副本


### 作者信息 ###
- 作者:Michael
- email:miles801@163.com


### 功能列表 ###

##### v1.0 #####
>+ 第一个可使用版本


### 注意事项 ###
1. 该应用采用的是servlet3.0开发，所以应用服务器应该是Tomcat7或以上!
2. 如果采用其他应用服务器（不支持servlet3.0的），可以手动在web.xml中配置com.michael.workanalysis.basic.AnalysisCtrl类
3. 注意：统计的文件的存放目录目前是d:/upload/silu/
