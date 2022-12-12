//
// Copyright (C) 2006 Media Saturn Systemzentrale. All rights reserved.
//
// $Project:FJP$
// $Revision:1$
// $Date:29.11.2008 19:47:22$
// $Log[10]:
//  1    FJP       1.0         29.11.2008 19:47:22    Laumeyer Leonhard  
// $
//

package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
public class MapPerReflection {
    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final Class mapClass = Class.forName("java.util.HashMap");
        final Constructor defaultCtor = mapClass.getConstructor();

        final Object o = defaultCtor.newInstance((Object[]) null);

        System.out.println("Created object: " + o);

        final Method put = mapClass.getMethod("put", Object.class, Object.class);
        put.invoke(o, "k1", "v1");
        put.invoke(o, "k2", "v2");

        System.out.println("Filled map: " + o);

        final Method keySetMeth = mapClass.getMethod("keySet");
        final Object ks = keySetMeth.invoke(o);

        final Method iterMeth = ks.getClass().getMethod("iterator");
        final Object iter = iterMeth.invoke(ks);

        final Method next = iter.getClass().getMethod("next");
        final Method hasNext = iter.getClass().getMethod("hasNext");

        while (((Boolean) hasNext.invoke(iter)).booleanValue()) {
            final Object key = next.invoke(iter);
            System.out.println("Map key: " + key);
        }
    }
}
