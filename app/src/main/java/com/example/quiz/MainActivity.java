package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama, etJumlah, etKode;
    private Button btnproses;
    private RadioGroup rgMember;
    private RadioButton rbGold, rbSilver, rbNormal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNama = findViewById(R.id.etNama);
        etJumlah = findViewById(R.id.etJumlah);
        etKode = findViewById(R.id.etKode);

        btnproses = findViewById(R.id.btnProses);
        rgMember = findViewById(R.id.rgMember);

        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbNormal = findViewById(R.id.rbNormal);



        btnproses.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        transaksi();
    }

    private void transaksi() {
        String name = etNama.getText().toString().trim();
        String kodebarang = etKode.getText().toString().trim();
        String jumlah = etJumlah.getText().toString().trim();

        if (name.isEmpty() || kodebarang.isEmpty() || jumlah.isEmpty()) {
            Toast.makeText(this, "harap di isi semuanya ", Toast.LENGTH_SHORT).show();
            return;
        }
        int total = Integer.parseInt(jumlah);

        double hargabarang = 0;
        String namabrg = "";
        switch (kodebarang) {
            case "SGS":
                namabrg = "SAMSUNG GALAXY S";
                hargabarang = 12999999;
                break;
            case "IPX":
                namabrg = "IPHONE X";
                hargabarang = 5725300;
                break;
            case "AA5":
                namabrg = "ACER ASPIRE 5";
                hargabarang = 9999999;
                break;
            default:
                Toast.makeText(this, "Kode barang yang anda masukkan sedang tidak ada", Toast.LENGTH_SHORT).show();
                return;
        }

        double totalHarga = hargabarang * total;
        int selectedId = rgMember.getCheckedRadioButtonId();
        double diskonmember = 0;
        String membership = null;
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            membership = radioButton.getText().toString();
            switch (membership) {
                case "gold":
                    diskonmember = totalHarga * 0.1;
                    break;
                case "silver":
                    diskonmember = totalHarga * 0.05;
                    break;
                case "normal":
                    diskonmember = totalHarga * 0.02;
                    break;
            }
        }

        // Diskon tambahan jika total transaksi di atas 10 juta
        if (totalHarga > 10000000) {
            totalHarga -= 100000;
        }
        double discountHarga = totalHarga - (hargabarang * total);
        double jumlahBayar = totalHarga - diskonmember;

        Intent intent = new Intent(this, detaill.class);
        intent.putExtra("Name", name);
        intent.putExtra("Membership", membership);
        intent.putExtra("Kode", kodebarang);
        intent.putExtra("NamaBarang", namabrg);
        intent.putExtra("HargaBarang", hargabarang);
        intent.putExtra("TotalHarga", totalHarga);
        intent.putExtra("DiskonHarga", discountHarga);
        intent.putExtra("DiskonMember", diskonmember);
        intent.putExtra("JumlahBayar", jumlahBayar);
        startActivity(intent);

    }
}
