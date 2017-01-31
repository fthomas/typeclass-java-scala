package jimpl;

public class BoolEncoder implements JsonEncoder<Boolean> {
    @Override
    public String asJson(Boolean aBoolean) {
        return aBoolean ? "true" : "false";
    }
}
