package jimpl;

import org.testng.annotations.Test;

import java.util.*;

public class JsonEncoderTest {
    @Test
    public void testAsJsonBool() throws Exception {
        BoolEncoder boolEncoder = new BoolEncoder();
        System.out.println(boolEncoder.asJson(true));
    }

    @Test
    public void testAsJson() throws Exception {
        final List<Boolean> l = Arrays.asList(true, false);

        final JsonEncoder<List<Boolean>> booleanListEncoder = new ListEncoder<>(new BoolEncoder());
        System.out.println(booleanListEncoder.asJson(l));
    }


    @Test
    public void testNestedLists() {
        final List<List<Boolean>> lists = Arrays.asList(
                Arrays.asList(true, false),
                Collections.singletonList(true),
                Collections.<Boolean>emptyList());

        final JsonEncoder<List<List<Boolean>>> listListEncoder =
                new ListEncoder<>(new ListEncoder<>(new BoolEncoder()));

        System.out.println(listListEncoder.asJson(lists));
    }

    @Test
    public void testMap() {
        Map<String, Boolean> map = new HashMap<>();
        //map.put("eins", true);
        //map.put("zwei", false);
        MapEncoder<String, Boolean> mapEncoder = new MapEncoder<>(new StringEncoder(), new BoolEncoder());
        System.out.println(mapEncoder.asJson(map));
    }

}
