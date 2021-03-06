/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clase_vero_2semanafebrero;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Repaso_Expresiones_Regulares {
    
    public static void main(String[] args)
    {
        //Cadena de la que queremos sacar un patrón
        String cadena1_true = "a";
        String cadena1_false = "*";
        
        System.out.println("Prueba de \\w:\n");
        
        //Creamos la expresión regular
        //Con esta expresión regular comprobamos que la cadena tiene un carácter alfanumérico (a-z, A-Z, 0-9)
        String regex1 = "\\w";
        
        //Esto devolverá true porque la cadena está formada por una sola letra
        System.out.println("La cadena1_true coincide y contiene el patrón \\w: "+Pattern.matches(regex1,cadena1_true));
        
        //Esto devolverá false porque la cadena está compuesta por un carácter especial, en este caso un asterisco
        System.out.println("La cadena1_false coincide y contiene el patrón \\w: "+Pattern.matches(regex1,cadena1_false));
        
        //------------------------------------------------------------------
        
        //Cadena de la que queremos sacar un patrón
        String cadena2_true = "1";
        String cadena2_false = "a";
        
        System.out.println("\nPrueba de \\d:\n");
        
        //Creamos la expresión regular
        //Con esta expresión regular comprobamos que la cadena tiene un dígito
        String regex2 = "\\d";
        
        //Esto devolverá true porque la cadena está formada por un solo dígito
        System.out.println("La cadena1_true coincide y contiene el patrón \\d: "+Pattern.matches(regex2,cadena2_true));
        
        //Esto devolverá false porque la cadena está compuesta por una letra
        System.out.println("La cadena1_false coincide y contiene el patrón \\d: "+Pattern.matches(regex2,cadena2_false));
        
        //-------------------------------------------------------------------
        
        //Cadena de la que queremos sacar un patrón
        String cadena3_true1 = "1";
        String cadena3_true2 = "a";
        String cadena3_true3 = "*";
        
        System.out.println("\nPrueba de . :\n");
        
        //Creamos la expresión regular
        //Con esta expresión regular comprobamos que la cadena tiene un dígito
        String regex3 = ".";
        
        //Esto devolverá true porque la expresión regular acepta cualquier cosa que le pasemos
        System.out.println("La cadena3_true1 coincide y contiene el patrón .: "+Pattern.matches(regex3,cadena3_true1));
        System.out.println("La cadena3_true2 coincide y contiene el patrón .: "+Pattern.matches(regex3,cadena3_true2));
        System.out.println("La cadena3_true3 coincide y contiene el patrón .: "+Pattern.matches(regex3,cadena3_true3));
        
        //-------------------------------------------------------------------
        
        //¿Cómo podemos indicar que ese patrón debe repetirse?
        
        System.out.println("\nPrueba de repetición de patrones con +: \n");
        
        //Con el signo + (debe aparecer una o más veces)
        String regex4 = "\\w+";
        
        String c1 = "a";
        String c2 = "aa";
        String c3 = "*a";
        
        System.out.println("Cadenas a, aa, *a :\n");
        
        //Devuelve true porque están compuestas por una o más letras
        System.out.println(Pattern.matches(regex4,c1));
        System.out.println(Pattern.matches(regex4,c2));
        
        //Devuelve false porque además de una letra tiene un asterisco (carácter especial)
        System.out.println(Pattern.matches(regex4,c3));
        
        System.out.println("\nPrueba de repetición de patrones con *: \n");
        
        //Con el signo * (puede aparecer 0 o más veces)
        String regex5 = "\\d*";
        
        String c4 = "";
        String c5 = "1";
        String c6 = "1111111111111111111111111111";
        String c7 = "a";
        
        //Devuelve true porque todas cumplen con la condición de que haya 0 o más dígitos
        System.out.println(Pattern.matches(regex5,c4));
        System.out.println(Pattern.matches(regex5,c5));
        System.out.println(Pattern.matches(regex5,c6));
        
        //Devuelve false porque la cadena no está vacía y lo que contiene es una letra. Para que sea true 
        //debe ser cadena vacía o con algún dígito.
        System.out.println(Pattern.matches(regex5,c7));
        
        //Con el signo ? (Puede aparecer o no aparecer)
        String regex6 = "\\w?";
        
        System.out.println("\nPrueba de repetición de patrones con ?: \n");
        
        String c8 = "a";
        String c9 = "";
        String c10 = "*";
        String c11 = "aa";
        
        //Esto devolverá true porque en todas las cadenas se da el caso de que aparece o no un carácter alfanumérico.
        System.out.println(Pattern.matches(regex6,c8));
        System.out.println(Pattern.matches(regex6,c9));
        
        //Esto devolverá false porque el carácter aparece más de una vez, incumpliendo con la condición.
        //Además tampoco acepta el asterisco (carácter especial) por el mismo motivo que el \\d*
        System.out.println(Pattern.matches(regex6,c10));
        System.out.println(Pattern.matches(regex6,c11));
        
        //Estableciéndole un rango personalizado entre llaves {}
        String regex7 = "\\d{5}";
        String regex8 = "\\d{3,5}";
        String regex9 = "\\d{3,}";
        String regex10 = "\\d{0,5}";
        
        System.out.println("\nPrueba de repetición de patrones con rangos personalizados: \n");
        
        String c12 = "12345";
        String c13 = "123";
        String c14 = "123456789";
        String c15 = "1";
        
        //Devuelve true porque es una secuencia de 5 dígitos
        System.out.println(Pattern.matches(regex7,c12)+"\n");
        //Devuelve true porque acepta cualquier rango de dígitos que vaya de 3 a 5
        System.out.println(Pattern.matches(regex8,c12));
        System.out.println(Pattern.matches(regex8,c13)+"\n");
        //Esto acepta un rango de 3 hasta infinito, así que devolverá true siempre que haya más de 3 dígitos
        System.out.println(Pattern.matches(regex9,c13));
        System.out.println(Pattern.matches(regex9,c12));
        System.out.println(Pattern.matches(regex9,c14));
        System.out.println(Pattern.matches(regex9,c15)+"\n");
        //Esto acepta un rango desde 0 a 5. Si se pasa de 5 devuelve false
        System.out.println(Pattern.matches(regex10,c15));
        System.out.println(Pattern.matches(regex10,c13));
        System.out.println(Pattern.matches(regex10,c12));
        System.out.println(Pattern.matches(regex10,c14)+"\n");
        
        //--------------------------------------------------------------
        
        //¿Se pueden combinar estas expresiones?
        
        //Sí se pueden. Todas ellas se pueden combinar para aumentar la complejidad del patrón que queremos encontrar.
        
        //En este ejemplo podemos validar que una matrícula de coche tiene el formato correcto:
        
        String regex11 = "\\d{4}[A-Z]{3}";
        
        String matricula_correcta = "1234AAA";
        String matricula_incorrecta = "1AAAA";
        String matricula_incorrecta1 = "1234AA";
        
        System.out.println("\nPrueba de validación de matrícula:\n");
        
        System.out.println(Pattern.matches(regex11,matricula_correcta));
        System.out.println(Pattern.matches(regex11,matricula_incorrecta));
        System.out.println(Pattern.matches(regex11,matricula_incorrecta1));
        
        
    }
    
}
