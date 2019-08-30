public class HelloWorld {
    private final static String hello = "Hello World!";

    public HelloWorld() {
        this.print();
    }

    public void print() {
        System.out.println(hello);
    }

    public static String getHello() {
        return hello;
    }
}
