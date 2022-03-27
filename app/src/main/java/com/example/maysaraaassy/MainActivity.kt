package com.example.maysaraaassy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Firebase.firestore
        val id = System.currentTimeMillis()

        back.setOnClickListener {
            val i = Intent(this, Librarybook::class.java)
            startActivity(i)
        }


        button.setOnClickListener {
            addBook(
                id.toString(),
                bookName.text.toString(),
                bookAouthor.text.toString(),
                bookprice.text.toString(),
                bookluch.text.toString()
            )
            bookName.text.clear()
            bookAouthor.text.clear()
            bookprice.text.clear()
            bookluch.text.clear()
        }
    }

    private fun addBook(id: String, name: String, author: String, price: String, Lunch: String) {
        val books = hashMapOf(
            "id" to id,
            "BookAuthor" to author,
            "BookName" to name,
            "BookLunch" to Lunch,
            "Price" to price
        )
        db!!.collection("MyLibrary").add(books)

    }
}