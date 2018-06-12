package me.vension.mvvm.demo.ui.activitys

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_agent_webview.*
import me.vension.mvvm.demo.R



/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 16:23.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class AgentWebActivity : AppCompatActivity() {
    lateinit var mAgentWeb : AgentWeb
    var url : String = ""
    var title : String = ""
    var siteName : String = ""

    companion object {
        private val KEY_URL = "KEY_URL"
        private val KEY_TITLE = "KEY_TITLE"
        private val KEY_SITE_NAME = "KEY_SITE_NAME"

        fun makeIntent(context: Context, url: String, title: String, siteName: String): Intent {
            val intent = Intent(context, AgentWebActivity::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_TITLE, title)
            intent.putExtra(KEY_SITE_NAME, siteName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_webview)

        title = intent.getStringExtra(KEY_TITLE)
        url = intent.getStringExtra(KEY_URL)
        siteName = intent.getStringExtra(KEY_SITE_NAME)
        setupToolbar()//设置标题栏
        setupAgentWebView()//设置webview
    }

    /**设置标题栏*/
    private fun setupToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            this.finish()
        }
    }


    private fun setupAgentWebView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container,LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .setWebChromeClient(ChromeClient())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url)
    }

    private inner class ChromeClient : WebChromeClient() {

        // NOTE: WebView can be trying to show an AlertDialog after the activity is finished, which
        // will result in a WindowManager$BadTokenException.
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return this@AgentWebActivity.isFinishing || super.onJsAlert(view, url, message,
                    result)
        }

        override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return this@AgentWebActivity.isFinishing || super.onJsConfirm(view, url, message,
                    result)
        }

        override fun onJsPrompt(view: WebView, url: String, message: String, defaultValue: String,
                                result: JsPromptResult): Boolean {
            return this@AgentWebActivity.isFinishing || super.onJsPrompt(view, url, message,
                    defaultValue, result)
        }

        override fun onJsBeforeUnload(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return this@AgentWebActivity.isFinishing || super.onJsBeforeUnload(view, url, message,
                    result)
        }

    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()

    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }


    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
           return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.navigation_item_share -> {
                shareToFriend(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun shareToFriend(activity: Activity) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, url)
        sendIntent.type = "text/plain"
        activity.startActivity(Intent.createChooser(sendIntent, title))
    }

}