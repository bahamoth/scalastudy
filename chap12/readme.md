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
