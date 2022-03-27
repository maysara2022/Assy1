package com.example.maysaraaassy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_edit_books.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.back

class EditBooks : AppCompatActivity() {
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_books)


        back.setOnClickListener {
            val k = Intent(this, Librarybook::class.java)
            startActivity(k)
        }
        edit.setOnClickListener {
            BookUpdate()
            Toast.makeText(applicationContext, "Book Edited Successfuly", Toast.LENGTH_LONG).show()

            val x = Intent(this, Librarybook::class.java)
            startActivity(x)


        }

        button11.setOnClickListener {
            deletBook()

            xbookName.text.clear()
            xbookAouthor.text.clear()
            xbookprice.text.clear()
            xbookluch.text.clear()

            Toast.makeText(applicationContext, "Book Deleted Successfuly", Toast.LENGTH_LONG).show()
            val x = Intent(this, Librarybook::class.java)
            startActivity(x)

        }


        db = Firebase.firestore


        xbookName.setText(intent.getStringExtra("BookName").toString())
        xbookAouthor.setText(intent.getStringExtra("BookAuthor").toString())
        xbookluch.setText(intent.getStringExtra("BookLunch").toString())
        xbookprice.setText(intent.getStringExtra("Price").toString())


    }

    private fun deletBook() {
        db!!.collection("MyLibrary").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                document.toObject<books>()
                if (document.get("id") ==
                    intent.getStringExtra("id")
                ) {
                    db!!.collection("MyLibrary").document(
                        document.id
                    ).delete()
                }
            }

        }

    }

    private fun BookUpdate() {
        db!!.collection("MyLibrary").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                document.toObject<books>()
                if (document.get("id") == intent.getStringExtra("id")) {
                    db!!.collection("MyLibrary").document(document.id)
                        .update("BookName", xbookName.text.toString())
                    db!!.collection("MyLibrary").document(document.id)
                        .update("BookAuthor", xbookAouthor.text.toString())
                    db!!.collection("MyLibrary").document(document.id)
                        .update("Price", xbookprice.text.toString())
                    db!!.collection("MyLibrary").document(document.id)
                        .update("BookLunch", xbookluch.text.toString())


                }
            }
        }
    }


}
