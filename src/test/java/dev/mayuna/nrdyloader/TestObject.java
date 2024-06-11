package dev.mayuna.nrdyloader;

public class TestObject {

    private String someString = "This is some string";
    private int someInt = 42;
    private Integer someInteger = 442;
    private boolean someBoolean = true;
    private double someDouble = 3.14;
    private float someFloatAgain = 3.14f;
    private long someLongAgain = 42L;
    private short someShortAgain = 42;
    private byte someByteAgain = 42;
    private char someCharAgain = 'c';
    private NestedObjectTest nestedObject = new NestedObjectTest();
    private TestEnum someEnum = TestEnum.SOME_OTHER_ENUM_VALUE;
    private String nullValue = null;
    private Boolean someBooleanAgain = true;
    private Double someDoubleAgain = 3.14;
    private Float someFloat = 3.14f;
    private Long someLong = 42L;
    private Short someShort = 42;
    private Byte someByte = 42;
    private Character someChar = 'c';
    public String publicString = "This is a public string";
    //private List<String> someList = Arrays.asList("This", "is", "a", "list");
    //private String[] someArray = {"This", "is", "an", "array"};
    //private int[] someIntArray = {69, 666, 420, 1337};
    //private NestedObjectTest[] nestedObjectArray = {new NestedObjectTest(), new NestedObjectTest()};
    //private TestEnum[] someEnumArray = {TestEnum.SOME_ENUM_VALUE, TestEnum.SOME_OTHER_ENUM_VALUE};
    //private List<String> someList = new ArrayList<>(Arrays.asList("This", "is", "a", "list"));
    //private Map<String, String> someMap = new HashMap<>();

    public TestObject() {
        makeEmpty();
    }

    public void makeEmpty() {
        someString = "";
        someInt = 0;
        someInteger = 0;
        someBoolean = false;
        someDouble = 0.0;
        nestedObject.makeEmpty();
        someEnum = TestEnum.SOME_ENUM_VALUE;
        someFloatAgain = 0.0f;
        someLongAgain = 0L;
        someShortAgain = 0;
        someByteAgain = 0;
        someCharAgain = ' ';
        nullValue = null;
        someBooleanAgain = false;
        someDoubleAgain = 0.0;
        someFloat = 0.0f;
        someLong = 0L;
        someShort = 0;
        someByte = 0;
        someChar = ' ';
        publicString = "";
        //someArray = new String[0];
        //someIntArray = new int[0];
        //nestedObjectArray = new NestedObjectTest[0];
        //someEnumArray = new TestEnum[0];
        //someList = new ArrayList<>();
        //someMap = new HashMap<>();
    }

    public void fillData() {
        someString = "This is some string";
        someInt = 42;
        someInteger = 442;
        someBoolean = true;
        someDouble = 3.14;
        nestedObject.fill();
        someEnum = TestEnum.SOME_OTHER_ENUM_VALUE;
        someFloatAgain = 3.14f;
        someLongAgain = 42L;
        someShortAgain = 42;
        someByteAgain = 42;
        someCharAgain = 'c';
        nullValue = null;
        someBooleanAgain = true;
        someDoubleAgain = 3.14;
        someFloat = 3.14f;
        someLong = 42L;
        someShort = 42;
        someByte = 42;
        someChar = 'c';
        publicString = "This is a public string";
        //someArray = new String[]{"This", "is", "an", "array"};
        //someIntArray = new int[]{69, 666, 420, 1337};
        //nestedObjectArray = new NestedObjectTest[]{new NestedObjectTest(), new NestedObjectTest()};
        //someEnumArray = new TestEnum[]{TestEnum.SOME_ENUM_VALUE, TestEnum.SOME_OTHER_ENUM_VALUE};
        //someList = new ArrayList<>(Arrays.asList("This", "is", "a", "list"));
        //someMap = new HashMap<>();
        //someMap.put("key1", "value1");
        //someMap.put("key2", "value2");
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "someString='" + someString + '\'' +
                ", someInt=" + someInt +
                ", someInteger=" + someInteger +
                ", someBoolean=" + someBoolean +
                ", someDouble=" + someDouble +
                ", nestedObject=" + nestedObject +
                ", someEnum=" + someEnum +
                //", someArray=" + Arrays.toString(someArray) +
                //", someIntArray=" + Arrays.toString(someIntArray) +
                //", nestedObjectArray=" + Arrays.toString(nestedObjectArray) +
                //", someEnumArray=" + Arrays.toString(someEnumArray) +
                //", someList=" + someList +
                //", someMap=" + someMap +
                '}';
    }
}
