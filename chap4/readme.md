# Chap4

## 내용 요약
맵과 튜플 특징
- 수정 가능한 맵, 수정 불가능한 맵 중에 선택 필요하다.
- 디폴트로 해시 맵을 얻게 되지만, 트리 맵을 얻을 수도 있다.
- 스칼라와 자바 맵 사이 변환이 쉽다.
- 튜플은 값들을 뭉치는 데 유용하다.


4.1 맵 생성
 - 수정 불가능
```scala
val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
scores: scala.collection.immutable.Map[String,Int] = Map(Alice -> 10, Bob -> 3, Cindy -> 8)
```

 - 수정 가능
```scala
val scores = new scala.collection.mutable.HashMap[String,Int]
```

 - 다음 같이 정의 가능
```scala
val scores = Map(("Alice",10),("Bob",3),("Cindy",8))
```

4.2 맵 값 접근
 - Bob 값 호출
```scala
val bobsScore = scores("Bob")
bobsScore: Int = 3
```

 - Bob 있는지 확인 후 출력
```scala
val bobsScore = if(scores.contains("Bob")) scores("Bob") else 0
val bobsScore = scores.getOrElse("Bob",0)
```

 -  map.get(key)호출은 Some(키에 대한 값) 혹은 None인 Option오브젝트를 리턴한다.(option 클래스는 14장)


4.3 맵 값 갱신
```scala
scores("Bob") = 10  //기존값 갱신 mutable scores
scores("Fred") = 7  //새로운 키/값 추가
scores += ("Bob" -> 10 , "Fred" -> 7) //연산자 이용
scores -= "Bob" //삭제
val newScores = scores + ("Bob" -> 10, "Fred" -> 7 ) //수정 불가능한 맵 갱신 immutable scores
var scores = scores + ("Bob" -> 10, "Fred" -> 7 )
```

4.4 맵 반복
```scala
for((k,v) <- map ) process k and v
for(v <- scores.values) println(v) // keySet,values 제공. 10 3 8
for((k,v) <- scores) yield(v,k) //맵 뒤집기
```

4.5 정렬 맵
 -  스칼라에서는 디폴트는 해시 테이블이고 수정 불가능한 트리맵만 존재.(Java TreeMap을 개조)
```scala
val scores = scala.collection.immutable.SortedMap("Alice"->10,"Fred"->7,"Bob"->3,"Cindy" ->8)
scores: scala.collection.immutable.SortedMap[String,Int] = Map(Alice -> 10, Bob -> 3, Cindy -> 8, Fred -> 7)
```
 -  삽입 순서대로 키 방문 -> scala.collection.mutable.LinkedHashMap


4.6 자바 연동
```scala
import scala.collection.JavaConversions.mapAsScalaMap
val scores:scala.collection.mutable.Map[String,Int] = new java.util.TreeMap[String,Int]

import scala.collection.JavaConversions.propertiesAsScalaMap
val props:scala.collection.Map[String,String] = System.getProperties()
```
 - Java -> Scala 암묵전 변환 제공
```scala
import scala.collection.JavaConversions.mapAsScalaMap
import java.awt.font.TextAttribute._
val attrs = Map(FAMILY -> "Serif", SIZE -> 12)
val font = new java.awt.Font(attrs) 
```

4.7 튜플
서로 다른 타입의 값들의 집합
```scala
val t = (1, 3.14, "Fred")         // Tuple3[Int, Double, java.lang.String]
val second = t._2               // 0이 아닌 1에서 시작
val (first,second,third) = t    // 패턴 매칭
val (first,second,_) = t        // first,second 만
"New Youk".partition(_.isUpper) // (NY,ew ouk)
```

4.8 지핑(Zipping) 
값들을 묶어서 한번에 처리할 때 사용
```scala
val symbols = Array("<","-",">") // Array[String] = Array(<, -, >)
val counts = Array(2,10,2)       // Array[Int] = Array(2, 10, 2)
val pairs = symbols.zip(counts)  // Array[(String, Int)] = Array((<,2), (-,10), (>,2))
for((s,n) <- pairs ) print(s*n)  // <<---------->>
symbols.zip(counts).toMap        // scala.collection.immutable.Map[String,Int] = Map(< -> 2, - -> 10, > -> 2)
```

## 문제풀이 해설
1. 사고 싶은 여러 장치의 가격 맵을 만들라. 그러고 나서 키는 같고 가격은 10% 할인된 두 번째 맵을 생성하라.
```scala
val car = Map("Benz"->100,"Audi"->50,"BMW"->30)
val disCar = for((k,v) <- car) yield(k,v*0.9)
```
2. 파일에서 단어들을 읽어 들이는 프로그램을 작성하라. 각 단어가 얼마나 빈번하게 등장하는지 세는 수정 가능한 맵을 이용하라.
단어를 읽어들이는 데는 간단히 java.util.Scanner를 사용하라.
```scala
val in = new java.util.Scanner(new java.io.File("C:/Users/kth/workspace/test/myfile.txt"))
val inMap = new scala.collection.mutable.HashMap[String,Int]

while (in.hasNext()) {
  val key = in.next()
  inMap(key) = inMap.getOrElse(key, 0) + 1
 }
println("----q2----")
println(inMap)
```
3. 2번 문제를 수정 불가능한 맵으로 반복하라
```scala
val q3in = new java.util.Scanner(new java.io.File("C:/Users/kth/workspace/test/myfile.txt"))
var inMap = new scala.collection.immutable.HashMap[String,Int]

while (q3in.hasNext()) {
  val key = q3in.next()
  inMap = inMap + (key -> (inMap.getOrElse(key, 0) + 1))
 }
println("----q3----")
println(inMap)
```
4. 2번 문제를 단어가 정렬되어 출력되게 정렬 맵으로 반복하라

5. 2번 문제를 java.util.TreeMap을 스칼라 API로 개조해서 반복하라.

6. "Monday"를 java.util.Calendar.MONDAY로 매핑하고 다른 요일도 비슷한 방식으로 매핑하는
링크 해시 맵을 정의하라. 원소는 삽입된 순서로 방문이 일어남을 보이라.

7. 다음과 같이 모든 자바 속성의 표를 출력하라.

8. 배열에서 가장 작은 수와 큰수의 쌍을 리턴하는 minmax(values: Array[Int])함수를 작성하라.

9. v보다 작은 수의 개수, v와 같은 수의 개수, v보다 큰 수의 개수를 트리플로 리턴하는 lteqgt(values:Array[Int]],v:Int)함수를 작성하라.

10. "Hello".zip("World")와 같이 두 문자열을 집(zip)하면 무슨일이 생기나?가능한 유스케이스를 생각하라.

