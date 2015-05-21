# Chap2  
 조건문,반복문,함수

## 내용 요약
1 . if문 자체가 값을 가짐.

    scala> val s = if(x > 0) 1 else -1 
    s: Int = 1
    scala> x
    res31: Int = 1

보통 다른데서는 

     scala> if(x > 0) 
     s= 1 
     else 
     s= -1

- 위에서는 1이라는 int타입을 예제로 했지만, 어떤타입이 와도 상관없음.
- 심지어 타입이 달라도 상관없음.


    scala> val s = if(x > 0) "a" else 1
    s: String = a


2 . Unit  = 값없음


- 값없음을 나타낼때 사용하는 타입이다. 
- java에서 void같은 느낌


3 . 구문종료, 세미콜론(;)
 - 세미콜론을 안쓰는걸 권장.
 - 대신 한줄에서 구분이 필요할때는 표시
 

    scala> var r = 3
    r: Int = 3
    scala> var n = 2
    n: Int = 2
    scala> {r = r * n n -=1}
    <console>:1: error: ';' expected but integer literal found.
                 {r = r * n n -=1}
                              ^
    scala> {r = r * n; n -=1}


4 . 블럭{}도 값을가진다.

    scala> val s = { x * x }
    s: Int = 1
    scala> x
    res37: Int = 1

만약 {}이 2줄이상일경우라면, 가장 마지막에 있는 수식의 결과값이 할당된다.

    scala> val s = { x * x; y*3; y+z }
    s: Int = 5
    scala> print(x+"/"+y+"/"+z)
    1/2/3


5 . 반복문 for ( i <- expr / variable <- expresion)

- while은 자바나 c와 동일
- for ( i <- expr / variable <- expresion)


    scala> for (i <- 1 to 10) r = r+ i
    scala> r
    res14: Int = 55


- for ( i <- 0 until str.length) ..
- for ( i <- "HELLO")


    scala> for (i <- 0 until s.length) printf("%c", s(i))
    Hello
    scala> for (i <- "HELLO") printf("%c", i)
    HELLO

- for문내에 조건, 중첩변수


    scala> for(i <- 1 to 3; j <-1 to 3 if i != j) printf (( 10 * i + j ) + " " )
    12 13 21 23 31 32

- for-comprehensions (yield)


    scala> for( i <- 1 to 10 ) yield i % 3
    res40: scala.collection.immutable.IndexedSeq[Int] =
     Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1)


6 . 함수
함수정의방법 ( def 함수이름 / 인자 / 바디  )

    scala> def abs(x: Double) = if (x >=0) x else -x
    abs: (x: Double)Double
    scala> abs(3.123)
    res41: Double = 3.123
    scala> abs(-3.123)
    res42: Double = 3.123

- 리턴타입 정의가 필요없음 (재귀함수 제외)
- '=' 우측의 식의 결과로 컴파일러가 알아서 판단
- return 키워드도 필요없음. 가장 마지막식이 그 함수의 리턴값이 됨.


7 . 함수의 기본인자
- 함수의 파라미터값이 없을경우 파라미터값을 디폴트로 설정할수 있다.
- 스프링에서 requestparam이랑 비슷해보임.

    scala> def decorate(str : String, left: String = "[", right: String = "]") = left + str + right
    decorate: (str: String, left: String, right: String)String
    scala> decorate("Hello")
    res44: String = [Hello]

8 . 함수의 가변인자 
- 파라미터의 갯수가 명시되지 않은경우 

    scala> def sum(args: Int*) = { 
    var result =0
    for(arg <- args) 
    result += arg
    result
    }
    sum: (args: Int*)Int
    scala> sum(1,4,9,16,25)
    res50: Int = 55
    scala> sum (1 to 5)
    <console>:9: error: type mismatch;
     found   : scala.collection.immutable.Range.Inclusive
     required: Int
              sum (1 to 5)
                     ^
    scala> sum (1 to 5 :_*)
    res52: Int = 15

- :_* -> 인자시퀀스


9 . 프로시져 == 값을 리턴하지 않는 함수
- 리턴타입이 Unit


10 . 레이지값 (게으른 초기화)

    lazy val a = 1

- 장점 : 필드가 많은 경우 사용되지 않는 상황이나 초기화를 빨리하고 부가 작업을 뒤로 미루고 싶을 때 적합합니다.
- 단점 : 최적화를 통해 성능 저하를 해결해야 하는 상황이 아니라면 초기화의 지연을 사용하지 않아야 합니다. 어떤 경우에는 이런 기법이 디버깅을 어렵게 만듭니다.


11 . 예외처리
- 기본적으로 자바와 동일
- 함수선언시 예외선언이 필요없음
- 타입은 Nothing타입 (Unit하고 다름)

----------

## 문제풀이 해설

1 . 숫자의 signum은 숫자가 양수이면 1, 음수이면 -1, 0이면 0이다. 이 값을 계산하는 함수를 작성하라.

    def signum(num: Int) = {
      if (num > 0) 1 else if (num < 0) -1 else 0
    }

2 . 빈 븍록시 {}의 값은 무엇인가? 타입은 무엇인가?

Unit

3 .   스칼라에서 x = y = 1 할당이 유효한 상황 하나만 말해보라. (힌트 적당한 x 타입을 고른다)

    scala> var x = ()
    x: Unit = ()
    scala> var y = 1
    y: Int = 1
    scala> x = y = 1
    x: Unit = ()

4 . 자바 루프 for (int i = 10; i >= 0; i++) System.out.println(i); 와 동일한 스칼라 코드를 작성하라.

    scala> for ( i<- 1 to 10) println(i)
    
5 . n부터 0까지 숫자를 출력하는 countdown(n: Int) 프로시저를 작성하라.

    def countdown(n: Int) : Unit= {
      val step = if (n < 0) 1 else -1
      printf("from %d to 0 -> ", n)
      for (i <- n to(0,step)) print(i + ", ")
    }

6 . 문자열의 모든 문자의 유니코드를 곱하는 for 루프를 작성하라. 예를들어 "Hello"문자들의 곱은 9415087488L이다.

    var product = 1L
    for (c <- "Hello") {
    product *= c
    }
    println("Hello is " + product)

7 . 6번 문제를 루프를 쓰지 않고 풀어보라.(힌트 스칼라독의 StringOps를 살펴본다.)

    "Hello".map(_.toLong).product


8 . 6번 문제에서 기술한 바와 같이 곱을 계산하는 product(s: String) 함수를 작성하라.

    def product(s: String) = {
    var product = 1L
    for (c <- s) {
       product *= c
     }
     product
    }
 
 9 .  6번 문제의 함수를 재귀함수로 만들라.    

    def recursiveMultiply(chars: Char*): Long = {
     if (chars.length == 0) return 1L
     chars.head * recursiveMultiply(chars.tail: _*)
    }


 10 . n은 정수인 x^n을 계산하는 함수를 작성하라. 다음 재귀 정의를 이용하라.
 
- n이 양의짝수이고, y = x^(n/2)이면 x^n = y^2
- n이 양의 홀수이면 x^n = x*x^(n-1)
- x^0 = 1
- n이 음수이면 x^n = 1 / x^-n
- return 문을 사용하지 않는다.



    def mypow(x: Long, n: Long): Long = {
      if (n > 0) {
          if (n % 2 == 0 && n > 2) mypow(mypow(x, n/2), 2)
              else x * mypow(x, n-1)
          } else if (n < 0) 1 / mypow(x, -n)
      else 1L
    }


