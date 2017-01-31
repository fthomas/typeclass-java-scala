package simpl

trait JsonEncoder[T] {
  def asJson(t: T): String
}

object JsonEncoder {
  implicit def boolEncoder = new JsonEncoder[Boolean] {
    override def asJson(t: Boolean): String =
      if (t) "true" else "false"
  }

  implicit def listEncoder[T](implicit tEncoder: JsonEncoder[T]) = new JsonEncoder[List[T]] {
    override def asJson(t: List[T]): String =
      t.map(tEncoder.asJson).mkString("[", ", ", "]")
  }
}
