package com.isa.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.profil_activity.*

class ProfilActivity : AppCompatActivity() {
    private var Nama: EditText? = null
    private var Email: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_activity)
        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById<EditText>(R.id.email)
        Telp = findViewById<EditText>(R.id.telp)
        Alamat = findViewById<EditText>(R.id.alamat)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            prosesSave()
        }
    }

    private fun prosesSave() {
        val getNama: String = Nama?.getText().toString()
        val getEmail: String = Email?.getText().toString()
        val getTelp: String = Telp?.getText().toString()
        val getAlamat: String = Alamat?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty() ) {
            Toast.makeText(this@ProfilActivity,"Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
        } else {
            val profil = ProfilModel(getNama, getEmail, getTelp, getAlamat, getUserID)
            ref.child(getUserID).child("Profil").push().setValue(profil).addOnCompleteListener {
            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show() }
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
