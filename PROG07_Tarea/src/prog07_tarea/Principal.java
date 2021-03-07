/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner tecladoMenu = new Scanner(System.in);
        int tipoCuenta;
        int opcion = 0;
        Banco banco = new Banco();
        
        CuentaBancaria cuentaEjemplo = new CuentaAhorro("dni1","nombre1","apellidos1",1500f,"iban1","interes1");
        
        banco.abrirCuenta(cuentaEjemplo);

        while (opcion != 7) 
        {
            System.out.println("Bienvenido al Banco Oficial Secreto de Programadores de Java. ¿En qué puedo ayudarle?");
            System.out.println("\n1: Abrir una nueva cuenta");
            System.out.println("2: Ver listado de cuentas disponibles");
            System.out.println("3: Obtener los datos de una cuenta concreta");
            System.out.println("4: Realizar un ingreso en una cuenta");
            System.out.println("5: Retirar efectivo de una cuenta");
            System.out.println("6: Consultar el saldo actual de una cuenta");
            System.out.print("7: Salir\n >");

            opcion = tecladoMenu.nextInt();

            switch (opcion) 
            {
                default:
                    System.out.println("Esa opción no está en el menú");
                    break;
                case 1:
                    //Método para abrir cuenta nueva
                    System.out.println("¿Qué tipo de cuenta desea abrir?");
                    System.out.println("1: Cuenta de Ahorro");
                    System.out.println("2: Cuenta Corriente Personal");
                    System.out.println("3: Cuenta Corriente de Empresa");
                    tipoCuenta = tecladoMenu.nextInt();
                    abrirCuenta(tipoCuenta, banco);
                    break;
                case 2:
                    //Método para ver listado de cuentas
                    verListadoCuentas(banco);
                    break;
                case 3:
                    //Método para obtener los datos de una cuenta concreta
                    verCuentaConcreta(banco);
                    break;
                case 4:
                    //Método para realizar un ingreso en cuenta
                    ingresarDinero(banco);
                    break;
                case 5:
                    //Método para retirar dinero de una cuenta
                    retirarDinero(banco);
                    break;
                case 6:
                    //Método para consultar el saldo actual de una cuenta
                    consultarSaldo(banco);
                    break;
                case 7:
                    System.out.println("Gracias por usar nuestros servicios.¡Hasta la próxima!");
                    break;
            }
            
            System.out.println("");
        }
        
        tecladoMenu.close();
    }
    
    public static boolean comprobarIBAN(String iban)
    {
        String regex = "ES\\d{20}";
        
        return Pattern.matches(regex, iban);
    }
    
    public static void abrirCuenta(int tipoCuenta, Banco banco)
    {
        CuentaBancaria nuevaCuenta;
        
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoF = new Scanner(System.in);
        
        String dni, nombre, apellidos, iban, tipoInteres;
        float saldoInicial, comision, maximoDescubierto;
        
        switch(tipoCuenta)
        {
            default:
                System.out.println("Tipo de cuenta no válido. Inroduzca una opción del 1 al 3.");
                break;
            case 1:
                System.out.println("\nDATOS DEL TITULAR:");
                System.out.println("DNI:");
                dni = teclado.nextLine();
                System.out.println("Nombre:");
                nombre = teclado.nextLine();
                System.out.println("Apellidos:");
                apellidos = teclado.nextLine();
                System.out.println("\nDATOS DE LA CUENTA");
                System.out.println("Saldo inicial:");
                saldoInicial = tecladoF.nextFloat();
                System.out.println("IBAN:");
                iban = teclado.nextLine();
                System.out.println("Tipo de interés:");
                tipoInteres = teclado.nextLine();
                
                if (comprobarIBAN(iban)) 
                {
                    nuevaCuenta = new CuentaAhorro(dni, nombre, apellidos, saldoInicial, iban, tipoInteres);
                
                    banco.abrirCuenta(nuevaCuenta);
                }
                else
                {
                    System.out.println("IBAN inválido");
                }
            
                break;
                
            case 2:
                System.out.println("\nDATOS DEL TITULAR:");
                System.out.println("DNI:");
                dni = teclado.nextLine();
                System.out.println("Nombre:");
                nombre = teclado.nextLine();
                System.out.println("Apellidos:");
                apellidos = teclado.nextLine();
                System.out.println("\nDATOS DE LA CUENTA");
                System.out.println("Saldo inicial:");
                saldoInicial = tecladoF.nextFloat();
                System.out.println("IBAN:");
                iban = teclado.nextLine();
                System.out.println("Comisión:");
                comision = teclado.nextFloat();
                
                if (comprobarIBAN(iban)) 
                {
                    nuevaCuenta = new CuentaCorrientePersonal(dni, nombre, apellidos, saldoInicial, iban, comision);
                
                    banco.abrirCuenta(nuevaCuenta);
                }
                else
                {
                    System.out.println("IBAN inválido");
                }
            
                break;
                
            case 3:
                System.out.println("\nDATOS DEL TITULAR:");
                System.out.println("DNI:");
                dni = teclado.nextLine();
                System.out.println("Nombre:");
                nombre = teclado.nextLine();
                System.out.println("Apellidos:");
                apellidos = teclado.nextLine();
                System.out.println("\nDATOS DE LA CUENTA");
                System.out.println("Saldo inicial:");
                saldoInicial = tecladoF.nextFloat();
                System.out.println("IBAN:");
                iban = teclado.nextLine();
                System.out.println("Tipo de interés:");
                tipoInteres = teclado.nextLine();
                System.out.println("Máximo descubierto permitido:");
                maximoDescubierto = tecladoF.nextFloat();
                
                if (comprobarIBAN(iban)) 
                {
                    nuevaCuenta = new CuentaCorrienteEmpresa(dni, nombre, apellidos, saldoInicial, iban, tipoInteres, maximoDescubierto);
                
                    banco.abrirCuenta(nuevaCuenta);
                }
                else
                {
                    System.out.println("IBAN inválido");
                }
            
                break;
        }
    }
    
    public static void verListadoCuentas(Banco banco)
    {
        String[] cuentas = banco.listadoCuentas();
        
        for(String s: cuentas)
        {
            System.out.println(s);
        }
    }
    
    public static void verCuentaConcreta(Banco banco)
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Introduzca el IBAN de la cuenta que quiera consultar:");
        String iban = teclado.nextLine();
        
        if (banco.informacionCuenta(iban)==null) 
        {
            System.out.println("La cuenta con el IBAN especificado no existe.");
        }
        else
        {
            System.out.println(banco.informacionCuenta(iban));
        }
    }
    
    public static void ingresarDinero(Banco banco)
    {
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoF = new Scanner(System.in);
        
        System.out.println("Introduzca el IBAN de la cuenta a la que quiere hacer el ingreso:");
        String iban = teclado.nextLine();
        System.out.println("Introduzca la cantidad que desee ingresar:");
        float cantidad = tecladoF.nextFloat();
        
        if(banco.ingresoCuenta(iban, cantidad)==true)
        {
            System.out.println("Ingreso realizado con éxito");
        }
        else
        {
            System.out.println("El IBAN introducido no pertenece a ninguna cuenta");
        }
    }
    
    public static void retirarDinero(Banco banco)
    {
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoF = new Scanner(System.in);
        
        System.out.println("Introduzca el IBAN de la cuenta de la que quiere retirar dinero:");
        String iban = teclado.nextLine();
        System.out.println("Introduzca la cantidad que desee retirar:");
        float cantidad = tecladoF.nextFloat();
        
        if(banco.retiradaCuenta(iban, cantidad))
        {
            System.out.println("Retirada realizada con éxito");
        }
        else
        {
            System.out.println("El IBAN no pertenece a ninguna cuenta o no tiene saldo suficiente.");
        }
    }
    
    public static void consultarSaldo(Banco banco)
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Introduzca el IBAN de la cuenta que desee consultar:");
        String iban = teclado.nextLine();
        
        float saldo = banco.obtenerSaldo(iban);
        
        if(saldo == -1)
        {
            System.out.println("El IBAN introducido no pertenece a ninguna cuenta");
        }
        else
        {
            System.out.println("El saldo de la cuenta es de "+saldo+" €");
        }
    }

}
