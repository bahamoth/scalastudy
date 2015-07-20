# chap.16 XML 처리

## 요약
1. Scala는 XML 리터럴을 언어적 차원에서 지원한다. e.g. "<head></head>" vs <head></head>
2. 기본 타입은 Node / NodeSeq 이다.
3. Node 안에 Scala 코드를 포함할 수 있다.
4. \ , \\ 로 XPath 연산을 제공한다.
5. XML Node 의 구조를 변환하기 위한 인터페이스로 RewriteRule / RuleTransformer 를 사용한다.
6. XML Load / Save 를 제대로 사용하려면 Java API 지원을 받아야 한다.
7. 주석, CDATA를 보존하려면 ConstructingParser를 사용한다.

## XML 리터럴

```scala
scala> val doc = <root><child>Hello Scala</child></root>

doc: scala.xml.Elem = <root><child>Hello Scala</child></root>

scala> 
val items = <node>Hello</node><node>Scala</node>
items: scala.xml.NodeBuffer = ArrayBuffer(<node>Hello</node>, <node>Scala</node>)
```

## element attributes
```scala
scala> val image = <img alt="asdasd 韓 asdasdas"/>
image: scala.xml.Elem = <img alt="asdasd 韓 asdasdas"/>

scala> image.attributes("alt")
res0: Seq[scala.xml.Node] = asdasd 韓 asdasdas

scala> val image = <img alt="asdasd &eacute; asdasdas"/>
image: scala.xml.Elem = <img alt="asdasd &eacute; asdasdas"/>

scala> image.attributes("alt")
res1: Seq[scala.xml.Node] = ArrayBuffer(asdasd , &eacute;,  asdasdas)
```

## Scala 코드 사용
```scala
scala> <ul>{for (i <- a) yield <li>{i}</li>}</ul>
res4: scala.xml.Elem = <ul><li>a</li><li>b</li><li>c</li></ul>
```

## XPath
```scala
scala> res4
res5: scala.xml.Elem = <ul><li>a</li><li>b</li><li>c</li></ul>

scala> res4 \ "li"
res6: scala.xml.NodeSeq = NodeSeq(<li>a</li>, <li>b</li>, <li>c</li>)
```

## XML 변환
```scala
res4: scala.xml.Elem = <ul><li>a</li><li>b</li><li>c</li></ul>

scala> val rule1 = new xml.transform.RewriteRule{
  override def transform(n: xml.Node) = n match {
    case e @ <ul>{_*}</ul> => e.asInstanceOf[xml.Elem].copy(label="ol")
    case _ => n
  }
}
     |      |      |      |      | rule1: scala.xml.transform.RewriteRule = <function1>

scala> import scala.xml.transform.RuleTransformer

val transform = new RuleTransformer(rule1).transform(res4)
import scala.xml.transform.RuleTransformer

scala> transform: Seq[scala.xml.Node] = List(<ol><li>a</li><li>b</li><li>c</li></ol>)
```
