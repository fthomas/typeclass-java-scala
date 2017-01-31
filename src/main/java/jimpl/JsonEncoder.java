package jimpl;

public interface JsonEncoder<T> {
    String asJson(T t);
}
