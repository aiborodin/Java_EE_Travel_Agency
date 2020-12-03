package test;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class EightToThirteenDigitsDecorator implements NumberGenerator{
    @Inject @Delegate @Any
    private NumberGenerator numberGenerator;

    @Override
    public String generateSmallNumber(int a) {
        return "Decorated " + a + numberGenerator.generateSmallNumber(a);
    }
}
