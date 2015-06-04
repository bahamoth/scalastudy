/**
 * Created by ByeongSu on 2015-06-04.
 */
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