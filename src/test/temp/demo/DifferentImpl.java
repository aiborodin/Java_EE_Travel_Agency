package temp.demo;

import javax.inject.Qualifier;

@Qualifier
public @interface DifferentImpl {
    Digits value();
    boolean odd();
}

