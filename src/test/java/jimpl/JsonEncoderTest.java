package jimpl;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonEncoderTest {
    @Test
    public void testAsJson() throws Exception {
        final List<Boolean> l = Arrays.asList(true, false);

        final ListEncoder<Boolean> booleanListEncoder = new ListEncoder<>(new BoolEncoder());
        System.out.println(booleanListEncoder.asJson(l));
    }

    @Test
    public void testNestedLists() {
        final List<List<Boolean>> lists = Arrays.asList(Arrays.asList(true, false), Collections.singletonList(true), Collections.<Boolean>emptyList());

        ListEncoder<List<Boolean>> listListEncoder = new ListEncoder<>(new ListEncoder<>(new BoolEncoder()));
        System.out.println(listListEncoder.asJson(lists));
    }
}
