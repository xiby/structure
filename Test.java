public class Test {
    private Integer a;
    private static Integer b;
    public static synchronized void name() {
        b++;
    }

    public void test(){
        synchronized(a){
            a++;
        }
    } 
}