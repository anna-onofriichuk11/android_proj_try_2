package com.example.tryengapp.views.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import org.koin.android.ext.android.inject

abstract class BaseFragment: Fragment() {

    val baseActivity: MainActivity
        get() = activity as MainActivity

    private val viewModel: BaseViewModel by inject()

    private var view: View? = null

    protected abstract val menuResultId: Int?
    protected abstract val toolbar: Int?
    protected abstract val contentResultId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (view == null) {
            view = inflater.inflate(contentResultId, container, false)
        }
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        inflateMenu(view)
        setObservers()
        setListeners()
        setActions()
    }


    protected open fun setViews(view: View) {}

    protected open fun setObservers() {}

    protected open fun setListeners() {}

    protected open fun setActions() {}

    protected open fun onBackPressed() {}

    fun showBottomBar() = baseActivity.showBottomBar()

    fun hideBottomBar() = baseActivity.hideBottomBar()

    protected open fun inflateMenu(view: View) {
        if (viewModel.isFirstInflating) {
            menuResultId?.let { menu ->
                toolbar?.let { toolbar ->
                    view.findViewById<MaterialToolbar>(toolbar).inflateMenu(menu)
                }
            }.also {
                viewModel.isFirstInflating = false
            }
        }
    }
}