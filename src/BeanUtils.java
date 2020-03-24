/**
 * Lesson 6 SBT. reflection
 *
 * @author Hin7
 */

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Map<String, Method> getters = new HashMap<>();
        Map<String, Method> setters = new HashMap<>();

        for (Method m : from.getClass().getMethods())
            if (isGetter(m))
                getters.put(m.getName(), m);

        for (Method m : to.getClass().getMethods())
            if (isSetter(m))
                setters.put(m.getName(), m);

        for (String getName : getters.keySet()) {
            String setName = "set" + getName.substring(3);
            Method getterM = getters.get(getName);
            Method setterM = setters.get(setName);
            try {
                if (setterM != null) {
                    if (setterM.getParameterTypes()[0].isAssignableFrom(getterM.getReturnType())) {
                        setterM.invoke(to, getterM.invoke(from));
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();

            }
        }

    }

    public static boolean isGetter(Method method) {
        return method.getName().startsWith("get") &&
                method.getParameterTypes().length == 0 &&
                !void.class.equals(method.getReturnType());

    }

    public static boolean isSetter(Method method) {
        return method.getName().startsWith("set") &&
                method.getParameterTypes().length == 1;
    }
}
