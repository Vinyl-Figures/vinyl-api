package br.com.acta.vinylpgapi.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Hash {
    private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
    private static final String PEPPER = System.getenv("PEPPER");

    private static char[] applyPepper(String password){
        return (password + PEPPER).toCharArray();
    }

    public static String generateHash(String password){
        return ARGON2.hash(4, 262144, 2, applyPepper(password));
    }

    public static boolean validatePassword(String hashedPassword, String sentPassword){
        return ARGON2.verify(hashedPassword, applyPepper(sentPassword));
    }
}