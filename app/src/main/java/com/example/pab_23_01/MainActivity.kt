package com.example.pab_23_01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val rgJK = findViewById<RadioGroup>(R.id.rgJK)
        val spKota = findViewById<Spinner>(R.id.spKota)
        val swMenyetujui = findViewById<SwitchCompat>(R.id.swMenyetujui)
        val btnSimpan = findViewById<Button>(R.id.btn_submit)

        val kota = arrayOf("Pilih Kota", "Jakarta", "Bandung", "Surabaya", "Padang")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            kota,
        )
        spKota.adapter = adapter

        btnSimpan.setOnClickListener {
            if (edtNama.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "Nama Tidak Boleh Kosong!!!",
                    Toast.LENGTH_SHORT,
                ).show()
                return@setOnClickListener
            }

            if (!swMenyetujui.isChecked) {
                Toast.makeText(
                    this,
                    "Anda Harus Menyetujui S&K",
                    Toast.LENGTH_SHORT,
                ).show()
                return@setOnClickListener
            }

            val jk = when (rgJK.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-Laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Belum Dipilih"
            }

            val data = "Nama : ${edtNama.text}" +
                    "\nJenis Kelamin : $jk" +
                    "\nKota : ${spKota.selectedItem}"

            // Pindah ke halaman Preview terlebih dahulu
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("DATA_HASIL", data)
            startActivity(intent)
        }

        // Setup Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_form

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_form -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
