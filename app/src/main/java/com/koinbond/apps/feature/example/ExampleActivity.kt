package com.koinbond.apps.feature.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koinbond.apps.R
import com.koinbond.apps.base.BaseActivity
import com.koinbond.apps.utils.RecyclerInterface

class ExampleActivity : BaseActivity(), ExampleView, RecyclerInterface {

    val examplePresenter = ExamplePresenter(this)

    override fun contentView(): Int {
        return R.layout.activity_example
    }

    override fun setupData(savedInstanceState: Bundle?) {
        //setup variable below(from another activity or on start of activity or call presenter on start activity)
        examplePresenter.getExample()
    }

    override fun setupView() {
        //Only View Allowed Here
    }

    override fun onSuccessExample(data: List<String>) {
        //When Success hit end point, this function is called
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onFailed(message: String) {

    }

    override fun onRecyclerItemClicked(position: Int) {

    }
}
