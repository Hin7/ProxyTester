public class TestClassA {
    private int val1;
    private float val2;

    public TestClassA(Integer val1, Float val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public void setVal1(Integer val1) {
        this.val1 = val1;
    }

    public void setVal2(Float val2) {
        this.val2 = val2;
    }

    public Integer getVal1() {
        return val1;
    }

    public Float getVal2() {
        return val2;
    }

    @Override
    public String toString() {
        return String.format("TestClassA: val1 = %d, val2 = %.1f", val1, val2);
    }
}
