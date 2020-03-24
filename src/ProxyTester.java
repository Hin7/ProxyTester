
import java.lang.reflect.*;

/**
 * Task for lesson 6 SBT. Reflection/
 *
 * @author Hin7
 * @version 1.0
 */

public class ProxyTester {

    public static String MONDAY = "MONDALaY";
    public static String TUESDAY = "TUESDAY";
    public static String WEDNESDAY = "WEDNESDAY";
    public static String THURSDAY = "THURSDAY";
    public static String FRIDAY = "FRIDAY";
    public static String SATURDAY = "SATURDAY";
    public static String SUNDAY = "SUNDAY";

    public static void main(String[] args) {
        String s_r = "ReflectionTest";
        ProxyTester pt = new ProxyTester();
        pt.PrintMetods(s_r);
        Integer i = 5;
        pt.PrintMetods(i);
        pt.PrintMetods(pt);
        pt.PrintConstants(pt);


        IKubber kub = (IKubber) Proxy.newProxyInstance(Kubber.class.getClassLoader(), Kubber.class.getInterfaces(),
                new CashHandler(new Kubber()));
        System.out.println("=============================");
        System.out.println("Тест кэширующего прокси");
        System.out.println("Куб " + i + " = " + kub.makeKube(new Double(i)));
        i = 6;
        System.out.println("Куб " + i + " = " + kub.makeKube(new Double(i)));
        i = 5;
        System.out.println("Куб " + i + " = " + kub.makeKube(new Double(i)));
        System.out.println("Куб " + i + " = " + kub.makeKube((double) i));
        System.out.println("Куб " + i + " = " + kub.makeKube((double) i));
        i = 10;
        System.out.println("Куб " + i + " = " + kub.makeKube((double) i));

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Тест BeanUtils.assign");
        TestClassA valA1 = new TestClassA(1, 2.0f);
        TestClassA valA2 = new TestClassA(3, 4.0f);
        TestClassB valB1 = new TestClassB(5, 6.0f, 7.0d, 8);
        TestClassB valB2 = new TestClassB(9, 10.0f, 11.0d, 12);
        TestClassC valC1 = new TestClassC(13, 14);

        System.out.println("A1 = " + valA1);
        System.out.println("A2 = " + valA2);
        System.out.println("B1 = " + valB1);
        System.out.println("B2 = " + valB2);
        System.out.println("C1 = " + valC1);
        BeanUtils.assign(valA2, valA1);
        System.out.println("После А1 -> A2");
        System.out.println("A1 = " + valA1);
        System.out.println("A2 = " + valA2);
        BeanUtils.assign(valB2, valA1);
        System.out.println("После А1 -> B2");
        System.out.println("A1 = " + valA1);
        System.out.println("B2 = " + valB2);
        BeanUtils.assign(valA2, valB1);
        System.out.println("После B1 -> A2");
        System.out.println("A2 = " + valA2);
        System.out.println("B1 = " + valB1);
        BeanUtils.assign(valA2, valC1);
        System.out.println("После C1 -> A2 (типы не совместимы)");
        System.out.println("A2 = " + valA2);
        System.out.println("C1 = " + valC1);
        BeanUtils.assign(valC1, valA1);
        System.out.println("После A1 -> C1");
        System.out.println("A1 = " + valA1);
        System.out.println("C1 = " + valC1);

    }

    private void PrintMetods(Object o) {
        Class<?> cl = o.getClass();
        String className = cl.getName();
        System.out.println("----------------------");
        System.out.println("Имя класса: " + className);
        System.out.println("Все public Методы:");
        int count = 0;
        for (Method m : cl.getMethods()) {
            System.out.println((count++) + " " + Modifier.toString(m.getModifiers()) + " " + m.getName());
        }
        System.out.println("---");
        System.out.println("Все объявленые методы");
        count = 0;
        for (Method m : cl.getDeclaredMethods()) {
            System.out.println((count++) + " " + Modifier.toString(m.getModifiers()) + " " + m.getName());
        }
        System.out.println("---");
        System.out.println("Все геттеры:");
        count = 0;
        for (Method m : cl.getDeclaredMethods()) {
            String mName = m.getName();
            if (BeanUtils.isGetter(m)) {
                System.out.println((count++) + " " + mName);
            }
        }
        System.out.println("----------------------");
    }

    private void PrintConstants(Object o) {
        Class<?> cl = o.getClass();
        System.out.println("Константы:");
        int count = 0;
        for (Field fl : cl.getFields()) {
            if (fl.getType().getName() == "java.lang.String") {
                String value = " ";
                try {
                    value = (String) fl.get(o);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value.equals(fl.getName()))
                    System.out.println((count++) + " " + fl.getName() + " = " + value);
                else
                    System.out.println((count++) + " " + fl.getName() + " = " + value + " не равно!");
            }
        }
    }
}
