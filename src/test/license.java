/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import utils.DeEncrypter;

/**
 *
 * @author Guinness
 */
public class license {

    public static void main(String[] args) {
        String license = DeEncrypter.encrypt("trial version");
        System.out.println("license: " + license);
        String decrypt = DeEncrypter.decrypt(license);
        System.out.println("decrypt: " + decrypt);
    }

}
