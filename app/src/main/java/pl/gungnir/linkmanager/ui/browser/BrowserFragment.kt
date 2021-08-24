package pl.gungnir.linkmanager.ui.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.ui.MainViewModel

@AndroidEntryPoint
class BrowserFragment : Fragment() {

    lateinit var mViewModel: MainViewModel
    private lateinit var webView: WebView
    private lateinit var label: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        webView = view.findViewById(R.id.webview)
        label = view.findViewById(R.id.name)

        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_browser, container, false)
    }

    private fun subscribeObservers() {
        mViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.peekContent().selectedLink?.let {
                label.text = it.label
                webView.loadUrl(it.url)
            }
        }
    }
}