public class TestClassB extends TestClassA {
    private double val3;
    private int val4;

    public TestClassB(Integer v1, Float v2, Double v3, Integer v4) {
        super(v1, v2);
        this.val3 = v3;
        this.val4 = v4;
    }

    public void setVal3(Double val3) {
        this.val3 = val3;
    }

    public void setVal4(Integer val4) {
        this.val4 = val4;
    }

    public Double getVal3() {
        return val3;
    }

    public Integer getVal4() {
        return val4;
    }

    @Override
    public String toString() {
        return String.format("TestClassB: val1 = %d, val2 = %.1f, val3 = %.1f, val4 = %d",
                getVal1(), getVal2(), val3, val4);
    }
}
