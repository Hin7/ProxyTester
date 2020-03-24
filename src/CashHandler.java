import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * CachHandler
 */
public class CashHandler implements InvocationHandler{
    private final Object delegate;
    private Object value = null;
    private Object result = null;

    public CashHandler(Object delegate){
        this.delegate = delegate;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(value == null || !value.equals(args[0])){
            value = args[0];
            //result = method.invoke(proxy, args);
            result = method.invoke(delegate, args);
            System.out.println("Прямое вычисление");
        }
        else
            System.out.println("Кэшированое значение");
        return result;
    }
}
