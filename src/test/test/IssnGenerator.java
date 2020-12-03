package test;

public class IssnGenerator implements NumberGenerator{
    @Override
    public String generateSmallNumber(int a) {
        return "Small ISSN-number" + a;
    }

    @Override
    public String generateBigNumber() {
        return "Big ISSN-number";
    }
}
