# 1.函数式编程

给定一个元素列表，如下所示：

```java
["1","2","bilibili","of","codesheep","5","at","BILIBILI","codesheep","23","CHEERS","6"]
```

里面有数字型字符串，有字母型字符串；字符串里有大写，也有小写；字符串长度也有长有短。

现在要写代码完成**一个小功能**：

我想找出所有 **长度>=5**的字符串，并且**忽略大小写**、**去除重复字符串**，然后**按字母排序**，最后**用“爱心❤”连接成一个字符串**输出！

正常实现：

**1、先写判断，判断该字符串是字母还是数字：**

```
public static boolean isNum(String str){
	for(int i=0;i<str.length();i++){
		if(!Character.isDigit(str.charAt(i))){
			return false;
		}
	}
	return true;
}
```

2、**接下来进行操作**

```java
// 先定义一个具备按字母排序功能的Set容器，Set本身即可去重
Set<String> stringSet = new TreeSet<String>(
	new Comparator<String>(){
        @Override
        public int Compare(String o1,String o2){
            return o1.compareTo(o2);//按字母的顺序排列
        }
    }
);

// 以下for循环完成元素去重、大小写转换、长度判断等操作
for(int i=0;i<list.size();i++){
    String s = list.get(i);
    if(!isNum(s) && s.length()>=5){
        String slower = s.toLowerCase();//转换成小写
        stringSet.add(slower);
    }
}

//for循环完成连接成句
StringBulider result = new StringBulider();
for(String s : stringSet){
    result.append(s);
    result.append("❤")；//用爱心连接字符串
}
String finalResult = result.subString(0,result.length()-1).toString();
System.out.println(finalResult);
```

输出结果：

```
bilibili❤cheers❤codesheep
```

而且我现在是一看到**for循环**遍历，我头就痛，上面代码倒还好，假如列表层级变复杂，**俄罗斯套娃式的for循环** 谁扛得住。

所以自Java 8开始，引入了函数式编程范式，这对于咱这种**底层劳动密集型码畜**来说，简直解放了双手，代码几乎少写一半，从此真正实现**编码5分钟，划水2小时**！

针对上面的作业，用Java 8的 `Stream`流式操作，仅需**一行代码**就可以搞定，for循环啥的统统灰飞烟灭。

```java
String result = list.stream()//首先将列表转化为stream流
    			.filter(i -> !isNum(i))//筛选出字母型字符
    			.filter(i -> i.length()>=5)//筛选出大于等于5的字母型字符串
    			.map(i-> i.toLowerCase())//转小写
    			.distinct()	//去重
    			.sorted(Comparator.naturalOrder()) //排序
    			.collect(Collectors.joining("❤"));//连成句
System.out.println(result);
```

上面其实已经通过举栗的方式阐述了Java 8函数式编程范式：**Stream流** 的优雅和强大，尤其在处理集合时，几本一步到位，嘎嘣脆。

当然**Stream**也仅仅只是Java 8函数式编程接口的一个而已，除了Stream接口，还有其他非常强大的函数式编程接口，比如：

- **Consumer接口**
- **Optional接口**
- **Function接口**

每个接口我们都来举一个好理解的例子，看完保证你难以拒绝！

## **一、Consumer接口**

顾名思义，它是“消费者的含义”，接受参数而不返回值，举个最最常见的栗子：

平时我们打印字符串，本质也是接受一个参数并打印出来，我们一般想都不想，会这样写：

```java
System.out.println("hello world");     // 打印 hello world
System.out.println("hello codesheep"); // 打印 hello codesheep
System.out.println("bilibili cheers"); // 打印 bilibili cheers
```

一旦你用了 `Consumer`之后，总感觉更加优雅一些

```java
Consumer c = System.out::println;
  	c.accept("hello world");      // 打印 hello world
	c.accept("hello codesheep");  // 打印 hello codesheep
	c.accept("bilibili cheers");  // 打印 bilibili cheers
```

而且 `Consumer`还可以用联用，达到多重处理的效果，比如：

```java
c.andThen(c).andThen(c).accept("hello world"); // 会连续打印 3次：hello worl
```

## **二、Function接口**

