public class TestClassC {
    private Number val1;
    private Number val2;

    public TestClassC(Number val1, Number val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public Number getVal1() {
        return val1;
    }

    public void setVal1(Number val1) {
        this.val1 = val1;
    }

    public Number getVal2() {
        return val2;
    }

    public void setVal2(Number val2) {
        this.val2 = val2;
    }

    @Override
    public String toString() {
        return "TestClassC: val1 = " + val1 + ", val2 = " + val2;
    }
}
