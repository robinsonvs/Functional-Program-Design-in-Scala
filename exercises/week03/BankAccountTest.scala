package scala_exercises.Modulo2.week03

object BankAccountTest extends App {
  val acct = new BankAccount
  println(acct deposit 50)
  println(acct withdraw 20)
  println(acct withdraw 20)
  println(acct withdraw 15)
}
