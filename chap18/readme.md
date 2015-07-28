# chap.18

## 싱글톤 타입
v.type -> builder 패턴 대체

```scala
scala> class Document {
  var title = ""
  var author = ""
  def setTitle(title: String): this.type = {this.title=title; this}
  def setAuthor(author: String): this.type = {this.author=author; this}
}
scala> val a = new Document().setTitle("my Title").setAuthor("my AUthor")
a: Document = Document@297ea53a

scala> a.title
res19: String = my Title

scala> a.author
res20: String = my AUthor
```

```scala
class Document {
}

```
