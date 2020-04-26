# 个人学习内容

- [爬虫](#java爬虫工具包的使用)
- [大厂机试题](#大厂机试题)
- [Java进阶](#(Java进阶)主要是一些工具API，多线程，JVM的学习用例和笔记)
- [rabbitMQ](#rabbitMQ)
- [线程池](#springBoot创建线程池bean)    
### java爬虫工具包的使用
####  Jsoup工具包
**jsoup** 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。
 
**jsoup的主要功能如下**：
   * 从一个URL，文件或字符串中解析HTML
   * 使用DOM或CSS选择器来查找、取出数据
   * 可操作HTML元素、属性、文本
  
**Jsoup依赖**
`<dependency>

     <groupId>org.jsoup</groupId>
 
     <artifactId>jsoup</artifactId>
 
     <version>1.10.3</version>

 </dependency>`
 
#### WebMagic
    
**WebMagic**的结构分为**Downloader**、**PageProcessor**、**Scheduler**、**Pipeline**四大组件，
并由Spider将它们彼此组织起来。这四大组件对应爬虫生命周期中的下载、处理、
管理和持久化等功能。WebMagic的设计参考了Scapy，但是实现方式更Java化一些。
 
而Spider则将这几个组件组织起来，让它们可以互相交互，流程化的执行，
可以认为Spider是一个大的容器，它也是WebMagic逻辑的核心

 **WebMagic的四个组件**
 1.Downloader
 Downloader负责从互联网上下载页面，以便后续处理。WebMagic默认使用了Apache HttpClient作为下载工具。
  
 2.PageProcessor
 PageProcessor负责解析页面，抽取有用信息，以及发现新的链接。WebMagic使用Jsoup作为HTML解析工具，并基于其开发了解析XPath的工具Xsoup。
  
 在这四个组件中，PageProcessor对于每个站点每个页面都不一样，是需要使用者定制的部分。
  
 3.Scheduler
 Scheduler负责管理待抓取的URL，以及一些去重的工作。WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理。
  
 4.Pipeline
 Pipeline负责抽取结果的处理，包括计算、持久化到文件、数据库等。WebMagic默认提供了“输出到控制台”和“保存到文件”两种结果处理方案。
  
 Pipeline定义了结果保存的方式，如果你要保存到指定数据库，则需要编写对应的Pipeline。对于一类需求一般只需编写一个Pipeline。
 
 `<dependencies>
  
          <!--WebMagic-->
  
          <dependency>
  
              <groupId>us.codecraft</groupId>
  
              <artifactId>webmagic-core</artifactId>
  
              <version>0.7.3</version>
  
          </dependency>
  
          <dependency>
  
              <groupId>us.codecraft</groupId>
  
              <artifactId>webmagic-extension</artifactId>
  
              <version>0.7.3</version>
  
          </dependency>
  
      </dependencies>
      `
### 大厂机试题
### (Java进阶)主要是一些工具API，多线程，JVM的学习用例和笔记
## rabbitMQ
#### springBoot集成rabbitMQ

## springBoot创建线程池bean
创建线程池并且使用消息异步发送