`Function`接口代表的含义是“函数”，其实和上面的 `Consumer`有点像，不过 `Function`既有输入，也有输出，使用更加灵活，举例：

比如我想对一个整数先乘以 `2`，再计算平方值

```java
Function<Integer,Integer> f1 = i -> i+i;  // 乘以2功能
Function<Integer,Integer> f2 = i -> i*i;  // 平方功能
Consumer c = System.out::println;         // 打印功能
c.accept( f1.andThen(f2).apply(2) );      // 三种功能组合：打印结果 16
```

## **三、Optional接口**

`Optional`本质是个容器，你可以将你的变量交由它进行封装，这样我们就不用显式对原变量进行 `null`值检测，防止出现各种空指针异常。举例：

我们想写一个获取学生某个课程考试分数的函数：`getScore()`

```java
public Integer getScore( Student student ) {
    if( student != null ) {      // 第一层 null判空
        Subject subject = student.getSubject();        
	if( subject != null ) {  // 第二层 null判空
            return subject.score;        
    }    
}    
return null;
}
```

这样写倒不是不可以，但我们作为一个**“严谨且良心的”**后端工程师，这么多嵌套的 if 判空多少有点扎眼！

为此我们必须引入 `Optional`：

```java
public Integer getScore( Student student ) {
    return Optional.ofNullable(student)            
		.map( Student::getSubject )            
		.map( Subject::getScore )            
		.orElse(null);
}
```

2022/11/ 5 下午5.

***

***

# 2.参数校验

## **简单业务场景模拟：**

假如你现在在做一个成绩录入系统，你愉快地用**Spring Boot框架**写了一个后台接口，用于接收前台浏览器传过来的 `Student`对象，并插入后台数据库。

我们将传入的 `Student`对象定义为：

```java
public class Student{
	private String name;
	private Integer score;
	private String mobile;
}
```

然后写一个**Post请求**的后台接口，来接收网页端传过来的 `Student`对象：

```java
@RestController
public class TestStudent{
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/add")
    public String addStudent(@ResquestBody Student student){
        studentService.addStudent(student);
        return "SUCCESS";
    }
}
```

此时我想你一定看出来了上面这段**代码的漏洞**，因为我们并没有对传入的 `Student`对象做任何**数据校验**，比如：

`Student`对象里三个字段的某一个忘传了，为 `null`怎么办？`Student`的 `score`分数，假如写错了，写成 `101`分怎么办？`Student`的 `mobile`11位手机号码，假如填错了，多写了一位怎么办？...等等

这些数据虽然在前端页面一般会做校验，但我们作为一个**严谨且良心**的后端开发工程师，我们肯定要对传入的每一项数据做**严格的校验**，所以我们应该怎么写？

```java
@PostMapping("/add")
public String addStudent (@RequestBody Student student){
	if(student == null){
		return "传入的Student对象为null，请传值";
	}
	if(student.getName() == null || "".equals( student.getName() )){
		return "传入的名字不能为空";
	}
	if(student.getScore()==null){
		return "传入的学生成绩为null，请传值";
	}
	if(student.getScore()<0 || student.getScore()>100){
		return "传入的学生成绩有误，分数应该在0~100之间";
	}
	if(student.getMobile()==null || "".equals(student.getMobile())){
		return "传入的学生电话号码为空，请传值";
	}
	if(student.getMobile().length() != 11){
		return "传入的学生电话号码长度有误，应为11位";
	}
	studentService.addStudent(student);
	return "SUCCESS";
}
```

写是写完了，就是感觉**手有点酸**，并且**心有点累**，这个 `Student`对象倒还好，毕竟内部仅3个字段，假如一个复杂的对象有30个字段怎么办？简直不敢想象！

![](F:\Users\dell\Pictures\Camera Roll\心痛.png)

其实Spring框架很早版本开始，就通过**注解的方式**，来方便地为我们提供了各项交互**数据的校验**工作，比如上面的例子，我们只需要在传入的 `Student`实体类的字段中加入对应注解即可方便的解决问题：                                      

