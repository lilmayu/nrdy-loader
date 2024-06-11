package dev.mayuna.nrdyloader;

public class NestedObjectTest {

    private String nestedString = "This is a nested string";

    public NestedObjectTest() {
        makeEmpty();
    }

    public void makeEmpty() {
        nestedString = "";
    }

    public void fill() {
        nestedString = "This is a nested string";
    }

    @Override
    public String toString() {
        return "NestedObjectTest{" +
                "nestedString='" + nestedString + '\'' +
                '}';
    }
}
