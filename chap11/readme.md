# chap.11 연산자
unary , binary operator는 실제로는 method call 이다.

연산자 우선순위는 연산자의 첫번째 문자로 결정한다.

:: 연산(list 생성) , = (assignment) 를 제외한 모든 연산은 좌->우로 계산한다.

apply() : new에 대응
```scala
val foo = bar("hello")
val foo = bar.apply("hello")
```

foo.update(a1,a2,...,v) : foo(a1,a2,..) = v
```scala
val foo("key") = value
val foo.update("key",value)
```

unapply : object -> 생성 parameter 추출
```scala
class foo(val a : Int) {
}

object foo {
  def apply(param : Int) = new foo(param)
  def unapply(in : foo) ={
    Some(in.a)
  }
}
var a=0
var foo(a) = foo(888)

scala> a: Int = 888
```