```java
public class Student{
	@NotNull(message = "传入的姓名为null，请传值")
	@NotEmpty(message = "传入的姓名为空字符串，请传值")
	private String name;
	
	@NotNull(message ="传入的成绩为null，请传值")
	@Min(value = 0,message = "传入的学生成绩有误，分数应该在0~100之间")
	@Max(value = 100, message = "传入的学生成绩有误，分数应该在0~100之间")
	private Integer score;
	
	@NotNull(message = "传入的手机号为null，请传值")
	@NotEmpty(message = "传入的手机号为空字符串，请传值")
	@Length(min = 11, max = 11, message = "传入的手机号有误，必须为11位")
	private String mobile;
}
```

当然，于此同时，我们还需要在对象入口处，加上注解 `@Valid`来开启对传入 `Student`对象的验证工作：

```java
@RestController
public class TestStudent{
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/add")
    public String addStudent(@ResquestBody @Valid Student student){
        studentService.addStudent(student);
        return "SUCCESS";
    }
}
```

这时候，如果某个字段传入错误，比如我传数据的时候，将学生的成绩误传为 `101`分，则接口返回结果便会提示出错误详情：

![](F:\Users\dell\Pictures\Camera Roll\参数校验返回示例.png)

当然，关于这个事情的原理，既然用到了注解，无非用的也就是Java里的各种反射等知识来实现的，感兴趣的小伙伴可以借此机会研究一下！

## 数据异常统一拦截

上面利用注解的方式做统一数据校验**感觉十分美好**，但唯一美中不足的就是返回的**结果太过繁杂**，不一定使我们需要的格式，我们需要做**统一处理**，比如：我只想将具体参数校验的错误提示信息给**抠出来**返回给前端即可。

![](F:\Users\dell\Pictures\Camera Roll\嘿嘿嘿.png)

为此，我们为项目配置**全局统一异常拦截器**来格式化所有数据校验的返回结果。

```java
@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor(){
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request,Exception e){
        String failMsg = null;
        if(e instanceof MethodArgumentNotValidException){
            // 拿到参数校验具体异常信息提示
            failMsg = ((MethodArgumentNotValidException) e)
                		.getBindingResult()
                		.getFieldError()
                		.getDefaultMessage();
        }
        return failMsg;	// 直接吐回给前端
    }
}
```

如上面代码所示，我们**全局统一拦截了**参数校验异常 `MethodArgumentNotValidException`，并仅仅只拿到对应异常的详细 `Message`信息吐给前端，此时返回给前端的数据就清楚得多：

![](F:\Users\dell\Pictures\Camera Roll\全局异常处理.png)

可以的，非常优雅！

每天进步一点点！Peace！

2022/11/7	早10.30

***

***

# 3.空指针异常

关于 `null`空指针异常问题的预防和解决，详细整理成文，并严格反思：**我们到底在代码中应该如何防止空指针异常所导致的Bug？**					

## <1>常见的输入判空

对输入判空非常有必要，并且常见，举个栗子：

```java
public String addStudent(Student student){
	//...
}
```

无论如何，你在进行函数内部业务代码编写之前一定会对传入的 `student`对象本身以及每个字段进行判空或校验：

```java
@PostMapping("/add")
public String addStudent (@RequestBody Student student){
	if(student == null){
		return "传入的Student对象为null，请传值";
	}
	if(student.getName() == null || "".equals( student.getName() )){
		return "传入的名字不能为空";
	}
	if(student.getScore()==null){
		return "传入的学生成绩为null，请传值";
	}
	if(student.getScore()<0 || student.getScore()>100){
		return "传入的学生成绩有误，分数应该在0~100之间";
	}
	if(student.getMobile()==null || "".equals(student.getMobile())){
		return "传入的学生电话号码为空，请传值";
	}
	if(student.getMobile().length() != 11){
		return "传入的学生电话号码长度有误，应为11位";
	}
	studentService.addStudent(student);
	return "SUCCESS";
}
```

为了避免**人肉手写**这种繁杂的输入判空，我们最起码可以用两种方式来进行**优雅的规避**：

### 方法一:运用springboot自带的注解校验

### 方法二:借助lombok工具的注解@NotNull

***

## <2>手动空指针保护

手动进行 `if(obj!=null)`的判空自然是最全能的，也是最可靠的，但是怕就怕**俄罗斯套娃式**的 `if`判空。

举例一种情况：

为了获取：`省(Province)→市(Ctiy)→区(District)→街道(Street)→道路名(Name)`

