package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math.min

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    n <- arbitrary[A]
    h <- frequency((1, Gen.const(empty)), (9, genHeap))
  } yield insert(n, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)


  // => Your properties should at least cover the following relevant facts:
  // * If you insert any two elements into an empty heap, finding the minimum of the resulting
  // heap should get the smallest of the two elements back.
  // * If you insert an element into an empty heap, then delete the minimum, the resulting heap should be empty.
  // * Given any heap, you should get a sorted sequence of elements when continually finding and deleting minima.
  // (Hint: recursion and helper functions are your friends.)
  // * Finding a minimum of the melding of any two heaps should return a minimum of one or the other.

  property("findMin") =  forAll { (v1: A, v2: A) =>
    val h = insert(v1, insert(v2, empty))
    findMin(h) == min(v1, v2)
  }

  property("isEmpty") = forAll { (n: A) =>
    isEmpty((deleteMin((insert(n, empty)))))
  }

  property("isSorted") = forAll {(h: H) =>
    def isSorted(h: H): Boolean =
      if (isEmpty(h)) true
      else {
        val m = findMin(h)
        val h2 = deleteMin(h)
        isEmpty(h2) || (m <= findMin(h2) && isSorted(h2))
      }
    isSorted(h)
  }

  property("findMin and meld") = forAll {(h1: H, h2: H) =>
    findMin(meld(h1, h2)) == min(findMin(h1), findMin(h2))
  }

  property("heapEqual") = forAll {(h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    heapEqual(meld(h1, h2), meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

  property("findMin and meld 2") = forAll {(h1: H, h2: H) =>
    val m1 = findMin(h1)
    val m2 = findMin(h2)
    val m = min(m1, m2)
    findMin(meld(deleteMin(h1), insert(m, h2))) == m

  }

}
