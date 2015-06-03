# Chap6

## 내용 요약
### object 는 scala 언어에서 제공하는, singleton instance 를 생성하기 위한 방법이다.
```scala
object foo {
  def bar = {...}
}
```
### class 이름과 동일한 object를 companion object 라 부른다.
```scala
#반드시 같은 file 안에 class 와 object 가 정의되어 있어야 한다.
class foo {}
object foo {}
```
### apply method는 ()를 overloading 할 수 있는 특별한 method 이다.(1장 8p)
```scala
class companion(val _Member :String) {
  println(_Member)
}

object companion {
  def apply(param :String) = new companion("<<"+param+">>")
}

object foo extends App {
  companion("Hello")
  new companion("777")
}
```
### apply method의 용례는 다음 2 가지이다.
1. companion method 의 factory
2. new keyword 생략에 의한 functional syntax 와 oo syntax 의 병합

### scala 프로그램의 entry point 는 object 하위 Array[String] => Unit 형태의 main method 이다.
### main method 대신 App trait 를 사용할 수도 있다.
### enumeration type 대신 enumeration class 를 제공한다.

## 문제풀이 해설
1.
```scala
object Conversion {
  def inchesToCentimeters(param :Double) = {param * 2.54}
  def gallonsToLiters(param :Double) = {param * 3.78541178}
  def milesToKilometers(param :Double) = {param * 1.609344}
}
```

2.
```scala
abstract class UnitConversion {
  def convert(param :Double) :Double
}

object inch2CM extends UnitConversion {
  def convert(param :Double) :Double = {param *2.54}
}
...

//또는
abstract class UnitConversion(val convertee :Double) {
  def convert(value :Double) :Double = {convertee * value}
}

object inch2CM extends UnitConversion(2.54)

```