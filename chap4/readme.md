# Chap4

## 내용 요약
... 여기에 챕터 내용을 요약

## 문제풀이 해설
1.
```scala
scala> import scala.collection.immutable._
val a = HashMap(("AppleWatch"->499), ("Macbook Retina"->2000))
val b = for ((key,value) <- a) yield (key, value*0.9)
import scala.collection.immutable._

scala> a: scala.collection.immutable.HashMap[String,Int] = Map(Macbook Retina -> 2000, AppleWatch -> 499)

scala> b: scala.collection.immutable.HashMap[String,Double] = Map(Macbook Retina -> 1800.0, AppleWatch -> 449.1)
```

2.
```scala
val str = scala.io.Source.fromFile("readme.md", "utf-8").mkString
val pattern = new scala.util.matching.Regex("[^(\\s)\\./{}]+")
val result = pattern findAllIn str
val resultMap = scala.collection.mutable.HashMap[String, Int]()
for (iter <- result) if (resultMap.contains(iter)) resultMap(iter) = resultMap(iter) + 1 else resultMap(iter) = 1
```

3.
```scala
val str = scala.io.Source.fromFile("readme.md", "utf-8").mkString
val pattern = new scala.util.matching.Regex("[^(\\s)\\./{}]+")
val result = pattern findAllIn str
var resultMap = scala.collection.immutable.HashMap[String, Int]()
for (iter <- result) resultMap = resultMap + (iter->(resultMap.getOrElse(iter, 0) + 1))
```

4.
```scala
val str = scala.io.Source.fromFile("readme.md", "utf-8").mkString
val pattern = new scala.util.matching.Regex("[^(\\s)\\./{}]+")
val result = pattern findAllIn str
var resultMap = scala.collection.immutable.SortedMap[String, Int]()
for (iter <- result) resultMap = resultMap + (iter->(resultMap.getOrElse(iter, 0) + 1))
```

5.
```scala
import scala.collection.JavaConversions.mapAsScalaMap
var javaMap: scala.collection.Map[String, Int] = new java.util.TreeMap[String, Int]()
val result = new scala.util.matching.Regex("[^(\\s)\\./{}]+").findAllIn (scala.io.Source.fromFile("readme.md", "utf-8").mkString)
for (iter <- result) javaMap = javaMap + (iter->(javaMap.getOrElse(iter, 0) + 1))
```

6.
```scala
val linkedMap = scala.collection.mutable.LinkedHashMap[String, Int]()
linkedMap("Monday") = java.util.Calendar.MONDAY
linkedMap("Tuesday") = java.util.Calendar.TUESDAY
linkedMap("Wednesday") = java.util.Calendar.WEDNESDAY
linkedMap("Thursday") = java.util.Calendar.THURSDAY
linkedMap("Friday") = java.util.Calendar.FRIDAY
linkedMap("Saturday") = java.util.Calendar.SATURDAY
linkedMap("Sunday") = java.util.Calendar.SUNDAY
scala> linkedMap
res15: scala.collection.mutable.LinkedHashMap[String,Int] = Map(Monday -> 2, Tuesday -> 3, Wednesday -> 4, Thursday -> 5, Friday -> 6, Saturday -> 7, Sunday -> 1)
```

7.
```scala
var length = 0
for (iter <- System.getProperties) if (length < iter._1.length) length = iter._1.length

scala> length
res21: Int = 29

for (iter <- System.getProperties) println(String.format("%29s | %s",iter._1, iter._2))
```

8.
```scala
def minmax(values:Array[Int]) = {
  (values.min,values.max)
}
```

9.
```scala
def lteqgt(values: Array[Int], v: Int) = {
  (values.count(v>_), values.count(v==_), values.count(v<_))
}
```

10.
```scala
#문자열 길이가 같을 경우
scala> "Hello".zip("World")
res27: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((H,W), (e,o), (l,r), (l,l), (o,d))

#문자열 길이가 다를 경우
scala> "Hello".zip("Worlds")
res28: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((H,W), (e,o), (l,r), (l,l), (o,d))

scala> "Helloooo".zip("Worlds")
res29: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((H,W), (e,o), (l,r), (l,l), (o,d), (o,s))
```