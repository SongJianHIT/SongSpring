# 【miniSpring】IOC

## 1 概念

IOC，控制反转。**控制** 指的是 **对 JAVA 对象创建的控制权**，**反转** 指的是 **将 JAVA 对象创建的控制权从程序员手中（`new`）反转到 IOC 容器（反射 `newInstance`）手中**。

**为什么需要控制反转？控制反转有什么作用？**

- 因为容器框架并不知道未来业务中需要注入哪个 Bean, 于是通过配置文件等方式告诉容器, 容器使用反射技术管理 Bean 的创建, 属性注入, 生命周期等
- 让程序员无需在管理对象上花费精力

## 2 实现

### 2.1 原始 IOC 实现

一个 IOC 容器需要的部件：

- 一个部件对应 Bean 内存的映像
  - 意思就是，对一个 Bean 的定义内容，通过一个内存中的类来进行读取保存
  - 相当于把磁盘文件，读取到内存中

- 一个 XML reader 负责从外部 XML 文件中读取 Bean 的声明
- 一个反射部件负责加载 Bean Class 并创建这个实例
- 一个 Map 负责保存 Bean 的实例
- 提供一个 `getBean()` 方法供外部使用

<img src="【miniSpring】IOC.assets/a382d7774c7aa504231721c7d28028c3.png" style="zoom:50%;" />

`ClassPathXmlApplicationContext` 的功能：

- 读取、解析 xml 文件中的内容
- 加载解析的内容，创建 `BeanDefinition` ，将 `Bean` 的配置信息放到内存
- 根据 `BeanDefinition` ，通过反射实例化 `Bean`，并将

![image-20230329140657141](【miniSpring】IOC.assets/image-20230329140657141.png)

通过构建，我们在业务程序中不需要再手动  `new` 一个业务类，只要把它交由框架容器去管理就可以获取我们所需的对象。另外还支持了 `Resource` 和 `BeanFactory`，**用 `Resource` 定义 `Bean` 的配置数据来源，让 `BeanFactory` 负责 `Bean` 的容器化管理** 。

![image-20230330152748605](./【miniSpring】IOC.assets/image-20230330152748605.png)

### 2.2 扩展 IOC 容器

我们对上述 IOC 容器进行扩展，主要包括：

- 增加单例 Bean 的接口定义，把所有的 Bean 默认设置为单例模式
- 预留事件监听的接口，方便后续进一步解耦代码
- 扩展 BeanDefinition，增加属性

#### 管理单例Bean的容器

`SingletonBeanRegistry` 中定义了管理单例 Bean 的接口，包括 Bean 的注册、获取、判断是否存在等。

`DefaultSingletonBeanRegistry` 是 `SingletonBeanRegistry` 的具体实现。

`SimpleBeanFactory` 为了实现单例，直接继承 `DefaultSingletonBeanRegistry` ，调用父类方法和成员变量 `ConcurrentHashMap`。

![image-20230330162551929](./【miniSpring】IOC.assets/image-20230330162551929.png)

#### 增加事件监听

构建好单例 Bean 之后，**为了监控容器的启动状态**，我们要增加事件监听。























