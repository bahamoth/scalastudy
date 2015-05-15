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



## 문제풀이 해설

여기에 문제풀이 해설을 기술


## 퀴즈

피보나치 수열의 각 항은 바로 앞의 항 두 개를 더한 것이 됩니다. 1과 2로 시작하는 경우 이 수열은 아래와 같습니다.

 >	1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

 짝수이면서 4백만 이하인 모든 항을 더하면 얼마가 됩니까?

[출처:project euler](http://euler.synap.co.kr/prob_detail.php?id=2)