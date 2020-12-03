package temp.demo;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

public class Values2 {
    @Produces @Alternative
    private String name = "Alex";

    @Produces @Alternative
    private int num = 100;

    @Produces @Alternative
    private double skill = 1.1;
}
