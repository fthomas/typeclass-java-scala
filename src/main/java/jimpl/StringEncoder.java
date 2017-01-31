package jimpl;

public class StringEncoder implements JsonEncoder<String> {
    @Override
    public String asJson(String s) {
        return "\"" + s + "\"";
    }
}
