package temp;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class GeneradorHash {

    public static void main(String[] args) {
        String contrasenaAdmin = "admin";
        String hashGenerado = BCrypt.withDefaults().hashToString(12, contrasenaAdmin.toCharArray());

        // Imprimimos el hash resultante en la consola
        System.out.println("La contraseña es: " + contrasenaAdmin);
        System.out.println("El hash BCrypt generado es:");
        System.out.println(hashGenerado);
    }
}