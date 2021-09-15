 package com.tuanteo.qrscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

 public class MainActivity extends AppCompatActivity {
     private WebView mWebView;
//     private String mWebLink;
     private int FILECHOOSER_RESULTCODE = 102;
     private ValueCallback<Uri[]> mUploadMessage;

     @SuppressLint("SetJavaScriptEnabled")
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.layout_web_view);

//         setFullScreenView();

//         mWebLink = getIntent().getStringExtra(NavigationDrawActivity.WEB_LINK);

         mWebView = findViewById(R.id.web_view);
         WebSettings settings = mWebView.getSettings();
         settings.setJavaScriptEnabled(true);
         settings.setAllowContentAccess(true);
         settings.setDomStorageEnabled(true);
         mWebView.setWebViewClient(new WebViewClient());
         mWebView.setWebChromeClient(new WebChromeClient(){
             @Override
             public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                 mUploadMessage = filePathCallback;
                 Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                 i.addCategory(Intent.CATEGORY_OPENABLE);
                 i.setType("image/*");
                 MainActivity.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);
                 return true;
             }

             //The undocumented magic method override
             //Eclipse will swear at you if you try to put @Override here
             // For Android 3.0+
             public void openFileChooser(ValueCallback<Uri[]> uploadMsg) {

                 mUploadMessage = uploadMsg;
                 Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                 i.addCategory(Intent.CATEGORY_OPENABLE);
                 i.setType("image/*");
                 MainActivity.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);

             }

             // For Android 3.0+
             public void openFileChooser( ValueCallback uploadMsg, String acceptType ) {
                 mUploadMessage = uploadMsg;
                 Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                 i.addCategory(Intent.CATEGORY_OPENABLE);
                 i.setType("*/*");
                 MainActivity.this.startActivityForResult(
                         Intent.createChooser(i, "File Browser"),
                         FILECHOOSER_RESULTCODE);
             }

             //For Android 4.1
             public void openFileChooser(ValueCallback<Uri[]> uploadMsg, String acceptType, String capture){
                 mUploadMessage = uploadMsg;
                 Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                 i.addCategory(Intent.CATEGORY_OPENABLE);
                 i.setType("image/*");

                 MainActivity.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), FILECHOOSER_RESULTCODE );

             }

         });
         mWebView.loadUrl("http://tdvnongtq.42web.io/");
//         mWebView.loadUrl("http://testduocmechant.42web.io/");
//         mWebView.loadUrl(mWebLink);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
         if(requestCode == FILECHOOSER_RESULTCODE) {
             if (mUploadMessage == null)
                 return;
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 mUploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
             }
             mUploadMessage = null;
         }
         super.onActivityResult(requestCode, resultCode, data);
     }

     @Override
     public void onBackPressed() {
         if(mWebView.getVisibility()==View.VISIBLE){
             // dont pass back button action
             if(mWebView.canGoBack()){
                 mWebView.goBack();
             } else {
                 super.onBackPressed();
             }
         }else{
             // pass back button action
             super.onBackPressed();
         }
     }

     /**
      * Set Full Screen when show WebView
      */
     private void setFullScreenView() {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
             final WindowInsetsController controller = getWindow().getInsetsController();

             if (controller != null)
                 controller.hide(WindowInsets.Type.statusBars());
         }
         else {
             //noinspection deprecation
             getWindow().getDecorView().setSystemUiVisibility(
                     View.SYSTEM_UI_FLAG_FULLSCREEN
                             | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                             | View.SYSTEM_UI_FLAG_IMMERSIVE
                             | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                             | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                             | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
         }
     }
 }