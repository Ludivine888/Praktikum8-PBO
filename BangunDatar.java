/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.praktikum8;

/**
 *
 * @author Lenovo
 */
public abstract class BangunDatar {
    String warna;

    public BangunDatar(String warna) {
        this.warna = warna;
    }

    // Abstract method: Tidak memiliki isi, wajib di-override subclass
    public abstract double hitungLuas();

    // Concrete method: Punya isi, bisa langsung dipakai
    public void cetakWarna() {
        System.out.println("Warna bangun ini adalah: " + warna);
    }
}