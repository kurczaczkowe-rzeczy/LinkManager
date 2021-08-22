package pl.gungnir.linkmanager.ui.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.domain.model.Link

class LinkAdapter : RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Link>() {

        override fun areItemsTheSame(oldItem: Link, newItem: Link): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Link, newItem: Link): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    private var onItemClick: ((Int) -> Unit)? = null

    fun updateList(list: List<Link>) {
        differ.submitList(list)
    }

    fun setOnClickItem(onItemClick: (Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val linkName: TextView = itemView.findViewById(R.id.linkName)

        fun bind(name: String, onClickListener: () -> Unit) {
            linkName.text = name
            linkName.setOnClickListener { onClickListener() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_link_history, parent, false)
        return LinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val data = this.differ.currentList[position]
        val name = if (data.label.isNotEmpty()) {
            data.label
        } else {
            data.url
        }

        holder.bind(name) {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount() = this.differ.currentList.size
}