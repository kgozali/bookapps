package com.larittabakery.mylaritta.screens.catalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import com.sampletest.testproject.R
import com.sampletest.testproject.adapter.AdapterExt
import com.sampletest.testproject.base.BaseFragment
import com.sampletest.testproject.screens.book.BookActivity
import com.sampletest.testproject.screens.main.MainContract
import com.sampletest.testproject.viewitems.GenreItem
import kotlinx.android.synthetic.main.fragment_recyclerview.*


class MainFragment: BaseFragment(), MainContract.View {
    private val mAdapter by lazy {
        AdapterExt().initializeAdapter(
            context = requireContext(),
            rv = mRecyclerView,
            withDivider = true
        )
    }
    private lateinit var mRecyclerView: RecyclerView

    override var isFragmentReady: Boolean = isAdded

    override var isFragmentDestroyed: Boolean = false
        get() = isDestroyed()

    override lateinit var presenter: MainContract.Presenter

    init {
        contentRes = R.layout.fragment_recyclerview
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        mRecyclerView = root.findViewById(R.id.rvContent)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun render() {
        val listOfItems: MutableList<AbstractItem<*, *>> = mutableListOf()
        if (presenter.isFetching) {
            loadingIndicator.visibility = View.VISIBLE
        } else {
            loadingIndicator.visibility = View.GONE
            presenter.responseData?.let { response ->
                response.resource?.map { data ->
                    listOfItems.add(
                        GenreItem.item {
                            genreItem = data
                            onClickListener = View.OnClickListener { onGenreClick(data.id ?: 0) }
                        }
                    )
                }
            }
        }

        mAdapter.setNewList(listOfItems)
    }

    override fun onGenreClick(id: Long) {
        val intent = Intent(context, BookActivity::class.java)
        intent.putExtra(BookActivity.GENRE_ID, id)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}