package lorgio.base.annotation.test;

import lorgio.base.annotation.core.ConfigurandoClase;
import lorgio.base.annotation.core.ConfigurandoMetodo;

import javax.print.attribute.standard.RequestingUserName;

/**
 * Created by lorgio on 20-03-15.
 */
@ConfigurandoClase(
        priority = ConfigurandoClase.Priority.HIGH,
        createdBy = "lorgio.trinidad",
        tags = {"bamboo, veracode"}
)
public class EjemploConfigurando {

    @ConfigurandoMetodo
    void pruebaA(){
        if (true){
            throw new RuntimeException("Este test siempre falla");
        }
    }

    @ConfigurandoMetodo(enabled = false)
    void pruebaB(){
        if(false){
            throw new RuntimeException("Este test siempre pasara");
        }
    }

    @ConfigurandoMetodo(enabled = true)
    void pruebaC(){
        if(10 > 1){
            //NO hacer nada, este test eimpre pasara
        }
    }
}
