package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class detaill extends AppCompatActivity {
    private Button btnshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill);

        btnshare = findViewById(R.id.btnshare);


        String name = getIntent().getStringExtra("Name");
        String membership = getIntent().getStringExtra("Membership");
        String kodebarang = getIntent().getStringExtra("Kode");
        String barang = getIntent().getStringExtra("NamaBarang");
        double harga = getIntent().getDoubleExtra("HargaBarang",0);
        double total = getIntent().getDoubleExtra("TotalHarga",0);
        double dishar = getIntent().getDoubleExtra("DiskonHarga",0);
        double dismem = getIntent().getDoubleExtra("DiskonMember",0);
        double jumlah = getIntent().getDoubleExtra("JumlahBayar",0);

        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
        String totall = rupiah.format(total);
        String dismember = rupiah.format(dismem);
        String dispembeli = rupiah.format(dishar);
        String jumlahh = rupiah.format(jumlah);
        String hargaa = rupiah.format(harga);



        TextView tvDispem = findViewById(R.id.tvDispem);
        TextView tvTotal = findViewById(R.id.tvTotal);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvNamabarang = findViewById(R.id.tvNamabarang);
        TextView tvKode = findViewById(R.id.tvKode);
        TextView tvdismem = findViewById(R.id.tvdismem);
        TextView tvtampil = findViewById(R.id.tvtampil);
        TextView tvtipe = findViewById(R.id.tvtipe);
        TextView tvBayar = findViewById(R.id.tvBayar);

        tvtampil.setText("selamat bergabung " + name);
        tvtipe.setText("tipe member " + membership);
        tvKode.setText("kode barang: " + kodebarang);
        tvNamabarang.setText("Nama barang: " + barang);
        tvHarga.setText("Harga barang: " + hargaa);
        tvTotal.setText("Total harga: " + totall);
        tvDispem.setText("Diskon pembelian: " + dispembeli);
        tvdismem.setText("Diskon member: " + dismember);
        tvBayar.setText("jumlah bayar: " + jumlahh);

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kiriman = "nama barang: " + barang + "\n" + "jumlah bayar: " + jumlahh;

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, kiriman);
                startActivity(Intent.createChooser(share, "pilih aplikasi untuk berbagi"));
            }
        });


    }
}