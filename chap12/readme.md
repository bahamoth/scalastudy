# chap.12 고차함수

## Functional programming 용어 정리
1. first class function(high order function, first class citizen, 약간 다름)
2. anonymous function
3. closure

## first class function
개념의 기원을 따져보면 쉽게 이해할 수 있음

Christopher Strachey 의 1960년데 논문에 "first class citizen" 이라는 표현이 나옴

모든 연산(assignment 및 parameter passing)이 가능한 entity를 first class citizen이라고 지칭(e.g. numeric)

어떤 프로그래밍 언어에서 function을 할당 , parameter passing , return 할 수 있으면 first class function을 지원한고 표현함

### first class function vs high order function
OOL의 class vs object 의 관계와 비슷하다고 보면 됨

first class function : 언어적 개념

high order function : first class citizen으로 쓰인 개별 function을 지칭

## anonymous function
high order function에 이름이 없음을 주목하면 anonymous function
variable 에 할당했다고 해서 anonymous function에 이름이 생긴건 아니다.
scala에서 anonymous function의 구현은 anonymous inner class이다.
```scala
//scala function literal
scala> (x: Int) => x + 1
res2: Int => Int = <function1>

scala> res2(1)
res3: Int = 2

//FunctionN function type 과 같다. syntatic sugar
scala> new Function1[Int, Int] {
  def apply(x: Int) = x + 1
}
     |      | res0: Int => Int = <function1>

scala> res0(1)
res1: Int = 2
```

## closure
free variable이 존재하는 high order function
```scala
//x 는 free variable
def greaterThan(x: Int, list: List[Int]) = {
  list.filter(i => i > x)
}
```

## 값으로서의 함수
```scala
scala> import scala.math._
val fun = ceil _
fun(7.77)

scala> fun: Double => Double = <function1>
scala> res23: Double = 8.0

scala> (1 to 10).map((x:Int) => x*x)
res26: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
```

## 익명 함수
```scala
scala> (1 to 10).toArray.map((x: Int) => 3*x)
res19: Array[Int] = Array(3, 6, 9, 12, 15, 18, 21, 24, 27, 30)

scala> (1 to 10).toArray.map(x => 3*x)
res21: Array[Int] = Array(3, 6, 9, 12, 15, 18, 21, 24, 27, 30)

scala> (1 to 10).toArray.map(3*_)
res20: Array[Int] = Array(3, 6, 9, 12, 15, 18, 21, 24, 27, 30)
```

```scala
scala> val fun = (x: Int) => x * x
fun: Int => Int = <function1>

scala> def fun(x: Int) = {x * x}
fun: (x: Int)Int
```
```scala
def fun(x: Int) = {x*x*x}
print("def: " + fun(3))

{
  val fun = (x: Int) => x*x
  print("anonymous" + fun(3))
}

print("결과는?: " + fun(3))
```


## 함수 인자를 받는 함수
```scala
scala> def a(f: Double => Double) = f(0.25)
a: (f: Double => Double)Double

scala> import scala.math._
a(ceil _)
scala> res22: Double = 1.0

a(sqrt _)
scala> res23: Double = 0.5
```

## 함수를 return하는 함수
```scala
scala> def a(x: Int) = (param: Int) => param * x
a: (x: Int)Int => Int

scala> val b = a(666)
b: Int => Int = <function1>

scala> b(666)
res24: Int = 443556
```
## parameter 타입 추론
```scala
def a(f: Double => Double) = f(0.25)

scala> a(x => x*1.1)
res25: Double = 0.275

scala> a(_ * 20)
res27: Double = 5.0
```

## closure의 scope(=free variable을 참조하는 방법)
```scala
class foo(val Value:Int = 666) {
}

var myFoo = new foo()

def makeBar(param: foo) = () => print(param.Value)

val bar = makeBar(myFoo)
val bar2 = () => print(myFoo.Value)

bar()
bar2()

var myFoo2 = myFoo
myFoo = new foo(777)

bar()
bar2()

scala> 666
scala> 666

scala> myFoo2: foo = foo@349d0836
scala> myFoo: foo = foo@4390f46e

scala> 666
scala> 777
```

## SAM 변환
```scala
import javax.swing.JButton
import java.awt.event.{ActionEvent, ActionListener}

val button = new JButton("Button!!")

//원래는 이렇게 해야 함
button.addActionListener(new ActionListener {
  override def actionPerformed(e: ActionEvent): Unit = {print("hello")}
})

scala> button.addActionListener((e: ActionEvent) => print("hello"))
<console>:17: error: type mismatch;
 found   : java.awt.event.ActionEvent => Unit
 required: java.awt.event.ActionListener
              button.addActionListener((e: ActionEvent) => print("hello"))
              
//implicit SAM 변환을 다음과 같이 구현할 수 있다.
scala> implicit def makeAction(a: (ActionEvent) => Unit) = new ActionListener {
  override def actionPerformed(e: ActionEvent): Unit = a(e)
} 
button.addActionListener((e: ActionEvent) => print("hello"))
```

##커링
```scala
scala> def b(x: Int) = (y: Int) => x * y
b: (x: Int)Int => Int


scala> b(5)
res33: Int => Int = <function1>

scala> res33(6)
res34: Int = 30

scala> b(5)(6)
res31: Int = 30
```
