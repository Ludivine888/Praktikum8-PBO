# Praktikum 8: Abstraction (Abstract Class & Interface) dan Polimorfisme Dinamis

## Identitas Mahasiswa
* **Nama:** Ibrey Diba Tarigan
* **NIM:** L0325027
* **Program Studi:** S-1 Informatika (PSDKU Kebumen)
* **Fakultas:** Fakultas Teknologi Informasi dan Sains Data (FATISDA)
* **Universitas:** Universitas Sebelas Maret (UNS)

---

## Deskripsi Singkat Praktikum
Praktikum ini berfokus pada implementasi konsep abstraksi dan polimorfisme pada bahasa pemrograman Java yang terbagi ke dalam 3 studi kasus:
1. **Abstract Class & Abstract Method:** Penerapan kelas abstrak `BangunDatar` dan kelas konkret `Lingkaran`.
2. **Dynamic Polymorphism (Pewarisan & Overriding):** Hirarki superclass `Hewan` dengan subclass `Kucing` dan `Anjing` yang dieksekusi secara polimorfis melalui array.
3. **Interface & Multiple Implementation:** Simulasi perangkat `Smartphone` yang mengimplementasikan lebih dari satu interface (`AlatElektronik` dan `Kamera`).

---

## 1. Studi Kasus 1: Abstract Class (Bangun Datar)

### A. Hubungan Antar-Class
* **`BangunDatar` (Abstract Class):** Berperan sebagai kerangka dasar (*blueprint*). Kelas ini tidak dapat diinstansiasi secara langsung menggunakan kata kunci `new`. Memiliki atribut `warna`, method konkret `cetakWarna()`, dan method abstrak `hitungLuas()`.
* **`Lingkaran` (Concrete Subclass):** Mewarisi (*extends*) `BangunDatar`. Kelas ini **wajib** mengimplementasikan (meng-*override*) seluruh method abstrak dari induknya, yaitu `hitungLuas()`, serta memanggil konstruktor induk melalui `super(warna)`.
* **`TesBangunDatar` (Main Class):** Menginstansiasi objek `Lingkaran` dan menguji pemanggilan method warisan serta method hasil perhitungan luas.

### B. Source Code

#### `BangunDatar.java`
```java
package com.praktikum8;

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
```

#### `Lingkaran.java`
```java
package com.praktikum8;

public class Lingkaran extends BangunDatar {
    private double jariJari;

    public Lingkaran(String warna, double jariJari) {
        super(warna);
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() {
        return Math.PI * jariJari * jariJari;
    }
}
```

#### `TesBangunDatar.java`
```java
package com.praktikum8;

public class TesBangunDatar {
    public static void main(String[] args) {
        Lingkaran lingkaran = new Lingkaran("Merah", 5);

        lingkaran.cetakWarna();
        System.out.println("Luas lingkaran: " + lingkaran.hitungLuas());
    }
}
```

### C. Penjelasan Logika Kode
1. Kata kunci `abstract` pada `BangunDatar` mencegah instansiasi objek umum bangun datar yang luasnya belum dapat ditentukan formulanya secara spesifik.
2. Method `cetakWarna()` bersifat konkret (memiliki tubuh kode `{ ... }`), sehingga subclass `Lingkaran` dapat langsung menggunakannya tanpa perlu menulis ulang logika pencetakan warna.
3. Method `hitungLuas()` dideklarasikan sebagai `abstract`, memaksa setiap subclass yang mewarisi `BangunDatar` untuk memberikan rumus perhitungannya masing-masing.
4. Pada `Lingkaran`, formula luas dihitung menggunakan konstanta bawaan Java `Math.PI * jariJari * jariJari`.

### D. Output Eksekusi Terminal
```text
Warna bangun ini adalah: Merah
Luas lingkaran: 78.53981633974483
```

---

## 2. Studi Kasus 2: Dynamic Polymorphism (Hirarki Suara Hewan)

### A. Hubungan Antar-Class
* **`Hewan` (Superclass):** Memiliki method `bersuara()` yang menghasilkan suara umum.
* **`Kucing` (Subclass):** Mewarisi `Hewan` dan meng-*override* method `bersuara()` untuk menghasilkan pesan spesifik suara kucing.
* **`Anjing` (Subclass):** Mewarisi `Hewan` dan meng-*override* method `bersuara()` untuk menghasilkan pesan spesifik suara anjing.
* **`PoliTester` (Main Class):** Mendemonstrasikan *Dynamic Method Dispatch* (Polimorfisme Dinamis) menggunakan array bertipe superclass `Hewan[]` yang menampung beragam objek dari subclass yang berbeda.

