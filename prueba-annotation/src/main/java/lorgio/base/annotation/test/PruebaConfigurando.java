package lorgio.base.annotation.test;

import lorgio.base.annotation.core.ConfigurandoClase;
import lorgio.base.annotation.core.ConfigurandoMetodo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by lorgio on 20-03-15.
 */
public class PruebaConfigurando {

    public static void main(String[] args){

        System.out.println("Iniciando testeo");
        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<EjemploConfigurando> obj = EjemploConfigurando.class;

        //Procesando @ConfigurandoClase
        if(obj.isAnnotationPresent(ConfigurandoClase.class)){
            Annotation annotation = obj.getAnnotation(ConfigurandoClase.class);
            ConfigurandoClase configurandoClase = (ConfigurandoClase)annotation;

            System.out.printf("%nPriority : %s", configurandoClase.priority());
            System.out.printf("%nCreatedBy : %s", configurandoClase.createdBy());
            System.out.printf("%nTags :");

            int tagLength = configurandoClase.tags().length;
            for(String tag : configurandoClase.tags()){
                if(tagLength > 1){
                    System.out.print(tag + ", ");
                }else{
                    System.out.print(tag);
                }
                tagLength--;
            }

            System.out.printf("%nLastModified :%s%n%n", configurandoClase.lastModify());
        }

        // Procesando ConfigurandoMetodo
        for(Method method : obj.getDeclaredMethods()){
            if(method.isAnnotationPresent(ConfigurandoMetodo.class)){
                Annotation annotation = method.getAnnotation(ConfigurandoMetodo.class);
                ConfigurandoMetodo configurandoMetodo = (ConfigurandoMetodo)annotation;

                if(configurandoMetodo.enabled()){
                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }
                }else{
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }
            }
        }

        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
    }
}
