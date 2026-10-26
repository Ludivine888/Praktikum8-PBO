/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.praktikum8;

/**
 *
 * @author Lenovo
 */
public class Smartphone implements AlatElektronik, Kamera {
    private String merk;

    public Smartphone(String merk) {
        this.merk = merk;
    }

    @Override
    public void nyalakan() {
        System.out.println(merk + " menyala. Booting OS...");
    }

    @Override
    public void matikan() {
        System.out.println(merk + " dimatikan. Goodbye.");
    }

    @Override
    public void ambilFoto() {
        System.out.println("Cekrek! Foto berhasil disimpan di galeri " + merk + ".");
    }
}
