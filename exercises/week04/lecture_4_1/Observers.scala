package scala_exercises.Modulo2.week04.lecture_4_1

object Observers extends App {

  val a = new BankAccount
  val b = new BankAccount

  val c = new Consolidator(List(a, b))

  println(c.totalBalance)

  println(a deposit 20)

  println(c.totalBalance)

  println(b deposit 30)

  println(c.totalBalance)
}
