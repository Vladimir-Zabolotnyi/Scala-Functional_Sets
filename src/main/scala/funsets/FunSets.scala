package funsets

import scala.annotation.tailrec

/**
 * 2. Purely Functional Sets.
 */
trait FunSets extends FunSetsInterface:
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  override type FunSet = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: FunSet, elem: Int): Boolean = s(elem)


  /**
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): FunSet = (a: Int) => a == elem


  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: FunSet, t: FunSet): FunSet = (a: Int) => s(a) || t(a)

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: FunSet, t: FunSet): FunSet = (a: Int) => s(a) && t(a)

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: FunSet, t: FunSet): FunSet = (a: Int) => s(a) && !t(a)

  /**
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: FunSet, p: Int => Boolean): FunSet = (a: Int) => s(a) && p(a)


  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: FunSet, p: Int => Boolean): Boolean =
    @tailrec
    def iter(a: Int): Boolean = {
      if contains(s, a) && p(a) then iter(a + 1)
      else if a > bound then true
      else false
    }

    iter(-bound)

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: FunSet, p: Int => Boolean): Boolean =
    @tailrec
    def iter(a: Int): Boolean =
      if contains(s, a) && p(a) then true
      else if a > bound then false
      else iter(a + 1)

    iter(-bound)

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: FunSet, f: Int => Int): FunSet =
    (a: Int) => exists(s, b => f(b) == a)

  /**
   * Displays the contents of a set
   */
  def toString(s: FunSet): String =
    val xs = for i <- (-bound to bound) if contains(s, i) yield i
    xs.mkString("{", ",", "}")

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: FunSet): Unit =
    println(toString(s))

object FunSets extends FunSets
