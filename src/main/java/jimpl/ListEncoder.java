package jimpl;

import java.util.List;
import java.util.stream.Collectors;

public class ListEncoder<T> implements JsonEncoder<List<T>> {
    private final JsonEncoder<T> tEncoder;

    public ListEncoder(JsonEncoder<T> tEncoder) {
        this.tEncoder = tEncoder;
    }

    @Override
    public String asJson(List<T> ts) {
        return ts.stream().map(tEncoder::asJson)
                .collect(Collectors.joining(", ", "[", "]"));
    }

}