作为一个**“严谨且良心”**的后端开发工程师，如果手动地进行空指针保护，我们难免会这样写：

```java
public String getStreetName(Province province){
	if(province != null){
		City city = province.getCity();
        if(city != null){
            District district = city.getDistrict();
            if(district != null){
                Street street = district.getStreet();
                if(street != null){
                    return street.getName();
                }
            }
        }
	}
    return "未找到指定街道名";
}
```

为了获取到链条最终端的目的值，直接**链式取值**必定有问题，因为中间只要某一个环节的对象为 `null`，则代码一定会炸，并且抛出 `NullPointerException`异常，然而俄罗斯套娃式的 `if`判空实在有点心累。

### 消除俄罗斯套娃式判空

我们也可以利用Java的函数式编程接口 `Optional`来进行优雅的判空！

`Optional`接口本质是个容器，你可以将你可能为 `null`的变量交由它进行托管，这样我们就不用显式对原变量进行 `null`值检测，防止出现各种空指针异常。

Optional语法专治上面的**俄罗斯套娃式 if 判空**，因此上面的代码可以重构如下：

```java
public String getStreetName(Province province){
    return Optional.ofNullable(province)
        .map(i -> i.getCity())
        .map(i -> i.getDistrict())
        .map(i -> i.getStreet())
        .map(i -> i.getName())
        .orElse("未找到指定街道名");
}
```

漂亮！嵌套的 `if/else`判空灰飞烟灭！

**解释一下执行过程：**

- `ofNullable(province )` ：它以一种智能包装的方式来构造一个 `Optional`实例， `province`是否为 `null`均可以。如果为 `null`，返回一个单例空 `Optional`对象；如果非 `null`，则返回一个 `Optional`包装对象
- `map(xxx )`：该函数主要做值的转换，如果上一步的值非 `null`，则调用括号里的具体方法进行值的转化；反之则直接返回上一步中的单例 `Optional`包装对象
- `orElse(xxx )`：很好理解，在上面某一个步骤的值转换终止时进行调用，给出一个最终的默认值

当然实际代码中倒很少有这种极端情况，不过普通的 `if(obj!=null)`判空也可以用 `Optional`语法进行改写，比如很常见的一种代码：

```java
List<User> userList = userMapper.queryUserList(userType);
if(userList != null){
    for(User user:userList){//此处免不了对userList进行判空
        // ...
    	// 对user对象进行操作
    	// ...
    }
}
```

如果用 `Optional`接口进行改造，可以写为：

```java
List<User> userList = userMapper.queryUserList(userType);
Optional.ofNullable(userList).ifPresent(
    list ->{
        for(User user:list){
            // ...
      		// 对user对象进行操作
      		// ...
        }
    }
)
```

这里的 `ifPresent()`的含义很明显：仅在前面的 `userList`值不为 `null`时，才做下面其余的操作。

没有用过 `Optional`语法肯定感觉上面的写法**非常甜蜜**！然而褪去华丽的外衣，甜蜜的 `Optional`语法底层依然是朴素的语言级写法，比如我们看一下 `Optional`的 `ifPresent()`函数源码，就是普通的 `if`判断而已：

![](F:\Users\dell\Pictures\Camera Roll\ifPresent源码.png)

那就有人问：**我们何必多此一举**，做这样一件无聊的事情呢？

其实不然！

用 `Optional`来包装一个可能为 `null`值的变量，其最大意义其实仅仅在于给了调用者一个**明确的警示**！

怎么理解呢？

比如你写了一个函数，输入学生学号 `studentId`，给出学生的得分 ：

```java
Score getScore(Long studentId){
	//...
}
```

调用者在调用你的方法时，一旦忘记 `if(score!=null)`判空，那么他的代码肯定是有一定 `bug`几率的。

但如果你用 `Optional`接口对函数的返回值进行了包裹：

```java
Optional<Score> getScore(Long studentId){
    //...
}
```

这样当调用者调用这个函数时，他可以清清楚楚地看到 `getScore()`这个函数的返回值的特殊性（有可能为 `null`），这样一个警示一定会很大几率上帮助调用者规避 `null`指针异常。

## <3>老项目的空指针判空处理

