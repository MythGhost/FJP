package proxy;

public class RealSubject implements Subject
{
  public RealSubject()
  {
  }

  public void meth1()
  {
    System.out.println("Methode1 called.");
  }

  public void meth2()
  {
    System.out.println("Methode2 called.");
  }
}