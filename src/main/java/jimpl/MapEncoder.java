package jimpl;

import java.util.Map;
import java.util.stream.Collectors;

public class MapEncoder<K, V> implements JsonEncoder<Map<K, V>> {
    private final JsonEncoder<K> keyEncoder;
    private final JsonEncoder<V> valueEncoder;

    public MapEncoder(JsonEncoder<K> keyEncoder, JsonEncoder<V> valueEncoder) {
        this.keyEncoder = keyEncoder;
        this.valueEncoder = valueEncoder;
    }

    @Override
    public String asJson(Map<K, V> kvMap) {
        return kvMap.entrySet().stream().map(e -> keyEncoder.asJson(e.getKey()) + ":" +
                valueEncoder.asJson(e.getValue())).collect(Collectors.joining(", ", "{", "}"));

    }
}
