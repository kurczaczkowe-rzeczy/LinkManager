package pl.gungnir.linkmanager.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.ui.MainViewModel
import pl.gungnir.linkmanager.ui.history.state.MainStateEvent

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_links, container, false)
    }

    override fun onResume() {
        super.onResume()

        triggerGetLinksEvent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            dataState.listLinks?.let {
                mViewModel.setLinks(it)
            }
        }
        mViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.listLinks?.let {
                Log.d("MRMRMR", "HistoryFragment.kt subscribeObservers: $it")
            }
        }
    }

    fun triggerGetLinksEvent() {
        mViewModel.setStateEvent(MainStateEvent.GetLinks)
    }
}