package quizapp.volkova.openbuttons

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var openLocationEditText: EditText
    lateinit var openWebSiteEditText: EditText
    lateinit var shareTextEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openLocationEditText = findViewById<EditText>(R.id.location_edittext)
        openWebSiteEditText = findViewById<EditText>(R.id.website_edittext)
        shareTextEditText = findViewById<EditText>(R.id.share_edittext)
        findViewById<Button>(R.id.open_location_button).setOnClickListener(this)
        findViewById<Button>(R.id.open_website_button).setOnClickListener(this)
        findViewById<Button>(R.id.share_text_button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.open_website_button -> openWebsite(v)
            R.id.open_location_button -> openLocation(v)
            R.id.share_text_button -> shareText(v)
        }
    }

    fun openWebsite(view: View?) {
        val url: String = openWebSiteEditText.getText().toString()
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun openLocation(view: View?) {
        val loc: String = openLocationEditText.getText().toString()

        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun shareText(view: View?) {
        val txt: String = shareTextEditText.getText().toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.app_name)
            .setText(txt)
            .startChooser()
    }
}