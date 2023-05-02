package com.example.appdev23s_a04

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class WebBrowserActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var input: TextInputEditText
    private lateinit var webView: WebView
    private lateinit var backButton: Button
    private lateinit var forwardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        button = findViewById(R.id.button)
        input = findViewById(R.id.input)
        webView = findViewById(R.id.webview)
        backButton = findViewById(R.id.backbtn)
        forwardButton = findViewById(R.id.forwardbtn)

        button.setOnClickListener {
            webView.loadUrl(input.text.toString())
        }

        backButton.setOnClickListener {
            webView.goBack()
        }

        forwardButton.setOnClickListener {
            webView.goForward()
        }
    }
}