import simpl._

def printJson[T](t: T)(implicit encoder: JsonEncoder[T]): String =
  encoder.asJson(t)


printJson(List(Foo("false")))

printJson(List(true, false))

printJson(List(List(true, false),
  List(true),
  List()))(
  JsonEncoder.listEncoder(
  JsonEncoder.listEncoder(
    JsonEncoder.boolEncoder)))
