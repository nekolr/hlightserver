# hlightserver
## 简介
虽然markdown已经普及，天然支持代码高亮，但是如果你想在文档格式的文件（如：doc/docx）中加入好看的代码，可以使用或部署该服务。
该服务是一个Java编写的小型Web服务器，基于[nanohttpd](https://github.com/NanoHttpd/nanohttpd)开发。使用该服务可以将想要美化的代码提交由web服务器生成美化后的代码页面。   

这里有一个正在使用中的例子：[Demo](https://nekolr.com/SyntaxHighlighter/)   

![hlight](https://github.com/nekolr/hlightserver/blob/master/preview.png)
## 使用
使用前请确保系统中安装有Java运行环境。

- 下载release包，解压
- 使用 `java -jar hlightserver-1.0.jar` 运行
- 打开index.html，开始使用吧
## 其他
高亮插件使用的是[syntaxhighlighter](https://github.com/syntaxhighlighter/syntaxhighlighter)，不过似乎这一版的样式在github上找不到了，传送门：[老版本](https://code.google.com/archive/p/syntaxhighlighter/)
