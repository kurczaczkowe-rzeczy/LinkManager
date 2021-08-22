package pl.gungnir.linkmanager.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.ui.MainActivity
import pl.gungnir.linkmanager.ui.MainViewModel
import pl.gungnir.linkmanager.ui.history.adapter.LinkAdapter
import pl.gungnir.linkmanager.ui.history.state.MainStateEvent
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var mViewModel: MainViewModel

    @Inject
    lateinit var mAdapter: LinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history_links, container, false)

        mAdapter.setOnClickItem { triggerOpenLinkEvent(it) }
        view.findViewById<RecyclerView>(R.id.list).adapter = mAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        subscribeObservers()
        triggerGetLinksEvent()
    }

    private fun subscribeObservers() {
        mViewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            dataState.listLinks?.let {
                mViewModel.setLinks(it)
            }

            dataState.selectedLink?.let {
                mViewModel.setSelectedLink(it)
            }
        }
        mViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.listLinks?.let {
                mAdapter.updateList(it)
            }

            viewState.selectedLink?.let {
                (activity as? MainActivity)?.showBrowserFragment()
            }
        }
    }

    private fun triggerGetLinksEvent() {
        mViewModel.setStateEvent(MainStateEvent.GetLinks)
    }

    private fun triggerOpenLinkEvent(position: Int) {
        mViewModel.setStateEvent(MainStateEvent.SelectLink(position))
    }
}