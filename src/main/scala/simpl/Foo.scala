package simpl

case class Foo(s: String)

object Foo {
  implicit val fooEncoder = new JsonEncoder[Foo] {
    override def asJson(t: Foo): String = s"(Foo ${t.s})"
  }
}