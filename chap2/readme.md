# Chap2

## 내용 요약

### 모든구문은 값을 갖는다.

scala 의 if/else 구문은 값을 갖는다.

```scala
#조건부 초기화가 가능하다.
val a = if (x >0) 1 else 0

#분기의 type이 다를 경우 type 은 Any 다.
scala> val a = if (true) 0 else "false"
a: Any = 0

#분기에 완결성이 없는 경우

#numeric 은 AnyVal
scala> val a = if (false) 0
a: AnyVal = ()

#non numeric 은 Any
scala> val a = if (false) ""
a: Any = ()

#()는 Unit
scala> val a = ()
a: Unit = ()

```

{} 을 사용하면 programmable 하게 변수를 초기화할 수 있다.
```scala
scala> val x = 10 ; val x0 = 0 ; val y = 7 ; val y0 = 3
x: Int = 10
x0: Int = 0
y: Int = 7
y0: Int = 3

scala> import scala.math._
import scala.math._

scala> val distance = {val dx = x - x0; val dy = y - y0; sqrt(dx*dx + dy*dy)}
distance: Double = 10.770329614269007

#dx 는 소멸. 깔끔하다.
scala> dx
<console>:11: error: not found: value dx
```

assignment 도 값을 갖는다. (Unit)
```scala
scala> val a = {val b = 0}
a: Unit = ()
```

이렇게 쓰지 않도록 주의한다.
```scala
#느낌상 a =1 , b = 1 일것 같지만, 그렇게 생각한다면 경기도 오산이다.
a = b = 1

a:Unit
b:Int = 1
```

for loop 는 iterator 만 지원한다. **for (int i=0 ; i < 100 ; ++i) 같은 문법은 없다.**

loop의 세미콜론(;)은 generator 를 기술할 때 사용한다.

yield 는 loop 의 sequence 를 값으로 갖는 collection을 생성한다.

```scala
scala> for (i <- 1 to 3 ; j <- 4 to 6) print(i*10 + j + " ")
14 15 16 24 25 26 34 35 36 

//아무 일도 안일어난다.
scala> for (i <- 1 to 3 ; j <- 4 to 6)  i*10 + j

//loop의 시퀀스를 값으로 갖는 Vector 가 res4 에 할당된다.
scala> for (i <- 1 to 3 ; j <- 4 to 6) yield i*10 + j
res4: scala.collection.immutable.IndexedSeq[Int] = Vector(14, 15, 16, 24, 25, 26, 34, 35, 36)

```



## 문제풀이 해설

1. 
```scala
scala> def signum(param: Int) = {if (param > 0) 1 else if (param < 0) -1 else 0 }
signum: (param: Int)Int

scala> signum(5)
res9: Int = 1

scala> signum(-5)
res10: Int = -1

scala> signum(0)
res11: Int = 0
```

2. 
```scala
scala> val a = {}
a: Unit = ()
```

3. 
```scala
scala> var x = ()
x: Unit = ()

scala> var y = 0
y: Int = 0

scala> x = y = 1
x: Unit = ()
```

4. 
```scala
scala> for (i <- 0 to 10 ; x = 10-i) print (x + " ")
10 9 8 7 6 5 4 3 2 1 0 
```

5. 
```scala
scala> def countdown(n: Int) {for (i <- 0 to n ; x = n - i) print(x + " ")}
countdown: (n: Int)Unit

scala> countdown(5)
5 4 3 2 1 0 
```

6. 




## 퀴즈

피보나치 수열의 각 항은 바로 앞의 항 두 개를 더한 것이 됩니다. 1과 2로 시작하는 경우 이 수열은 아래와 같습니다.

 >	1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

 짝수이면서 4백만 이하인 모든 항을 더하면 얼마가 됩니까?

[출처:project euler](http://euler.synap.co.kr/prob_detail.php?id=2)