上面所述的 `Optional`语法只是在 `JDK1.8`版本后才开始引入，那还在用 `JDK1.8`版本之前的老项目怎么办呢？

没关系！

`Google`大名鼎鼎的 `Guava`库中早就提供了 `Optional`接口来帮助优雅地处理 `null`对象问题，其本质也是在可能为 `null`的对象上做了一层封装，使用起来和JDK本身提供的 `Optional`接口没有太大区别。

你只需要在你的项目里引入 `Google`的 `Guava`库：

```java
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
</dependency>
```

即可享受到和 `Java8`版本开始提供的 `Optional`一样的待遇！

2022/11/7 	早11.

***

***

# 4.Object类相关

## <1>概览

**Object是java所有类的基类，是整个类继承结构的顶端，也是最抽象的一个类**。大家天天都在使用 `toString()、equals()、hashCode()、wait()、notify()、getClass()`等方法，或许都没有意识到是 `Object`的方法，也没有去看 `Object`还有哪些方法以及思考为什么这些方法要放到 `Object`中。本篇就每个方法具体功能、重写规则以及自己的一些理解。

## <2>**Object类所有方法详解**

`Object`中含有： `registerNatives()、getClass()、hashCode()、equals()、clone()、toString()、notify()、notifyAll()、wait(long)、wait(long,int)、wait()、finalize()` 共**十二个方法**。这个顺序是按照 `Object`类中定义方法的顺序列举的，下面我也会按照这个顺序依次进行讲解。

- `registerNatives()`

```java
public class Object{
    private static native void registerNatives();
    static{
        registerNatives();
    }
}
```

**从名字上理解，这个方法是注册 native方法**（本地方法，由 `JVM`实现，底层是 `C/C++`实现的）**向谁注册呢？当然是向 JVM**，当有程序调用到 `native`方法时， `JVM`才好去找到这些底层的方法进行调用。

`Object`中的 `native`方法，并使用 `registerNatives()`向 `JVM`进行注册。（这属于 `JNI`的范畴，有兴趣的可自行查阅。）

![](F:\Users\dell\Pictures\Camera Roll\jni.jpg)

> 为什么要用静态方法，还要放在静态块中呢？

我们知道了在类初始化的时候，会依次从父类到本类的类变量及类初始化块中的类变量及方法按照定义顺序放到         `< clinit>`方法中，这样可以保证父类的类变量及方法的初始化一定先于子类。所以当子类调用相应 `native`方法，比如计算 `hashCode`时，一定可以保证能够调用到 `JVM`的 `native`方法。

- **`getClass()`**

```java
public final native Class getClass();
```

这是一个 `public`的方法，我们可以直接通过对象调用。

类加载的第一阶段类的加载就是将 `.class`文件加载到内存，并生成一个 `java.lang.Class`对象的过程。 `getClass()`方法就是获取这个对象，这是当前类的对象在运行时类的所有信息的集合。这个方法是反射三种方式之一。

- **hashCode()**

```java
public native int hashCode();
```

这是一个 `public`的方法，所以子类可以重写它。这个方法返回当前对象的 `hashCode`值，这个值是一个整数范围内的 `（-2^31~2^31-1）`数字。

对于 `hashCode`有以下几点约束:

1. 在 Java应用程序执行期间，在对同一对象多次调用 `hashCode` 方法时，必须一致地返回相同的整数，前提是将对象进行 `equals` 比较时所用的信息没有被修改；
2. 如果两个对象 `x.equals(y)` 方法返回 `true`，则 `x`、 `y`这两个对象的 `hashCode`必须相等。
3. 如果两个对象 `x.equals(y)` 方法返回 `false`，则 `x`、 `y`这两个对象的 `hashCode`可以相等也可以不等。但是，为不相等的对象生成不同整数结果可以提高哈希表的性能。
4. 默认的 `hashCode`是将内存地址转换为的 `hash`值，重写过后就是自定义的计算方式；也可以通过 `System.identityHashCode(Object)`来返回原本的 `hashCode`。

```java
public class HashCodeTest(){
    private int age;
    private String name;
    
    @Override
    public int hashCode(){
        Object[] a = Stream.of(age,name).toArray();
        int result = 1;
        for(Object element : a){
            result = 31*result+(element == null ? 0 : element.hashCode());
        }
        return result;
    }
}
```

