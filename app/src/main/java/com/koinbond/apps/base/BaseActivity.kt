package com.koinbond.apps.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity(){

    var isActive = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isActive = true
        setContentView(contentView())
        setupData(savedInstanceState)
        setupView()
    }

    override fun onDestroy() {
        isActive = false
        super.onDestroy()
    }

    protected abstract fun contentView(): Int
    protected abstract fun setupData(savedInstanceState: Bundle?)
    protected abstract fun setupView()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(isHasBackButton: Boolean) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(isHasBackButton)
            supportActionBar!!.setDisplayHomeAsUpEnabled(isHasBackButton)
            toolbar!!.setNavigationOnClickListener { v -> onBackPressed() }
        }
    }

    protected fun setupToolbar(title: String, isHasBackButton: Boolean) {
        setupToolbar(isHasBackButton)
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    protected fun setupToolbar(title: Int, isHasBackButton: Boolean) {
        setupToolbar(isHasBackButton)
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(title)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("RESREQ", "REQ :$requestCode, RES :$resultCode")
    }
}