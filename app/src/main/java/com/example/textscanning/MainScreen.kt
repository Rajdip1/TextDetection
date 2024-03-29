package com.example.textscanning

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainScreen : AppCompatActivity() {

    lateinit var result: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        supportActionBar?.hide()

        val edit = findViewById<ImageView>(R.id.btnEdit)
        val camera = findViewById<ImageView>(R.id.btnCamera)
        val copy = findViewById<ImageView>(R.id.btnCopy)

        //initialized 'result' variable
        result = findViewById(R.id.resultTextView)

        camera.setOnClickListener {
            //open up the camera and store image
            //on clicked image we will run the ML algo to detect text out it
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent,123)
            }
            else{
                Toast.makeText(this,"Oops something wrong",Toast.LENGTH_SHORT).show()
            }
        }

        edit.setOnClickListener {
            result.setText("")
        }

        copy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip =ClipData.newPlainText("label",result.text.toString())
            clipboard.setPrimaryClip(clip)

            Toast.makeText(this,"Copied to clipboard",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==123 && resultCode== RESULT_OK && data != null){
            val extras = data.extras
            val bitmap = extras?.get("data") as Bitmap

            detectTextFromImageUsingMl(bitmap)
        }
    }

    private fun detectTextFromImageUsingMl(bitmap: Bitmap) {
        //latin script
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap,0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Toast.makeText(this,"Something went wrong with ML kit",Toast.LENGTH_SHORT).show()
            }
    }

}