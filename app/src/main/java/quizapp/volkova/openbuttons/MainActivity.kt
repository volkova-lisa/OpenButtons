package quizapp.volkova.openbuttons

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import quizapp.volkova.openbuttons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    private lateinit var openLocationEditText: EditText
//    private lateinit var openWebSiteEditText: EditText
//    private lateinit var shareTextEditText: EditText

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        openLocationEditText = findViewById<EditText>(R.id.location_edittext)
//        openWebSiteEditText = findViewById<EditText>(R.id.website_edittext)
//        shareTextEditText = findViewById<EditText>(R.id.share_edittext)
        binding.openLocationButton.setOnClickListener(this)
        binding.openWebsiteButton.setOnClickListener(this)
        binding.shareTextButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.openWebsiteButton.id -> openWebsite(v)
            binding.openLocationButton.id -> openLocation(v)
            binding.shareTextButton.id -> shareText(v)
        }
    }

    fun openWebsite(view: View?) {
        val url: String = binding.websiteEdittext.text.toString()
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun openLocation(view: View?) {
        val loc: String = binding.locationEdittext.text.toString()

        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun shareText(view: View?) {
        val txt: String = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.app_name)
            .setText(txt)
            .startChooser()
    }
}