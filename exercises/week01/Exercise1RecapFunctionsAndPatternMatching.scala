package scala_exercises.Modulo2.week01

// Given the function

object Exercise1RecapFunctionsAndPatternMatching extends App {

  val f: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: y :: rest => "two"
  }

  // What do you expect is the result of
  val t1 = f.isDefinedAt(List(1, 2, 3)) // Gives
  // 0 - true - its correct
  // 0 - false
  println(t1)

  val g: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: rest =>
      rest match {
        case Nil => "two"
      }
  }

  val t2 = g.isDefinedAt(List(1, 2, 3)) // Gives
  // 0 - true - its correct
  // 0 - false

  println(t2)

}
