import simpl._

def printJson[T](t: T)(implicit encoder: JsonEncoder[T]): String =
  encoder.asJson(t)

printJson(List(true, false))

printJson(List(List(true, false), List(true), List()))
