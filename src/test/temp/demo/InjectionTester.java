package temp.demo;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class InjectionTester {
    @Inject @InjectTest
    Interface i;

    @Inject @InjectTest
    private String name;

    @Inject
    private int num;

    @Inject
    private double num2;

    @Override
    public String toString() {
        return name + " " + num + " " + num2;
    }
}
