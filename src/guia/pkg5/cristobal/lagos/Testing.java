/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guia.pkg5.cristobal.lagos;

import ClasesEInterfaces.Validator;

/**
 *
 * @author cristolagos
 */
public class Testing {
    public static void main(String[] args) {
        System.out.println(new Validator().align("Prueba1", 1) + "\t" + new Validator().align("palabra", 2) + "\t" + new Validator().align("pal", 1));
        System.out.println(new Validator().align("Prueba2", 1) + "\t" + new Validator().align("pal", 2) + "\t" + new Validator().align("pal", 1));
        System.out.println(new Validator().align("Prueba3", 1) + "\t" + new Validator().align("palabraaaaaaa", 2) + "\t" + new Validator().align("pal", 1));
    }
    
}
