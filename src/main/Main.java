package main;

import interfaz.ConsolaInterfazUsuario;
import interfaz.InterfazUsuario;


public class Main {
    public static void main(String[] args) {

        InterfazUsuario interfazUsuario = new ConsolaInterfazUsuario();

        interfazUsuario.iniciar();
    }
}
