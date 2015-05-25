# Chap3

길이가 고정이면 Array 사용, 가변이면 ArrayBuffer 사용
초기 값을 줄 때 new 를 사용하지 않음.
원소 접근에는 ()를 사용
원소 방문에는 for(elem <- arr)  
새 배열 변환 for(elem <- arr if ...) ... yield ...
scala, java 배열은 호환 가능. ArrayBuffer와 scala.collection.JavaConversions 사용
3.1 고정길이 배열(Array)
     - 길이가 변하지 않는 배열 필요시 Array 사용.
     val nums = new Array[Int](10) //10개 정수배열 모두 0으로 초기화
     val a= new Array[String](10)     //10개 원소 문자열 배열, null 초기화
     val s = Array("Hello", "World")     //길이2 Array[String] - 타입 추론
          //초기값 부여시 new 없음
     s(0) = "Goodbye"     //원소 접근시 []가 아닌 () 사용

3.2 가변길이 배열 : 배열버퍼 (ArrayBuffer)
     import scala.collection.mutable.ArrayBuffer
     val b = ArrayBuffer[Int]()
          // new ArrayBuffer[Int] 도 같은 효과
          //정수 저장 빈 배열 버퍼
     b += 1     //ArrayBuffer(1), += 원소끝에 더한다
     b += (1,2,3,5)     //ArrayBuffer(1,1,2,3,5)
     b ++= Array(8,13,21)     //ArrayBuffer(1,1,2,3,5,8,13,21)     ++=연산자로 어떤 컬렉션이나 덧붙일수 있음
     b.trimEnd(5)     //마지막 5개 원소 제거    ArrayBuffer(1,1,2)
     
     배열 끝에 원소를 더하거나 제거는 효율적(분활 상환 고정 시간) 연산이나, 임의의 위치 연산은 효율적이지 않다.(그 위치 이후의 모든 원소를 이동해야 함)

     b.insert(2,6)     //index 2 앞에 삽입, ArrayBuffer(1,1,6,2)
     b.insert(2,7,8,9)     //원하는 수 만큼 가능, ArrayBuffer(1,1,7,8,9,6,2)
     b.remove(2)     //ArrayBuffer(1,1,8,9,6,2)
     b.remove(2,3)     //index2부터 5까지제거, ArrayBuffer(1,1,2)
     
     배열버퍼를 배열(Array)로 전환      b.toArray     //Array(1,1,2)
     배열을 배열버퍼로 전환     a.toBuffer     //ArrayBuffer(1,1,2)

3.3 배열과 배열 버퍼 방문
     for(i <- 0 until a.length)
          println(i+": "+a(i))     
     //i는 0부터 a.length-1까지 
     0 until (a.length, 2)     //매 요소를 2씩 건너뜀
     for(elem <- a)     //배열의 매 요소를 분기

3.4 배열 변환
     val a = Array(2,3,5,7,11)
     val result = for(elem <-a) yield 2*elem
          //Array(4,6,10,14,22)

     for(elem <a if a%2==0) yield 2*elem
          // a.filter(_ % 2 == 0).map(2 * _)
          //a filter { _ % 2 == 0 } map { 2 * _ }

     var first = false
     val indexes = for (i <- 0 until a.length if first || a(i) >= 0) yield {
          if (a(i) <0) first =- false; i
     }
     for(j<0 until indexes.length) a(j) = a(indexes(j))
     a.trimEnd(a.length - indexes.length)

3.5 일반 알고리즘
     Array(1,7,2,9).sum     //19 , ArrayBuffer도 동작
     ArrayBuffer("Mary", "had", "a", "little").max     //"little"

     val b = ArrayBuffer(1,7,2,9)    
     val bSorted = b.sorted(_ < _)     //ArrayBuffer(1,2,7,9)

     //배열은 제자리 정렬 가능
     val a = Array(1,7,2,9)
     scala.util.Sorting.quicksort(a)     //a는 Array(1,2,7,9)로 변경

     배열 사이 문자열
     a.mkString(" and ")     //"1 and 2 and ..."

3.6 스칼라독 해독
     //문서 읽어볼 것

3.7 다차원 배열
     ofDim 메소드 이용

     val matrix = Array.ofDim[Double](3,4)     //3행4열
     
     접근시
     matrix(row)(column) = 42

     
3.8 자바 연동

     







## 문제풀이 해설
연습문제
/** Chapter 03 **/
 
/*
1. a를 0(포함)과 n(불포함) 사이의 임의의 수 n개의 배열로 설정하는 코드 조각을 작성하라.
*/
println("# 3.1 --")
 
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
 