推荐使用 `Objects.hash(Object…values)`方法。相信看源码的时候，都看到计算 `hashCode`都使用了 `31`作为基础乘数，为什么使用 `31`呢？我比较赞同与理解 `result*31=(result<<5)-result`。 `JVM`底层可以自动做优化为位运算，效率很高；还有因为 `31`计算的 `hashCode`冲突较少，利于 `hash`桶位的分布。

- > **`equals()`**

```java
public boolean equals(Object obj);
```

用于比较当前对象与目标对象是否相等，默认是比较引用是否指向同一对象。为 `public`方法，子类可重写。

```java
public class Object{
    public boolean equals(Object obj){
        return (this == obj);
    }
}
```

>为什么需要重写 `equals`方法？

**因为如果不重写equals方法，当将自定义对象放到 map或者 set中时**；如果这时两个对象的 `hashCode`相同，就会调用 `equals`方法进行比较，这个时候会调用 `Object`中默认的 `equals`方法，而默认的 `equals`方法只是比较了两个对象的引用是否指向了同一个对象，显然大多数时候都不会指向，这样就会将重复对象存入 `map`或者 `set`中。这就**破坏了 map与 set不能存储重复对象的特性，会造成内存溢出**。

**重写 equals方法的几条约定：**

1. **自反性**：即 `x.equals(x)`返回 `true`， `x`不为 `null`；
2. **对称性**：即 `x.equals(y)`与 `y.equals(x）`的结果相同， `x`与 `y`不为 `null`；
3. **传递性**：即 `x.equals(y)`结果为 `true`, `y.equals(z)`结果为 `true`，则 `x.equals(z)`结果也必须为 `true`；
4. **一致性**：即 `x.equals(y)`返回 `true`或 `false`，在未更改 `equals`方法使用的参数条件下，多次调用返回的结果也必须一致。 `x`与 `y`不为 `null`。
5. 如果 `x`不为 `null`, `x.equals(null)`返回 `false`。

- ``clone()`

```java
protected native Object clone() throws CloneNotSupportedException;
```

此方法返回当前对象的一个副本。

这是一个 `protected`方法，提供给子类重写。但需要实现 `Cloneable`接口，这是一个标记接口，如果没有实现，当调用 `object.clone()`方法，会抛出 `CloneNotSupportedException`。

```java
public class CloneTest implements Cloneable {
    private int age;
    private String name;
    //省略get、set、构造函数等 
    
