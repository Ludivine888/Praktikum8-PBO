/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.praktikum8;

/**
 *
 * @author Lenovo
 */
public class TesBangunDatar {
    public static void main(String[] args) {
        Lingkaran lingkaran = new Lingkaran("Merah", 5);

        lingkaran.cetakWarna();
        System.out.println("Luas lingkaran: " + lingkaran.hitungLuas());
    }
}
