
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
case class Nil[A]() extends List[A]

val l: List[Int] = Cons(4, Cons(4,Cons(1, Nil())))


trait Monoid[T] {
  def neutral: T
  def op(a: T, b: T): T
}

implicit val x = new Monoid[String] {
  override def neutral: String = ""
  override def op(a: String, b: String): String = b + a
}

def sum[T](l: List[T])(implicit monoid: Monoid[T]): T =
  l.foldLeft(monoid.neutral)(monoid.op)

def prod(l: List[Int]): Int =
  l.foldLeft(1)(_ * _)


//sum(l)
prod(l)
val s = Cons("b", Cons("a", Nil()))
//s.foldLeft("")(_ + _ )
sum(s)
Nil().isEmpty