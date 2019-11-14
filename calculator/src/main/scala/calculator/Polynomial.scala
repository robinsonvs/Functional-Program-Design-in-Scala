package calculator

import Math._

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal {
      pow(b(), 2) - 4 * a() * c() // A = b2 - 4ac
    }
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal {
      val d = delta()
      if (d < 0) Set()
      else {
        val bValue = b()
        val aValue = a()

        Set (
          (-bValue + sqrt(d)) / (2 * aValue),
          (-bValue - sqrt(d)) / (2 * aValue)
        )
      }
    }
  }
}
