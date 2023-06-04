package PR6.Prototype;

class PrototypeTest implements Cloneable {
    private String propertyA;
    private String propertyB;

    public PrototypeTest(String propertyA, String propertyB) {
        this.propertyA = propertyA;
        this.propertyB = propertyB;
    }

    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    public void setPropertyB(String propertyB) {
        this.propertyB = propertyB;
    }

    public PrototypeTest clone() throws CloneNotSupportedException {
        return (PrototypeTest) super.clone();
    }

    @Override
    public String toString() {
        return "propertyA=" + propertyA + ", propertyB=" + propertyB;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeTest prototype = new PrototypeTest("valueA", "valueB");
        System.out.println(prototype.toString());
        PrototypeTest clonedPrototype = prototype.clone();
        System.out.println(clonedPrototype.toString());

        clonedPrototype.setPropertyA("newValueA");
        System.out.println(prototype.toString());
        System.out.println(clonedPrototype.toString());
    }
}
