package proxy;

public class Application
{

  /**
   * @param args
   */
  public static void main( String[] args )
  {
    Proxy p = new Proxy();
    p.meth2();
    p.meth1();
    p.meth2();
    System.out.println("Methode2 called " + p.getInvocationCount() + " times.");
  }
}