### B. Source Code

#### `Hewan.java`
```java
package com.praktikum8;

public class Hewan {
    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara yang tidak spesifik.");
    }
}
```

#### `Kucing.java`
```java
package com.praktikum8;

public class Kucing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("Kucing: Meow meow!");
    }
}
```

#### `Anjing.java`
```java
package com.praktikum8;

public class Anjing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("Anjing: Guk guk!");
    }
}
```

#### `PoliTester.java`
```java
package com.praktikum8;

public class PoliTester {
    public static void main(String[] args) {
        Hewan[] daftarHewan = new Hewan[3];
        daftarHewan[0] = new Hewan();
        daftarHewan[1] = new Kucing();
        daftarHewan[2] = new Anjing();

        for (Hewan h : daftarHewan) {
            h.bersuara();
        }
    }
}
```

### C. Penjelasan Logika Kode
1. **Dynamic Method Dispatch:** Pada class `PoliTester`, array `daftarHewan` bertipe referensi `Hewan`, namun elemen indeks 1 dan 2 diisi oleh objek konkret dari kelas turunannya (`new Kucing()` dan `new Anjing()`).
2. Saat perulangan `for (Hewan h : daftarHewan)` mengeksekusi perintah `h.bersuara()`, Java Virtual Machine (JVM) secara dinamis saat *runtime* menentukan implementasi method mana yang dipanggil berdasarkan objek instansiasi aslinya, bukan berdasarkan tipe variabel referensinya.
3. Objek `Hewan` mencetak suara umum, objek `Kucing` mencetak `"Meow meow!"`, dan objek `Anjing` mencetak `"Guk guk!"`.

### D. Output Eksekusi Terminal
```text
Hewan ini mengeluarkan suara yang tidak spesifik.
Kucing: Meow meow!
Anjing: Guk guk!
```

---

## 3. Studi Kasus 3: Interface & Multiple Implementation (Smartphone)

### A. Hubungan Antar-Class
* **`AlatElektronik` (Interface):** Mendefinisikan kontrak operasi daya dasar: `nyalakan()` dan `matikan()`.
* **`Kamera` (Interface):** Mendefinisikan kontrak pengambilan gambar: `ambilFoto()`.
* **`Smartphone` (Class Pelaksana):** Mengimplementasikan kedua interface sekaligus dengan sintaks `implements AlatElektronik, Kamera`. Ini mendemonstrasikan kemampuan Java dalam mendukung *multiple inheritance of type*.
* **`TesSmartphone` (Main Class):** Menguji fungsionalitas objek `Smartphone` setelah seluruh method kontrak diimplementasikan.

### B. Source Code

#### `AlatElektronik.java`
```java
package com.praktikum8;

public interface AlatElektronik {
    void nyalakan();
    void matikan();
}
```

#### `Kamera.java`
```java
package com.praktikum8;

public interface Kamera {
    void ambilFoto();
}
```

#### `Smartphone.java`
```java
package com.praktikum8;

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
```

#### `TesSmartphone.java`
```java
package com.praktikum8;

public class TesSmartphone {
    public static void main(String[] args) {
        Smartphone hp = new Smartphone("Samsung");

        hp.nyalakan();
        hp.ambilFoto();
        hp.matikan();
    }
}
```

### C. Penjelasan Logika Kode
1. Di Java, sebuah class tidak dapat memperluas (*extends*) lebih dari satu class induk (*single inheritance*). Untuk mengatasi kebutuhan objek yang memiliki perilaku dari banyak entitas, digunakanlah **Interface**.
2. Seluruh method di dalam interface secara implisit bersifat `public abstract`.
3. Class `Smartphone` wajib menyediakan blok implementasi konkret untuk semua method yang ada pada interface `AlatElektronik` (`nyalakan`, `matikan`) dan interface `Kamera` (`ambilFoto`).
4. Pada `TesSmartphone`, pemanggilan ketiga method tersebut membuktikan bahwa satu objek `Smartphone` mampu berperan ganda sebagai alat elektronik sekaligus sebagai perangkat kamera.

### D. Output Eksekusi Terminal
```text
Samsung menyala. Booting OS...
Cekrek! Foto berhasil disimpan di galeri Samsung.
Samsung dimatikan. Goodbye.
```
