# Chap5

## 내용 요약
... 여기에 챕터 내용을 요약

## 문제풀이 해설
1.
```scala
class Counter(private var value :BigInt = 0) {  
  def increment() {value +=1}
  def current = value
}

class Counter(private var value :Int = 0) {  
  def increment() {if (value < Int.MaxValue) value +=1}
  def current = value
}
```

2.
```scala
class BankAccount(private var balance :Int = 0) {    
  def deposit = balance
  def withdraw(param :Int) {balance -= param}
}
val a = new BankAccount(100)

scala> a.deposit
res8: Int = 100

scala> a.withdraw(10)

scala> a.deposit
res12: Int = 90
```

3.
```scala
class Time(private var _hours :Int = 0 , private var _minutes :Int = 0) {
  def hours = _hours % 24
  def minutes = _minutes % 60
  def before(h :Int, m :Int) :Boolean = {
    if (hours < h || (hours == h && minutes == m)) true else false
  } 
}
```
