abstract class List[A] {

  def foldLeft[B](neutral: B)(op:(A, B) => B): B =
    this match {
      case Cons(head, tail) => op(head, tail.foldLeft(neutral)(op))
      case Nil() => neutral
    }


  def isEmpty: Boolean =
    foldLeft(true)((x,y) => false)
    /*this match {
      case Cons(_, _) => false
      case Nil() => true
    }*/

  def size: Int =
    this match {
      case Cons(_,tail) => 1 + tail.size
      case Nil() => 0
    }

  def head: Option[A] =
    this match {
      case Cons(head, _) => Option(head)
      case Nil() => Option.empty
    }

  def foreach(f: A => Unit): Unit =
    this match {
      case Cons(head, tail) =>
        f(head)
        tail.foreach(f)
      case Nil() =>
    }

  def append(a: A): List[A] =
    this match {
      case Cons(h, tail) =>Cons(h, tail.append(a))
      case Nil() => Cons(a, Nil())
    }

  def prepend(a: A): List[A] =
    Cons(a, this)

  def insert(i: Int, a: A): List[A] =
    this match {
      case Cons(head, tail) if i == 0 =>
        prepend(a)
      case Cons(head, tail) =>
        Cons(head, tail.insert(i-1, a))
      case Nil() => Nil().prepend(a)
    }

  def remove(a: A): List[A] =
    this match {
      case Cons(head, tail) if head == a => tail
      case Cons(head, tail) => Cons(head, tail.remove(a))
      case Nil() => this
    }
}


case class Cons[A](head2: A, tail: List[A]) extends List[A]
case class Nil[A]() extends List[A] {
  def foo = 1
}

object List {
  def apply[A](xs: A*): List[A] =
    xs.foldRight[List[A]](Nil())((a, list) => Cons(a, list))

  def foo = Cons(1, Nil())
}

val l: List[Int] = Cons(4, Cons(4,Cons(1, Nil())))

List(1, 2, 4)

Seq(123)


def sum(l: List[Int]): Int =
  l.foldLeft(0)(_ + _)

def prod(l: List[Int]): Int =
  l.foldLeft(1)(_ * _)

trait Monoid[A] {
  def neutral: A
  def op(a: A, b: A): A
  // op(neutral, x) = x
  // op(x, neutral) = x
  // op(a, op(b, c)) = op(op(a, b), c)
}

// !(a || b) = !a && !b
// !!a = a

val sumMonoid = new Monoid[Int] {
  override def neutral: Int = 0
  override def op(a: Int, b: Int): Int = a + b
}

val prodMonoid = new Monoid[Int] {
  override def neutral: Int = 1
  override def op(a: Int, b: Int): Int = a * b
}

val strMonoid = new Monoid[String] {
  override def neutral: String = ""
  override def op(a: String, b: String): String = s"$a$b"
}

new Monoid[Boolean] {
  override def neutral: Boolean = true
  override def op(a: Boolean, b: Boolean): Boolean = a && b
}

def sum2[A](l: List[A], m: Monoid[A]): A =
  l.foldLeft(m.neutral)(m.op)

def sum3[A](v: Vector[A], m: Monoid[A]): A =
  if (v.isEmpty) m.neutral
  else if (v.size == 1) v.head
  else {
    val (left, right) = v.splitAt(v.size / 2)
    val resL = sum3(left, m)
    val resR = sum3(right, m)
    m.op(resL, resR)
  }

sum3(Vector(1,2,3,4,5), prodMonoid)


sum2(l, sumMonoid)
sum2(l,prodMonoid)
sum2(List("a", "b", "c"), strMonoid)
List("a", "b", "c").foldLeft("")(_ ++ _)



//val s = Cons("b", Cons("a", Nil()))
//s.foldLeft("")(_ + _ )
//sum(s)
//Nil().isEmpty