def randomNumberArray(n : Int) = {
(for (i <- 0 until n) yield Random.nextInt(n)).toArray
}
 
val randomNumbers = randomNumberArray(20)
println("난수들 : " + randomNumbers)
 
/*
2. 정수 배열의 인접한 원소를 교환하는 루프를 작성하라. 예를들어 Array(1,2,3,4,5)는
Array(2,1,4,3,5)가 된다.
*/
println("# 3.2 --")
 
val arr1 = Array(1,2,3,4,5)
for (i <- 1 until (arr1.length, 2)) {
val temp = arr1(i - 1)
arr1(i - 1) = arr1(i)
arr1(i) = temp
}
 
println("인접 원소 교환 : " + arr1)
 
/*
3. 2번 문제를 반복하되 교환한 값으로 새 배열을 생성하라. for/yield를 사용한다.
*/
println("# 3.3 --")
val arr2 = Array(1,2,3,4,5,6,7,8,9,10)
val arr2Changed = (for (i <- 0 until arr2.length) yield {
if (i % 2 == 1) {
arr2(i - 1)
} else if (i != (arr2.length - 1)) {
arr2(i + 1)
} else {
arr2(i)
}
}).toArray
 
println("교환후 새 배열 : " + arr2Changed)
 
/*
4. 정수 배열이 주어졌을 때, 기존 배열의 모든 양수 값이 기존 순서대로 오고 그 뒤에 모든 0 혹은
음수 값이 기존 순서대로 오는 새 배열을 작성하라.
*/
println("# 3.4 --")
val ex4ab = new ArrayBuffer[Int]()
val ex4arr = Array(1,2,0,3,4,-5,6,7,-2,-3,10)
ex4ab ++= (for (i <- 0 until ex4arr.length if ex4arr(i) > 0) yield ex4arr(i))
ex4ab ++ (for (i <- 0 until ex4arr.length if ex4arr(i) <= 0) yield ex4arr(i))
println(ex4ab.toArray)
 
/*
5. Array[Double]의 평균을 어떻게 계산하나?
*/
println("# 3.5 --")
val doubles = Array(3.3,5.3,8.3,1.2,6.7)
println(doubles.sum / doubles.length)
 
/*
6. Array[Int]를 어떻게 재배열하면 역순으로 정렬되게 나오나? 같은 일을 ArrayBuffer[Int]에는 어떻게 하나?
*/
println("# 3.6 --")
val ex6arr = Array(54,3,2,2,56,8,3,34,8,98,23).sorted.reverse
println("배열 : " + ex6arr)
val ex6ab = ArrayBuffer(54,3,2,2,56,8,3,34,8,98,23).sorted.reverse
println("ArrayBuffer : " + ex6ab)
// 너무 쉬운데, 문제 의도를 잘 못 파악한 것인가?
 
/*
7. 중복을 제거한 배열의 모든 값을 생성하는 코드 조각을 작성하라.
(힌트: 스칼라독을 참고한다.)
*/
println("# 3.7 --")
val ex7arr = Array(1,2,3,2,4,5,6,7,5,8,9,7,2,10,10)
println("중복 제거 : " + ex7arr.distinct.mkString("<", ",", ">"))
 
/*
8. "3.4 배열 변환" 마지막에 나오는 예제를 재작성하라. 음수 원소의 인덱스를 모으고,
시퀀스를 역순으로 만들고, 마지막 인덱스를 빼고, 각 인덱스에 a.remove(i)를 호출하라.
3.4절의 두가지 방법과 이 방법의 효율성을 비교하라.
*/
println("# 3.8 --")
 
println("TODO")
 
/*
9. java.util.TimeZone.getAvailableIDs가 리턴하는 미국 내 모든 시간대의 콜렉션을 만들어라.
"America/" 접미를 제거하고 그 결과를 정렬하라.
*/
println("# 3.9 --")
 
val americas = for (am <- java.util.TimeZone.getAvailableIDs if am startsWith "America") yield am.replaceFirst("America/", "")
println("America 모든 시간 대 : " + americas.sorted)
 
/*
10. java.awt.datatransfer._ 를 임포트하고 SystemFlavorMap 타입 오브젝트를 다음 호출로
만들어라.
val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
DataFlavor.imageFlavor를 인자로 getNativesForFlavor 메소드를 호출하여 스칼라 버퍼로
리턴 값을 얻어라.
(왜 이런 이상한 클래스일까? 표준 자바 라이브러리에서 java.util.List 사용을 찾기가 쉽지 않다.)
*/
println("# 3.10 --")
 
import java.awt.datatransfer._
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer
 
val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
val imageFlavors : Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
println("ImageFlavors : " + imageFlavors)