    @Override
    protected CloneTest clone() throws CloneNotSupportedException{
        return (CloneTest) super.clone();
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest cloneTest = new CloneTest(23, "XX");        
        CloneTest clone = cloneTest.clone();        
        System.out.println(clone == cloneTest);        
        System.out.println(cloneTest.getAge()==clone.getAge());
        System.out.println(cloneTest.getName()==clone.getName());   
	}
}
//输出结果
//false
//true
//true
```

从输出我们看见， `clone`的对象是一个新的对象；但原对象与 `clone`对象的 `String`类型的 `name`却是同一个引用，这表明， `super.clone`方法对成员变量如果是引用类型，进行是浅拷贝。

>那如果我们要进行深拷贝怎么办呢？ 
>
>**答案是**：如果成员变量是引用类型，想实现深拷贝，则成员变量也要实现 `Cloneable`接口，重写 `clone`方法。

- **`toString()`**

```java
public String toString();
```

这是一个 `public`方法，子类可重写，建议所有子类都重写 `toString`方法，默认的 `toString`方法，只是将当前类的全限定性类名 `+@+`十六进制的 `hashCode`值。

**我们思考一下为什么需要toString方法？**

可以这么理解：返回当前对象的字符串表示，可以将其打印方便查看对象的信息，方便记录日志信息提供调试。我们可以选择需要表示的重要信息重写到 `toString`方法中。

- **`wait()/ wait(long)/ wait(long,int)`**

这三个方法是用来线程间通信用的，作用是阻塞当前线程，等待其他线程调用 `notify()/notifyAll()`方法将其唤醒。这些方法都是 `public final`的，不可被重写。

**注意：**

1. 此方法只能在当前线程获取到对象的锁监视器之后才能调用，否则会抛出 `IllegalMonitorStateException`异常。
2. 调用 `wait`方法，线程会将锁监视器进行释放；而 `Thread.sleep，Thread.yield()`并不会释放锁。
3. `wait`方法会一直阻塞，直到其他线程调用当前对象的 `notify()/notifyAll()`方法将其唤醒；而 `wait(long)`是等待给定超时时间内（单位毫秒），如果还没有调用 `notify()/nofiyAll()`会自动唤醒； `wait(long,int)`如果第二个参数大于 `0`并且小于 `999999`，则第一个参数 `+1`作为超时时间；

- `**notify()/notifyAll()**`

前面说了，如果当前线程获得了当前对象锁，调用 `wait`方法，将锁释放并阻塞；这时另一个线程获取到了此对象锁，并调用此对象的 `notify()/notifyAll()`方法将之前的线程唤醒。这些方法都是 `public final`的，不可被重写。

1. `publicfinalnativevoidnotify();` 随机唤醒之前在当前对象上调用 `wait`方法的一个线程
2. `publicfinalnativevoidnotifyAll()`; 唤醒所有之前在当前对象上调用 `wait`方法的线程

**注意**：调用 `notify()`后，阻塞线程被唤醒，可以参与锁的竞争，但可能调用 `notify()`方法的线程还要继续做其他事，锁并未释放，所以我们看到的结果是，无论 `notify()`是在方法一开始调用，还是最后调用，阻塞线程都要等待当前线程结束才能开始。

> 为什么 `wait()/notify()`方法要放到 `Object`中呢？
>
> 因为每个对象都可以成为锁监视器对象，所以放到 `Object`中，可以直接使用。

- `**finalize()**`

```java
protected void finalize() throws Throwable;
```

此方法是在垃圾回收之前，JVM会调用此方法来清理资源。此方法可能会将对象重新置为可达状态，导致JVM无法进行垃圾回收。

我们知道java相对于C++很大的优势是程序员不用手动管理内存，内存由jvm管理；如果我们的引用对象在堆中没有引用指向他们时，当内存不足时，JVM会自动将这些对象进行回收释放内存，这就是我们常说的垃圾回收。但垃圾回收没有讲述的这么简单。

**finalize()方法具有如下4个特点：**

1. 永远不要主动调用某个对象的 `finalize()`方法，该方法由垃圾回收机制自己调用；
2. `finalize()`何时被调用，是否被调用具有不确定性；
3. 当 `JVM`执行可恢复对象的 `finalize()`可能会将此对象重新变为可达状态；
4. 当 `JVM`执行 `finalize()`方法时出现异常，垃圾回收机制不会报告异常，程序继续执行。

## 总结

> 基本讲述了Object类相关的所有方法和作用，常用toString(),equals()方法。

2022/11/7 下午2.

***

***

# IO流

## IO流的基本理解

**IO就是输入/输出**。Java IO类库基于抽象基础类InputStream和OutputStream构建了一套**I/O体系**，主要解决从数据源读入数据和将数据写入到目的地问题。`我们把数据源和目的地可以理解为IO流的两端。`当然，通常情况下，这两端可能是文件或者网络连接。

我们用下面的图描述下，加深理解：

>从一种数据源中通过InputStream流对象读入数据到程序内存中

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9RbuaPDHJwybiaxkH7bGwy2IGCicnwej13icRiaibU6KAsOVa28Q4qZPJamib8Jg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

> 当然我们把上面的图再反向流程，就是OutputStream的示意了。

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9RbusVBEiciboOkLMlITMZUYZ4kS5JdbCyicia7ksBL3Ym6CDFueapqleeEcJA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

其实除了面向字节流的InputStream/OutputStream体系外，Java IO类库还提供了面向字符流的Reader/Writer体系。Reader/Writer继承结构主要是为了国际化，因为它能更好地处理16位的Unicode字符。

在学习是这两套IO流处理体系可以对比参照着学习，因为有好多相似之处。

## 总体设计

刚开始写IO代码，总被各种IO流类搞得晕头转向。这么多IO相关的类，各种方法，啥时候能记住。

其实只要我们掌握了IO类库的总体设计思路，理解了它的层次脉络之后，就很清晰。知道啥时候用哪些流对象去组合想要的功能就好了，API的话，可以查手册的嘛。

首先从流的流向上可以分为输入流InputStream或Reader，输出流OutputStream或Writer。`任何从InputStream或Reader派生而来的类都有read()基本方法，读取单个字节或字节数组；任何从OutputSteam或Writer派生的类都含有write()的基本方法，用于写单个字节或字节数组。`

从操作字节还是操作字符的角度，有面向字节流的类，基本都以XxxStream结尾，面向字符流的类都以XxxReader或XxxWriter结尾。当然这两种类型的流是可以转化的，有两个转化流的类，这个后面会说到。

一般在使用IO流的时候会有下面类似代码：

```java
FileInputStream inputStream = new FileInputStream(new File("a.txt"));
BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
```

这里其实是一种装饰器模式的使用，`IO流体系中使用了装饰器模式包装了各种功能流类。`

在Java IO流体系中`FilterInputStream/FilterOutStream`和`FilterReader/FilterWriter`就是装饰器模式的接口类，从该类向下包装了一些功能流类。有`DataInputStream、BufferedInputStream、LineNumberInputStream、PushbackInputStream`等，当然还有输出的功能流类；面向字符的功能流类等。

## IO流的继承体系结构

> InputStream流体系

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9RbuRyic9LXDaibVeib0PH1LRTMBCuaN7kAgDV7Dzm3M2jxs7lZKo4zn8945g/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

> OutputStream流体系

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9Rbu65shQvaePqT2FgIniaUU2mGPHicarV3QptJJkZEKoBeicu6rtiamFJS5Qg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

> Reader体系

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9RbumG0YNSG8YWh8VDd5ljHLgRUM24bO0h3J4NBXZpXrxs8ibibaERuMxIoA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

> writer体系

![图片](https://mmbiz.qpic.cn/mmbiz_png/zG26PepPiafY9Se4sibrElyafaicX7k9RbuY9liaZR2Duj28ppY1Vb9NjWrxxodSNX3w3l9KicOrRjibOPsnwicAgKYibg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

## File其实是个工具类

File类其实不止是代表一个文件，它也能代表一个目录下的一组文件（代表一个文件路径）。下面我们盘点一下File类中最常用到的一些方法

>```
>File.delete(); //删除文件或文件夹目录。
>File.createNewFile() //创建一个新的空文件。
>File.mkdir() //创建一个新的空文件夹。
>File.list() //获取指定目录下的文件和文件夹名称。
>File.listFiles() //获取指定目录下的文件和文件夹对象。
>File.exists() //文件或者文件夹是否存在
>
>String   getAbsolutePath()   // 获取绝对路径
>long   getFreeSpace()       // 返回分区中未分配的字节数。
>String   getName()         // 返回文件或文件夹的名称。
>String   getParent()         // 返回父目录的路径名字符串；如果没有指定父目录，则返回 null。
>File   getParentFile()      // 返回父目录File对象
>String   getPath()         // 返回路径名字符串。
>long   getTotalSpace()      // 返回此文件分区大小。
>long   getUsableSpace()    //返回占用字节数。
>int   hashCode()             //文件哈希码。
>long   lastModified()       // 返回文件最后一次被修改的时间。
>long   length()          // 获取长度,字节数。
>boolean canRead()  //判断是否可读
>boolean canWrite()  //判断是否可写
>boolean isHidden()  //判断是否隐藏
>
>
>// 成员函数
>static File[]    listRoots()    // 列出可用的文件系统根。
>boolean    renameTo(File dest)    // 重命名
>boolean    setExecutable(boolean executable)    // 设置执行权限。
>boolean    setExecutable(boolean executable, boolean ownerOnly)    // 设置其他所有用户的执行权限。
>boolean    setLastModified(long time)       // 设置最后一次修改时间。
>boolean    setReadable(boolean readable)    // 设置读权限。
>boolean    setReadable(boolean readable, boolean ownerOnly)    // 设置其他所有用户的读权限。
>boolean    setWritable(boolean writable)    // 设置写权限。
>boolean    setWritable(boolean writable, boolean ownerOnly)    // 设置所有用户的写权限。
>```

