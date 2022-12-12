package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Aufruf
{

  public static void main( String[] args ) throws SecurityException, NoSuchMethodException,
      IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    Class<Complex> cls = Complex.class;

    Method valueOf = cls.getMethod( "valueOf", double.class, double.class );
    Complex c = (Complex) valueOf.invoke( null, new Double( -1.0 ), new Double( 1.0 ) );
    Complex d = (Complex) valueOf.invoke( null, new Double( 1.0 ), new Double( -1.0 ) );

    // Complex c = Complex.valueOf(-1.0, 1.0);
    // Complex d = Complex.valueOf(1.0, -1.0);

    Method add = cls.getMethod( "add", Complex.class );
    Complex sum = (Complex) add.invoke( c, d );
    System.out.println( sum );

    // Complex sum = c.add(d);
  }
}
