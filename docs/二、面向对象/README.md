# 二、面向对象

## 目录

- [面向对象](#面向对象)  
  - [面向对象基本概念](#面向对象基本概念)
  - [内存里的对象](#内存里的对象)
  - [面向对象的特殊语法](#面向对象的特殊语法)
  - [修改权限修饰符](#修改权限修饰符)
  - [面向对象三大特征之封装](#面向对象三大特征之封装)
  - [面向对象三大特征之继承](#面向对象三大特征之继承)
  - [面向对象三大特征之多态](#面向对象三大特征之多态)
  
- [抽象类及接口](#抽象类及接口)  
  - [抽象类](#抽象类)
  - [接口](#接口)

- [内部类和匿名内部类](#内部类和匿名内部类)  
  - [内部类](#内部类)
  - [匿名内部类](#匿名内部类)

- [常见类](#常见类)  
  - [Object类](#Object类)
  - [String类](#String类)
  - [StringBuffer类](#StringBuffer类)
  - [Date类](#Date类)
  - [DateFormat类](#DateFormat类)
  - [Math类](#Math类)


## 1 面向对象

### 1.1 面向对象基本概念

面向过程的思想：程序功能是由一系列有序的动作来完成。
    
面向对象的思想：程序是由一系列的对象和对象间的消息组成。  
- 运行中的程序，由多个对象组成。

- 运行中的对象，需要相互协作，共同完成程序的功能。

要想写出面向对象的程序，首先要学会构建**面向对象程序的基本组成单位 —— 对象**。  

类：同种物体在属性和行为上的集合与抽象，是构造 Java 对象的模板。

类和对象的关系：
- 类描述出了该种类型对象共有的属性和行为。

- 类描述了对象有哪些属性，具备哪些行为（包括行为的具体实现）。

- 各个对象的属性取值由具体的对象确定。

Java 语言中定义类，其实就是定义类的成员（成员变量和成员方法）：  
- 成员变量：就是事物的属性。  
  成员变量是定义在类体中，方法体之外的变量。

- 成员方法：就是事物的行为。  
  之前学的方法的修饰符都是 public static，修饰符中去掉 static 修饰符的方法就是成员方法。

示例：  
```java
/*
定义一个学生类：  
- 属性（成员变量）：姓名，性别，年龄，学号
- 行为（成员方法）：吃饭，睡觉，学习  
*/
class Student {
  // 成员变量
  String name;
  boolean = isMale;
  int age;
  long id;

  // 成员方法
  public void eat() {}
  public void sleep() {}
  public void study() {}
}

```
有了类定义，我们就有了对同种类型的对象的描述，我们就可以创建学生对象了
- 创建对象：
  ```java
  类名 对象名 = new 类名();
  ``` 
  示例：  
  ```java
  Student st = new Student();
  ```

- 给对象的属性赋值, 或者访问对象的属性：
  ```java
  对象名.成员变量
  ```
  示例：  
  ```java
  st.name = "zhang3";
  ```

- 访问对象的行为：
  ```java
  对象名.成员方法
  ```
  示例：   
  ```java
  st.eat();
  ```

局部变量和成员变量的比较：
- 在类中定义的位置不同。

- 在内存中的位置不同：
  - 局部变量存储在方法对应的栈中。

  - 成员变量存储在堆上，在对象的内存中。

- 初始化值不同：
  - 局部变量没有天生的初始值，必须在使用之前用代码初始化成员变量的值。

  - 成员变量存储在堆上的，天然有默认初值。

- 生命周期不同：
  - 局部变量存储在栈帧中，随着栈帧的存在而存在，随着栈帧的销毁而销毁。

  - 成员变量存储在堆上，随着对象的存在而存在，随着对象的销毁而销毁。

### 内存里的对象

类本身属于引用数据类型，对于引用数据类型的执行分析，必须结合内存操作来看。
- 堆内存保存每一个对象的属性内容；堆内存需要用关键字 new 才可以开辟，如果一个对象没有对应的堆内存指向，将无法使用；  

- 栈内存保存的是一块堆内存的地址数值，可以把它想象成一个 int 类型变量（每一个 int 类型变量只能存放一个数组），所以每一块栈内存只能够保留一块堆内存地址。

对象的内存图解：  
<div align="center">
<img src="./img/p1.png">
</div>

### 面向对象的特殊语法

**（1）构造方法**  

构造方法的作用就是在 JVM 构造对象的时候，进行成员变量的初始化。  
其定义的原则是：  
- 方法名称与类名相同。

- 没有返回值类型声明。

- 构造方法可以进行重载。  

示例：
```
class Student {
  // 成员变量
  String name;
  boolean = isMale;
  int age;
  long id;

  // 构造方法
  public Student(String nameValue, boolean isMaleValue) {
    name = nameValue;
    isMale = isMaleValue;
  }
}
```
当我们没有在类中定义任何一个构造方法的时候，JVM 会自动添加一个默认的构造方法（无参构造方法）。但是，一旦我们自己在类中定义了哪怕只有一个构造方法，JVM 就不会再自动添加无参构造方法。
- 这和 JVM 执行固定流程有关系：开辟对象的存储空间 -> 给对象成员变量赋予默认初值 -> 使用构造方法，初始化对象成员变量的值。


**（2）this关键字**  

成员变量的隐藏问题：当方法中定义了和类中同名的成员变量时，在方法体中，通过同名的变量名来访问变量值，只能访问到方法中的那个局部同名变量的值，而访问不到，同名成员变量的值。  
在方法体中，就好像同名成员变量，被同名局部变量给隐藏起来了。

this 关键字：代表对象自身的引用。  
- 在构造方法中的 this，指代的当前对象，就是构造方法执行时，JVM 正在创建的对象。

- 对于普通成员方法而言，this 指代的对象 `对象名.方法()` 指的是对象名对应的对象。  

示例：
```
class Student {
  // 成员变量
  String name;
  boolean = isMale;
  int age;
  long id;

  // 构造方法
  public Student(String name, boolean isMale) {
    this.name = name;
    this.isMale = isMale;
  }
  public Student(String name, boolean isMale, int age) {
    this(name, isMale); // 必须处在该构造方法体的第一条语句的位置。

    this.age = age;
  }
}
```

this 关键字的作用：
- 解决成员变量的隐藏的问题。

- 访问对象的成员（访问当前对象的成员变量值，访问当前对象的成员方法）。

- 访问对象的构造方法。
  1. 只能在某个构造方法体中使用 this 调用构造方法；
  2. 必须处在该构造方法体的第一条语句的位置。

**（3）static关键字**  

static 关键字：可以修饰成员变量和成员方法。  

注：按照严格意义上的面向对象思想，static 修饰的成员变量和成员方法都不能算做类中定义的成员。只是习惯上的称呼称 static 修饰的变量为静态成员变量，修饰的方法为静态成员方法。

示例：
```java
public class TestDemo {
  public static void main(String[] args) {
      HelloWorld.print();
  }
}

class HelloWorld {
    static void print() {
        System.out.println("HelloWorld");
    }
}

/* 运行结果：
HelloWorld
*/
```

static 关键字的特点：  
- 被类的所有成员所共享（这点判定是否使用 static）。   
  - 当 static 修饰了成员变量，该成员变量的值就不存在其存储对象中，而是单独存储一份，被类的所有对象所共享。  

  - 当 static 修饰成员方法时，该方法被当前类的所有方法共享。

- 可以通过类名访问。  
  - 可以通过类名直接访问 static 成员变量的值。

  - 通过类名直接调用 static 成员方法。  

- 随着类的加载而加载。  
  - static 成员变量，随着类加载过程，其实就已经在方法区中，分配了内存。

  - static 成员方法, 一旦类加载完毕，就可以直接访问，而不必等待创建对象，然后再用 `对象名.访问方法` 的方式。

- 优先于对象而存在。  
  - 不依赖于对象而存在。  
     1. 成员变量的角度来理解：  
     static 修饰的成员变量的值不再存储在该类的每个对象中。  
     作为对比，没有被 static 修饰的成员变量都依赖于对象而存在，因为他们的值都存储在对象中。

     2. 成员方法角度：  
     被 static 修饰的成员方法，在没有对象存在的情况下，也可以直接通过类名来调用方法。  
     作为对比，没有被 static 修饰的普通成员方法，它依赖于对象而存在。原因是普通成员方法中，可以访问普通成员变量的值，而普通对象的值又是依赖于对象而存在的。

  - 先出现在内存。  
    静态成员变量， 一定先于没有被 static 修饰的普通成员变量出现在内存中。


static 关键字注意事项：  
- 非静态成员变量（非静态的成员方法）不能在静态上下文（静态方法的方法体）中被访问。  

- 静态方法的方法体中是没有 static 关键字的。

- 静态方法的使用场景：  
  静态方法和非静态方法除了访问方式不同，最大的区别就是静态方法可以访问到的数据集合（静态方法方法体中，不能直接访问非静态的成员变量）。所以通常静态方法所访问的数据要么是静态的成员变量，要么是方法的形式参数。  
  通常定义静态方法都是为了方便使用该方法的功能（工具方法），使其不用创建对象就可以使用。


**（4）代码块**  

在 Java 中，使用 `{}` 括起来的代码被称为代码块，根据其位置和声明的不同，可以分为局部代码块、构造代码块、静态代码块和同步代码块（多线程讲解）。  

局部代码块（开发中不会用）：方法体中用 `{}` 括起来的一段代码。  
- 执行时机：随着方法的执行而执行。  

- 优点：限定变量生命周期，及早释放，提高内存利用率。  
  （这个优点理论上确实存在，但是这个优点，在现在 JVM 中的效果微乎其微，甚至可以忽略不计）

注：在嵌套的代码块中，不能定义同名变量。

构造代码块：类中方法外用 `{}` 包含的代码。  
- 执行特征：创建对象的时候执行。  
  注：构造方法和构造代码块，都是在创建对象的时候执行，但是构造代码块先执行，构造方法后执行。

- 构造代码块的使用场景：
  - 可以把多个构造方法方法中相同的代码提取放到构造代码块中。

  - 可以用构造代码块来初始化对象的成员变量的值。  
  注：成员变量初始化语句和构造代码块的执行顺序按顺序结构。  

静态代码块：类中方法外，被 static 修饰。  
- 执行特征：随着类加载而执行，所以静态代码块还有一个特征 —— 至多执行一次。


**（5）package关键字**  

在 Java 源程序文件的第一行使用 package 声明，可以使文件中定义的类成为指定包的成员。  

声明语法：`package 包名;`。  

包名通常由多个字符串名构成，中间用 `.` 分隔。每个字符串名表示的包称为其前面的名字表示的包的子包。  

通常以组织机构的域名反转形式作为其所有包的通用前缀，比如：`com.somecompany.apps`

注：Java 中定义的任何一个类，都是在某一个包下的。如果代码没声明所属的包，此时类会在 Java 中的一个默认包中。

**（6）import关键字**  

在类名前面加上类所属的包名，中间用 `.` 分隔，称为类的完全限定名（Full Qualified Name），简称类的限定名。  

当在类体中使用了与当前类不同包的类名时，编译器编译时会因为无法找到该类的定义而报错。对此有两个解决办法：  
1. 使用不同包类的完全限定名；
2. 使用 import 声明，为编译器提供该类的定义信息。

声明语法：`import <类的完全限定名>;`。    
示例：
```java
import java.util.Scanner;
```

注：
- import 声明一般紧跟在 package 声明之后，且必须在类声明之前。

- Java 语言核心包 java.lang 包中的类将被隐式导入，可以直接使用其中的类。

- import 声明提供了一种包的智能导入方式：`import <包名>.*;`。包中的类将根据需要导入，避免使用多条 import 声明。  
  注：智能导包（不会递归导入子包中的类）。  


### 访问权限修饰符

在 Java 语言中，一切事物（类所有成员）都具有或显示定义或隐式定义的访问权限，而这种语言层面的访问权限控制，是由访问权限修饰符实现。  

访问权限修饰符的访问控制，分为 2 个层面：
- 修饰类中成员（field & method）。  
  控制类中的成员，对其他类可见性（其他类是否可以直接使用到）。

- 修饰类。  
  通常用来限定类库中的类（自定义数据类型），对于外部使用者的可见性（是否能使用该类型）。

修饰类中成员可以使用的访问权限修饰符有 4 种：
- public：任意类均访问，实际就是没有限制访问权限。  
  1. 类体中，可以直接访问；
  2. 同包的其他类中，也可以访问；
  3. 不同包的的类中，也可以访问。

- protected：同包中的其他类，和不同包的（可见）子类均可见。
  1. 类体中，可以直接访问；  
  2. 同包其他类中，可以访问；  
  3. 在非同包的一部分类中，访问不到（有一部分子类中可以访问到：父类中被 protected）。

- default：默认权限，隐式定义。
  1. 类体中，可以直接访问；
  2. 同包其他类中，可以访问；
  3. 不同包的类中，访问不了。

- private：仅对同类中的其他成员可见。  
  使用户不要触碰他们「不该」触碰的代码（private），类库设计者可以安全地修改实现细节。
  1. 类体中，可以访问
  2. 同包其他类中，无法访问
  3. 非同包中，无法访问  

针对 private 成员变量，专门定义 public 的方法，让外部能够访问私有成员变量的值。
- get：让外部读取到私有成员变量值。

- set：让外部通过该方法，修改私有成员变量的值。

同时，为了清楚的表名 get 和 set 分别获取和改变的是哪个成员变量的值，命名规则为：
`getXxx()`、`setXxx(修改目标值参数)`。

示例：
```java
class Student {

    private String name;
    private int age;
    private boolean isMale;
    
    public Student(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

提供 get、set 方法的好处：
-  可以满足某些场景下，需要访问私有成员变量值的需求。

-  通过定义 get 和 set 方法来实现对私有成员变量的读写分离。

-  一旦一个成员变量被 private 修饰，别人就无法直接通过 `对象名.成员变量` 访问，只能通过 `set()` 此时我们在 set 方法中，就可以通过代码控制别人的修改。

各权限修饰符对成员的可见性的影响：  
| | public | protected | default | private |
| - | - | - | - | - | 
| 同一类 | √ | √ | √ | √ |
| 同包子类 / 其它类 | √ | √ | √ | |
| 不同包子类 | √ | √ |  |  |
| 不同包其它类 | √ |  |  |  |  


能够修饰类的访问权限修饰符只有 2 种：
- public：对其他任意类可见。  
  任何其他地方都可以访问到这个类（同一个 module 下），比如同包中的其他类以及非同包的其他类。

- default：对同包中的其他类可以访问，非同包的类不可访问。


### 面向对象三大特征之封装

封装是一种信息隐藏技术，是指将数据和基于数据的操作封装在一起。数据被保护在内部，系统的其他部分只有通过在数据外面的被授权的操作才能够进行交互。目的在于将类使用者 class user 和类设计者 class creator 分开。  

在面向对象的编程中，用类来封装相关的数据和方法保证了数据的安全和系统的严密性。

```
类
  成员变量（一定要考虑访问权限）

  构造方法
    无参构造方法
    带参构造方法

  成员方法
    getXxx()
    setXxx()
```
给成员变量赋值的方式：
-  无参构造方法 + setXxx()。

-  带参构造方法。

### 面向对象三大特征之继承

**（1）继承的概念**  

Java 中的继承和我们现实生活中的继承含义基本类似，但是含义更广。
- 简单来说都可以表示「不劳而获」（类似于现实世界中继承的含义）。

- 类型之间「is a」的关系：一种类型（类）继承另外一种类型（类）。

被继承的类称之为父类（基类或超类），继承其他类的类称之为子类（派生类，导出类）。

子类可以通过继承机制，不写任何额外代码就可以拥有父类的「所有」成员。

继承的语法：`class 子类名 extends 父类名 {}`。  

继承的优点：
- 代码复用（方法，类）。

- 提高了代码的可维护性（这是一把双刃剑）。

- 弱化 Java 中的类型约束（多态的前提）。

继承的缺点：父类的修改可能会出现在所有子类中（我们无法选择这些修改可以反映在哪些子类中，不可以反映在哪些子类中）。

Java 继承的特点：在 Java 中只能实现单重继承。  
单重继承：简单来说 Java 的 extends 关键字后只能跟一个。当然这并不意味着一个 Java 类只能继承某一个类的成员（可以间接继承）。  
示例：
```java
class SubDemo extends Demo{} // ok
class SubDemo extends Demo1, Demo2{} // error
```

继承的注意事项：  
- 子类只能访问父类所有非私有的成员（成员方法和成员变量）。

- 子类不能继承父类的构造方法。


子类继承父类，于是子类拥有了父类的成员。同时，子类本身又可以定义属于自己的成员。这样一来一个子类对象中包含的数据，其实由两部分组成：  
- 父类包含的可继承的成员（成员变量和成员方法）。

- 子类中自己定义的成员。

一旦子类继承了父类，那么在创建子类对象、初始化子类对象的过程及子类对象的初始化过程和之前相比也会有较大的不同。  
根本原因在于子类对象初始化时要初始化父类对象和子类对象两部分数据，而且父类对象（即父类成员部分）都应该先于子类对象（即子类中自己定义的成员部分）而被初始化。  
这种优先关系可以从 2 个角度去理解：  
- 直觉：儿子能继承父亲的前提是，父亲先存在。

- JVM：子类成员变量的初始化可能依赖于父类成员。

**（2）子类对象初始化**  

在 Java 语言中，子类对象的初始化有两种，隐式初始化和显式初始化。
- 隐式初始化：当父类提供了默认的构造方法且子类的构造方法中没有显式调用父类的其它构造方法时，在执行子类的构造方法之前会自动执行父类的构造方法。  

- 显式初始化：通过 super 关键字。
  
示例：  
```java
class ExplicitFather {
    int fatherInt;
    
    public ExplicitFather(int fatherInt) {
        this.fatherInt = fatherInt;
    }
}
class ExplicitSon extends ExplicitFather{
    int sonInt;
    
    public ExplicitSon(int fatherInt, int sonInt) {
        super(fatherInt);
        this.sonInt = sonInt;
    }
}
```

this 代表当前这个对象，代表对象的内存空间标识（当前类定义的内容）。  
super 代表对象的内存空间的标识（父类定义的内容）。

用法：  
- 访问成员变量：`super.成员变量`。

- 访问构造方法：`super(实参列表)`。

- 访问成员方法：`super.成员方法()`。

父类域的隐藏：如果在子类中定义了和父类同名的成员变量，则在子类类体中，通过同名变量名访问到的是子类中定义的成员变量。看起来就好像父类成员变量在子类中被隐藏。

注：
- 执行父类方法时，通过同名成员变量的名访问到的是父类中定义的成员变量。

- 通过 super 关键字也可以在子类对象中访问到父类中的同名成员变量。

**（3）方法覆盖**  

方法覆盖是子类对父类允许继承的方法的实现过程进行重写，返回值和形参都不变。即外壳不变，核心重写。  

方法覆盖的使用场景：当子类继承父类之后，想要修改父类的方法，就可以使用方法覆盖。  
示例：   
```java
public class TestDemo {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();

        animal.move();
        dog.move();
    }
}

class Animal {
    public void move() {
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal {
    @Override
    public void move() {
        System.out.println("狗可以跑和走");
    }
}
/* 运行结果：
动物可以移动
狗可以跑和走
*/
```

发生方法重载时：
- 在子类方法体访问到的是子类中定义的方法。

- 在父类方法体中执行发生重载的方法，访问到的仍然是子类中定义的方法。   

示例：   
```java
public class TestDemo {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        
        dog.act();
    }
}

class Animal {
    public void act() {
        this.move();  // 父类方法体中执行发生重载的方法
    }
    public void move() {
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal {
    @Override
    public void move() {
        System.out.println("狗可以跑和走");
    }
}

/* 运行结果：
狗可以跑和走
*/
```

方法重载主要看的是子类和父类的方法声明部分：访问权限、返回值和方法签名。
- 访问权限：并非子类和父类方法的访问权限要相同，只要子类方法的访问权限不小于父类方法的访问权限。

- 返回值：
  - 基本数据类型的方法返回值：子类必须和父类相同。

  - 引用数据类型：子类父类返回值类型相同；或子类方法返回值类型是父类方法返回值类型的子类类型（因为可以把子类类型看做是父类类型）。

- 方法签名：子类方法的方法签名必须和父类一样。

注：  
- 父类中私有方法不能被重写。

- 静态方法不能被重写！

**（4）final关键字**  

final 是最终的意思，可以修饰类、变量和成员方法。  
- 修饰类，类不能被继承。

- 修饰变量，变量就变成了常量，且只能被赋值一次。  
  - 修饰成员变量：必须在定义时初始化。  
    对于成员变量而言，如果被 final 修饰，必须在对象创建完毕之前，给对象的该成员变量值赋值。

  - 修饰局部变量：可以在定义时初始化，也可以选择在构造方法中进行初始化。  
    对于局部变量而言，如果被 final 修饰，那么该变量必须在使用之前赋值。

- 修饰方法，方法不能被重写（override）。


### 面向对象三大特征之多态  

**（1）多态的概念**  

多态：同一个对象的行为在不同的时刻或条件下表现出不同的效果。  
示例：
```java
public class TestDemo {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.act();

        animal = new Cat();
        animal.act();
    }
}

class Animal {
    public void act() {
        System.out.println("动物行为");
    }
}

class Dog extends Animal {
    public void act() {
        System.out.println("狗可以看家");
    }
}

class Cat extends Animal {
    public void act() {
        System.out.println("猫可以抓老鼠");
    }
}

/* 运行结果：
狗可以看家
猫可以抓老鼠
*/
```

要实现多态的效果有前提条件：
- 继承。

- 要有方法覆盖。

- 父类引用指向子类对象（对象有时也称之为实例）。

父类引用指向子类对象，去访问子类对象中的成员时（多态的特征）：
- 成员变量：编译看左边，运行看左边。  
  通过引用变量访问到的子类成员的范围，是由引用类型来决定的。

- 成员方法：编译看左边，运行看右边。  
  通过引用变量访问到的方法，是由引用实际指向的对象来决定的。

多态的好处：
- 提高了程序的维护性（由继承保证）。

- 提高了程序的扩展性（由多态保证）。

多态的弊端：
- 不能访问子类特有功能。  
  通过多态的转型可以解决。  

多态中的转型：  
- 向上转型：从子到父，即父类引用指向子类对象。  
  注意：向上转型，是 Java 语言天生就允许的。

- 向下转型：从父到子，即父类引用转为子类对象的引用。  
  格式：`子类类型 变量名 = (子类类型)父类类型的变量`。  
  注：向下转型是不安全的，因此 Java 语言默认不允许向下转型。但是通过 instanceof 关键字可以做到安全的向下转型。
  
instanceof 关键字：instanceof 关键字的作用是判断其左边对象是否为其右边类的实例，返回 boolean 类型的数据。可以用来判断继承中的子类的实例是否为父类的实现。  

语法：`对象名 instanceof 类名`。
示例：
```java
Animal animal = new Dog();
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
}
```

## 抽象类及接口

### 抽象类

抽象类：在普通类的结构里面增加抽象方法的组成部分。  
抽象方法：没有方法体的方法。
- **抽象类和抽象方法必须用 abstract 关键字修饰。**
  ```java
  abstract class 类名 {}    // 抽象类
  public abstract void 方法名();    // 抽象方法
  ```  

- 抽象类不一定有抽象方法，有抽象方法的类一定是抽象类。

- 抽象类不能直接实例化，可以间接实例化：`抽象类类型 引用 = new 具体子类();`。  

- 抽象类的子类可以是抽象类，也可以是具体类。

抽象类的成员特点：  
- 构造方法：同普通类。

- 成员变量：同普通类。

- 成员方法：可以是抽象方法，也可以是非抽象方法。  
  一个不包含抽象方法的抽象类的意义：
  - 虽然不包含抽象方法，但是依然无法直接使用它（new 该类型的对象）。  
  - 如果别人使用你定义的抽象类，就必须自己定义一个子类继承抽象类。此时可能就会查看抽象类的定义，你在抽象类中的注释就会提示代码的使用者，哪些方法适用哪些场景。

注：代码中永远是通过多态调用子类覆盖抽象父类来使用 abstract 定义的抽象方法，而被 private，final，static 关键字修饰的方法都不能在子类中被覆盖，因此 private，final，static 这三个关键字不能和 abstract 共存。

### 接口

如果一个类只是由抽象方法和全局常量组成的，在这种情况下通常不会将其定义为一个抽象类，而是定义为接口。所以所谓的接口严格来讲就属于一个特殊的类，这个类里面只有抽象方法和全局常量。  
注：接口不是类！而是对类的一组需求描述，这些类要遵从接口描述的统一格式进行定义。  

接口用关键字 interface 定义，格式：`interface 接口名 {}`。  

在 Java 语言中 interface 也可以表示一种数据类型。
- 类和接口都可以用来表示数据类型（类和接口是地位对等的），只不过他们的侧重点不一样。

- 操作（行为）描述：  
  类定义的是一个数据集合基于这个数据集的一组操作（行为），类所描述的这一组行为，它们是有关系的（间接），都可以访问同一个数据集合。  

  接口表示数据类型，侧重于描述一组具有特殊功能的行为。这些行为可以完全没有任何关系。

类和接口可以有实现关系（类可以实现接口），这种实现关系其实是一种实质上的继承关系。  
类实现接口用 implements 表示，格式：`class 类名 implements 接口名 {}`。  

接口的原则：  
- 接口不能直接实例化。

- 接口的子类可以是抽象类也可以是具体类。

接口的特点：  
- 无构造方法。

- 成员变量：只能是常量，修饰符 public static final。

- 成员方法：只能是抽象方法，修饰符 public abstract。

- 接口与接口之间可以实现多重继承；  
  一个类也可以实现多个接口，一个考虑接口的比较完整的类定义语法：  
  ```java
  class 类名 extends 父类 implements 接口 1, 接口 2... {

  }
  ```

抽象类和接口的比较：
- 成员区别  
  抽象类：变量、抽象方法、非抽象方法；  
  接口：常量、抽象方法。  

- 关系区别  
  类与类：继承、单继承；  
  类与接口：实现、单实现、多实现；  
  接口与接口：继承、单继承、多继承。  

- 设计理念区别  
  抽象类：被继承体现的是共性功能。  
    - 抽象类可以被其他类继承，而且子类只能 extends 一个类
    - 抽象被子类继承之后，子类和抽象类的关系是「is a」。  
  
  接口：被实现体现的是扩展功能。  
    - 一个类可以同时多个接口。
    - 类实现接口之后，类和接口的关系用「like a」来描述。

从 JDK8 开始，接口中可以定义两种特殊的方法，这两种方法可以有方法体，默认的访问权限都是 public。
- 默认方法：它就是一种折中，通过添加默认方法的方式修改接口。不会对已经实现接口的其他类造成影响。

- 静态方法：作为工具方法来使用。  


## 内部类和匿名内部类

### 内部类

在 Java 语言中类可以嵌套定义。  
内部类：定义在其他类内部的类就称为内部类。约定把包含内部类的类，称之为外部类。  
注：内部类之所以存在，是为了方便外部类的操作。
示例：  
```java
public class TestDemo {

    public static void main(String[] args) {

        /*
        MemberOuter memberOuter = new MemberOuter();
        MemberOuter.MemberInner innerObj = memberOuter.new MemberInner();
        */
        MemberOuter.MemberInner innerObj = new MemberOuter().new MemberInner();
        innerObj.accessOuter();
        innerObj.innerMemberMethod();
    }

}

class MemberOuter {

    private void outerPrivateMethod() {
        System.out.println("外部方法");
    }

    class MemberInner {
        public void innerMemberMethod() {
            System.out.println("内部方法");
        }

        public void accessOuter() {
            outerPrivateMethod();
        }
    }
}

/* 运行结果：
外部方法
内部方法
*/
```
内部类的访问特点：  
- 内部类可以直接访问外部类的成员，包括私有。

- 外部类要访问内部类的成员，必须创建对象。

按照内部类在类中定义的位置不同，可以分为如下两种格式：  
- 成员位置（成员内部类）。

- 局部位置（局部内部类）。

成员内部类
- 成员内部类的定义位置：外部类的成员位置。  
  如果将内部类看做一个整体，对于外部类而言成员内部类就类似于一个成员变量或成员方法的一个普通成员。所以成员内部类在静态上下文中也无法访问非静态的成员。

- 成员内部类对象的实例化语法：`外部类.内部类 对象 = new 外部类().new 内部类();`。  

- 成员内部的常见修饰符：  
  - private：保证成员位置内部类只对其外部类可见
  
  - static：一旦被 static 修饰，那么整个成员内部类就有了静态的访问特征：  
    普通成员内部类依赖于外部类对象而存在。创建普通成员内部类的语法：`外部类.内部类 对象 = new 外部类().new 内部类();`。  
    静态成员内部类作为外部类的一个静态成员，不再依赖于外部类对象而存在。创建静态成员内部类的语法：`外部类.内部类 对象 = new 外部类.内部类();`

局部内部类
- 局部内部类的定义位置：方法体内。

- 局部内部类的特征：可以创建内部类对象，通过对象调用内部类方法，来使用局部内部类功能。  
  
  注：所以局部内部类可以访问方法体中局部变量。但是，被局部内部类对象访问的局部变量必须被 final 关键字修饰。  
  这是因为局部内部类对象与局部变量的生命周期冲突：  
  - 局部变量的生命周期，随着方法的执行结束，即栈帧销毁，其从内存中消失。

  - 局部内部类对象存储在堆上，对象的销毁和方法栈帧没有直接关系。  

  - 简单来说就是方法运行结束后，局部变量不存在了，但是对象还在。因此要想继续正常使用局部内部类对象，就要将被其访问的局部变量用 final 关键字修饰。


### 匿名内部类

不管是成员或局部位置内部类，要使用内部类都分成了 2 步：  
1. 定义内部类；

2. 创建内部类对象

通过定义匿名内部类对象，可以将上面的 2 步变为 1 步。前提：存在一个类（可以是具体类也可以是抽象类）或者接口。    

匿名内部类语法：
```
new 类名或者接口名() {
  方法覆盖
}
```
示例：
```java
interface Print {  // 定义一个接口
    public void print();
}

public class TestDemo {

    public static void main(String[] args) {
        new Print() {
            @Override
            public void print() {
                System.out.println("HelloWorld");
            }
        }.print();
    }
}

/* 运行结果：
HelloWorld
*/
```

本质：是一个继承了类或者实现了接口的子类匿名对象。

特征：匿名内部类对象只能在被创建的时候被访问一次。

## 常见类

### Object类

Object 类是所有类的父类，也就是说任何一个类在定义时如果没有明确地继承一个父类，那它就是 Object 类的子类。  
- 每个类都使用 Object 作为超类。  
  在设计代码时，如果不确定参数类型，可以使用 Object 类。因为它可以通过向上转型来接收全部类的对象。  

- 所有对象（包括数组）都有这个类的方法。

构造方法：
- `public Object()`：因此所有类都会有一个默认的无参构造方法。

常见成员方法：
- `public final Class getClass()`：返回此 Object 的运行时类。返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。  
  Class 对象代表一个类，表示的就是所有类的共性：
  - 构造方法。
  - 成员变量。
  - 成员方法。

- `public String toString()`：返回该对象的字符串表示。  
  通常 toString 方法会返回一个「以文本方式表示」此对象的字符串。结果应是一个简明但易于读懂的信息表达式。  
  建议所有子类都重写此方法。  
  
  该字符串由类名（对象是该类的一个实例）、标记符（@）和此对象哈希码的无符号十六进制表示组成。换句话说，该方法返回一个字符串，它的值等于：`getClass().getName() + '@' + Integer.toHexString(hashCode())`。

- `public boolean equals(Object obj)`：指示其他某个对象是否与此对象「相等」。  
  equals 方法用于检查一个对象是否等于另一个对象。在 Object 类中，这个方法将判断两个对象是否具有相同的引用。   
  格式：`对象.equals(比较对象)`。  

  equals 方法在非空对象引用上实现相等关系： 
  - 自反性：对于任何非空引用值 x，`x.equals(x)` 都应返回 true。 

  - 对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，`x.equals(y)` 才应返回 true。 

  - 传递性：对于任何非空引用值 x、y 和 z，如果 `x.equals(y`) 返回 true，并且 `y.equals(z)` 返回 true，那么 `x.equals(z)` 应返回 true。 

  - 一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。 

  - 对于任何非空引用值 x，`x.equals(null)` 都应返回 false。 

- `public int hashCode()`：取得对象哈希码。  
  支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。   

  hashCode 的常规协定是： 
  - 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。 

  - 如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。 

  - 如果根据 equals(Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是 coder 应该意识到为不相等的对象生成不同整数结果可以提高哈希表的性能。 

  实际上，由 Object 类定义的 hashCode 方法确实会针对不同的对象返回不同的整数。这一般是通过将该对象的内部地址转换成一个整数来实现的，但是 Java 编程语言不需要这种实现技巧。  

- `protected Object clone()`：创建并返回此对象的一个副本。「副本」的准确含义可能依赖于对象的类。  
  对于任何对象 x，表达式：
  - `x.clone() != x` 为 true。  
    说明 clone 创建了一个新的对象。

  - `x.clone().getClass() == x.getClass()` 为 true。  
    说明 clone 创建的对象和原对象是同一个类的对象。

  - `x.clone().equals(x)` 为 true。  
    说明复制对象和原对象的内容（成员变量值）也相同。  

  注：被 clone() 方法复制的对象，所属的类必须实现一个接口 Cloneable。  

  Cloneable 接口是个空接口。
  ```java
  public interface Cloneable {}
  ```
  空接口也被称之为标记接口：做标记（数据类型层面的标记）。对于 clone 方法而言，Cloneable 就是一个标记，因为 clone 只会复制，实现类了 Cloneable 接口的类的对象。  
  利用 instanceof 运算符判断：`对象  instanceof  Cloneable`。

  浅拷贝（Shallow Clone）：被复制对象的所有变量都含有与原来对象相同的值，而所有对其它对象的引用仍然指向原来的对象。换而言之，浅拷贝仅仅复制所考虑的对象，而不复制它所引用的对象。  

  深拷贝（Deep Clone）：被复制对象的所有变量都含有与原来对象相同的值，除去那些引用其它对象的变量。那些引用其它对象的变量将指向被复制的新对象，而不再是原有的那些被引用的对象。换而言之，深拷贝把复制的对象所引用的对象都复制了一遍。  

- `protected void finalize()`：当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。  
  子类重写 finalize 方法以配置系统资源或执行其他清除。  

  finalize 的常规协定是：当 JVM 已确定尚未终止的任何线程无法再通过任何方法访问此对象时，将调用此方法，除非由于准备终止的其他某个对象或类的终结操作执行了某个操作。

  finalize 方法可以采取任何操作，其中包括再次使此对象对其他线程可用；不过，finalize 的主要目的是在不可撤消地丢弃对象之前执行清除操作。
  比如：当我们要执行一些 IO 或者是网络通信的功能的时候，JVM 是借助操作系统的内核完成的，所以执行这些功能时，Java 程序需要占用一定的操作系统资源，当程序使用完操作系统资源的时候，即时释放资源。当使用资源的对象变成垃圾，才能安全的释放系统资源。  
  finalize 方法刚刚好就是在对象变成垃圾，并且被垃圾回收器回收的时候会调用这个方法。但是回收时机不确定。


### String类

Java 没有内置的字符串类型，而是标准 Java 类库中提供了一个预定义类 String。每个用双引号括起来的字符串都是 String 类的一个实例。  

常用构造方法：
- `public String()`：初始化一个新创建的 String 对象，使其表示一个空字符序列 `""`。

- `public String(byte[] bytes)`：利用字节数组，创建字节数组所表示的字符串。  
  字符是以数值形式存储（'a' -> 97），所以可以用多个字节值，表示多个字符，即字符序列。

- `public String(byte[] bytes,int offset,int length)`：利用字节数数组的一部分，创建字符序列，从 byte 数组的 offset 开始的 length 个字节值。

- `public String(char[] value)`：利用一个字符数组创建字符串。

- `public String(char[] value,int offset,int count)`：创建字符数组从第 offset 位置开始的 count 个字符所代表的字符串对象。

- `public String(String original)`：初始化一个新创建的 String 对象，使其表示一个与参数相同的字符序列；换句话说，新创建的字符串是该参数字符串的副本。

注：字符串是常量，它的值在创建之后不能更改。

String 类的判断功能：  
- `boolean equals(Object obj)`：比较字符串内容。

- `boolean equalsIgnoreCase(String str)`：比较字符串内容，但是忽略字符串大小写。

- `boolean contains(String str)`：判断当前字符串中是否包含，参数字符串。


- `boolean startsWith(String str)`：判断字符串是否以参数字符串开头。

- `boolean endsWith(String str)`：判断字符串是否以参数字符串结尾。

- `boolean isEmpty()`：判断字符串是否为空串
`

String 类的的获取功能：  
- `int  length()`：返回当前字符串中包含的字符个数。

- `char charAt(int index)`：获取字符串指定位置的字符（字符串中的字符，从左到右，从 0 开始编号）。

- `int indexOf(int ch)`：在当前字符串中，查找参数字符，如果当前字符串中存在，则返回首次出现的位置，若不存在则返回 -1。

- `int indexOf(String str)`：在当前字符串中，查找参数字符串首次出现的位置。找到，则返回参数字符串首次出现的首字母的位置，否则返回 -1。

- `int indexOf(int ch, int fromIndex)`：指定从字符串的 fromIndex 位置开始，向后查找指定字符。找到，则返回其从 formIndex 开始首次出现的位置，否则返回 -1。

- `int indexOf(String str, int fromIndex)`：指定从字符串的 fromIndex 位置开始，向后查找指定字符串。找到，则返回其从 formIndex 开始首次出现的位置，否则返回 -1。

- `String substring(int start)`：生成当前字符串的子串，字串的取值是原字符串的 [start, length()-1]。

- `String substring(int start, int end)`：生成当前字符串的子串，字串的取值是原字符串的 [start, end)。

String 类的的转换功能：
- `byte[] getBytes()`： 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。

- `char[] toCharArray()`：将此字符串转换为一个新的字符数组。

- `static String valueOf(char[] chs)`：返回 char 数组参数的字符串表示形式。

- `static String valueOf(int i)`：返回 int 参数的字符串表示形式。

- `String toLowerCase()`：使用默认语言环境的规则将此 String 中的所有字符都转换为小写。

- `String toUpperCase()`：使用默认语言环境的规则将此 String 中的所有字符都转换为大写。

- `String concat(String str)`：将指定字符串连接到此字符串的结尾。

String 类的替换功能：  
- `String replace(char oldChar, char newChar)`：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。

- `String replace(CharSequence target, CharSequence replacement)`：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。


String 类去除空字符串：  
- `String trim()`：返回字符串的副本，忽略前导空白和尾部空白。 

String 类的比较功能：
- `int compareTo(String str)`： 按字典顺序比较两个字符串。  
  返回值：如果参数字符串等于此字符串，则返回值 0；如果此字符串按字典顺序小于字符串参数，则返回一个小于 0 的值；如果此字符串按字典顺序大于字符串参数，则返回一个大于 0 的值。

- `int compareToIgnoreCase(String str)`：按字典顺序比较两个字符串，不考虑大小写。


### StringBuffer类

StringBuffer：线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，但**通过某些方法调用可以改变该序列的长度和内容**。  

可将字符串缓冲区安全地用于多个线程。可以在必要时对这些方法进行同步，因此任意特定实例上的所有操作就好像是以串行顺序发生的，该顺序与所涉及的每个线程进行的方法调用顺序一致。 

构造方法：  
- `public StringBuffer()`：构造一个其中不带字符的字符串缓冲区，其初始容量为 16 个字符。

- `public StringBuffer(int capacity)`：构造一个不带字符，但具有指定初始容量的字符串缓冲区。

- `public StringBuffer(String str)`：构造一个字符串缓冲区，并将其内容初始化为指定的字符串内容。该字符串的初始容量为 16 加上字符串参数的长度。 

示例：
```java
public class TestDemo {
    public static void main(String[] args) {
        // public StringBuffer()
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.capacity());
        System.out.println(stringBuffer.length());

        // public StringBuffer(int capacity)
        stringBuffer = new StringBuffer(20);
        System.out.println(stringBuffer.capacity());
        System.out.println(stringBuffer.length());

        // public StringBuffer(String str)
        stringBuffer = new StringBuffer("HelloWorld");
        System.out.println(stringBuffer.capacity());
        System.out.println(stringBuffer.length());
    }
}

/* 运行结果：
16
0
20
0
26
10
*/
```

添加功能：
- `public StringBuffer append(String str)`：将指定的字符串追加到此字符序列。

- `public StringBuffer insert(int offset, String str)`：将字符串插入此字符序列中。

示例：  
```java
public class TestDemo {

    public static void main(String[] args) {

        StringBuffer stringBuffer = new StringBuffer("start：");
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // append
        String s1 = "12345";
        stringBuffer.append(s1);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        int i = 6;
        stringBuffer.append(i);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        boolean b = true;
        stringBuffer.append(b);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // insert
        String s2 = "0";
        stringBuffer.insert(6, s2);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // 链式调用
        stringBuffer.insert(13, '7')
                .append("ABC")
                .append(false);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());
    }
}

/* 运行结果：
start：
capacity：22
start：12345
capacity：22
start：123456
capacity：22
start：123456true
capacity：22
start：0123456true
capacity：22
start：01234567trueABCfalse
capacity：46
*/
```

注：
- 不管任何数据类型都可以被添加到字符缓冲区中，如果该类型不是字符串类型会自动转换为字符串类型。

- 虽然 StringBuffer 可以自动扩容，但是在开发中如果能有效的预估字符缓冲区所需的长度，建议使用 `public StringBuffer(int capacity)` ，因为每一次扩容都比较耗时。

删除功能：

- `public StringBuffer deleteCharAt(int index)`：移除此序列指定位置的 char。。

- `public StringBuffer delete(int start, int end)`。

替换功能：
- `public StringBuffer replace(int start, int end, String str)`：使用给定 String 中的字符替换此序列的子字符串中的字符。

示例：  
```java
public class TestDemo {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("012345");
        StringBuffer abcd = stringBuffer.replace(0, 2, "abcd");
        System.out.println(abcd.toString());
    }
}

/* 运行结果：
abcd2345
*/
```

反转功能：
- `public StringBuffer reverse()`：将此字符序列用其反转形式取代。

截取功能：
- `public String substring(int start)`：返回一个新的 String，它包含此字符序列当前所包含的字符子序列。

- `public String substring(int start, int end)`。

 从 JDK 5 开始，为该类（StringBuffer）补充了一个单个线程使用的等价类 StringBuilder。
- StringBuilder 针对单线程运行环境，它的 api 和 StringBuffer 几乎没有差别。

- StringBuffer 针对多线程运行环境。


### Date类

类 Date 表示特定的瞬间，精确到毫秒。 

构造方法：
- `Date()`：分配 Date 对象并初始化此对象，以表示分配它的时间（精确到毫秒）。  

- `Date(long date)`：分配 Date 对象并初始化此对象，以表示自从标准基准时间（称为「历元（epoch）」，即 1970 年 1 月 1 日 00:00:00 GMT）以来的指定毫秒数。

常见方法：
- `public long getTime()`：返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

示例：  
```java
import java.util.Date;

public class TestDemo {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("当前：" + date);

        long time = date.getTime();
        System.out.println("此时距离历元：" + time + " 秒");

        date = new Date(date.getTime() + 24 * 60 * 60 * 1000L);   // 一天后
        System.out.println("一天后：" + date);
    }
}

/* 运行结果：
当前：Sun May 10 21:12:28 CST 2020
此时距离历元：1589116348785 秒
一天后：Mon May 11 21:12:28 CST 2020
*/
```


### DateFormat类

DateFormat 类：
- 是日期 / 时间格式化子类的抽象类。  

- 它以与语言无关的方式格式化并解析日期或时间。

- 因为是抽象类，所以实际使用的是 SimpleDateFormat 这个实现子类。

构造方法：
- `SimpleDateFormat()`：用默认的模式和默认语言环境的日期格式符号构造 SimpleDateFormat。

- `SimpleDateFormat(String pattern)`：用给定的模式和默认语言环境的日期格式符号构造 SimpleDateFormat。  
  - y：年。
  - M：表示年中的月份。
  - d：表示月份中的天数。
  - H：表示一天中的小时数。
  - m：小时中的分钟。
  - s：分钟中的秒数。

常用方法：
- `public Date parse(String source)`：解析字符串的文本，生成 Date。如果发生错误，则返回 null。  

- `public final String format(Date date)`：把一个 Date 对象表示成一个指定格式的表示时间的字符串。

示例：  
```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println("默认：" + date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("格式处理：" + simpleDateFormat.format(date));

        date = simpleDateFormat.parse("2020/01/10 17:06:00");
        System.out.println("解析字符串：" + date);
    }
}

/* 运行结果：
默认：Sun May 10 21:48:57 CST 2020
格式处理：2020/05/10 21:48:57
解析字符串：Fri Jan 10 17:06:00 CST 2020
*/
```

### Math类

Math 类包含用于执行基本数学运算的方法，如初等指数、对数、平方根和三角函数。 

常见方法：
- `public static int abs(int a)`：求绝对值。

- `public static double ceil(double a)`：向上取整。

- `public static double floor(double a)`：向下取整。 

- `public static int max(int a, int b)`：返回两个 int 值中较大的一个。

- `public static double pow(double a, double b)`：返回第一个参数的第二个参数次幂的值。

- `public static double random()` // 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0 [0.0 1.0) 随机数。

- `public static int round(float a)`：返回最接近参数的 int。

- `public static double sqrt(double a)`：返回正确舍入的 double 值的正平方根。