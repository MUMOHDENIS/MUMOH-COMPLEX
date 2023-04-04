package com.example.midmorningintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    //Instantiate the user interface elements
    lateinit var btnSMS:Button
    lateinit var btnEmail:Button
    lateinit var btnCAMERA:Button
    lateinit var btnSHARE:Button
    lateinit var btnMPESA:Button
    lateinit var btnCALL:Button
    lateinit var btnWebsite:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSMS = findViewById(R.id.mBtnSms)
        btnEmail =findViewById(R.id.mBtnEmail)
        btnCAMERA = findViewById(R.id.mBtnCamera)
        btnSHARE = findViewById(R.id.mBtnShare)
        btnMPESA = findViewById(R.id.mBtnMpesa)
        btnCALL =findViewById(R.id.mBtnCall)
        btnWebsite =findViewById(R.id.mBtnWebsite)

        btnSMS.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0706918506")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Rada ni gani mzing")
            startActivity(intent)
        }
        btnEmail.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "dmumo198@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Game time")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello,the game kicks off at 4:30.....")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }
        btnCAMERA.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        btnSHARE.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)


        }
        btnMPESA.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        btnCALL.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0706918506"))
            if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf<String>(android.Manifest.permission.CALL_PHONE), 1)
            } else {
                startActivity(intent)
            }
        }
        btnWebsite.setOnClickListener {
            val tembea = Intent(this@MainActivity, WebstiteActivity::class.java)
            startActivity(tembea)

        }

    }
}