package mikaelhg.example;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import static com.google.common.collect.Iterables.any;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business services for our example application.
 */
@Service
@lombok.extern.slf4j.Slf4j
public class ExampleService {

    @Resource private ExampleDao dao;

    private static Predicate<String> contains(final String match) {
        return new Predicate<String>() {
            @Override public boolean apply(final String input) {
                return match.toLowerCase().contains(input.toLowerCase());
            }
        };
    }

    private ImmutableList<String> badWords = ImmutableList.of(
            "republican", "religion", "true finns");

    /**
     * Add a new example, provided that it meets our stringent criteria.
     */
    @Transactional
    public boolean submitNewExample(final Example example) {
        if (any(badWords, contains(example.getName()))) {
            return false;
        } else {
            return null != dao.save(example);
        }
    }

}
