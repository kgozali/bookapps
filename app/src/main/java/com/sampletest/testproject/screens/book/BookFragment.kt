package com.sampletest.testproject.screens.book

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.sampletest.testproject.R
import com.sampletest.testproject.adapter.AdapterExt
import com.sampletest.testproject.base.BaseFragment
import com.sampletest.testproject.screens.bookdetail.BookDetailActivity
import com.sampletest.testproject.screens.bookdetail.WriterDetailActivity
import com.sampletest.testproject.viewitems.BookItem
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class BookFragment : BaseFragment(), BookContract.View {

    private val adapterExt = AdapterExt()
    private lateinit var mAdapter: FastItemAdapter<AbstractItem<*, *>>

    override lateinit var presenter: BookContract.Presenter

    override var isFragmentReady = false
        get() = isAdded

    override var isFragmentDestroyed: Boolean = false
        get() = isDestroyed()

    init {
        contentRes = R.layout.fragment_recyclerview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        presenter.start()
    }

    override fun setupView() {
        context?.let {
            mAdapter = adapterExt.initializeAdapter(context = it, rv = rvContent, withDivider = false)
        }
    }

    override fun render() {
        val listOfItems: MutableList<AbstractItem<*, *>> = mutableListOf()

        if (presenter.isFetching) {
            loadingIndicator.visibility = View.VISIBLE
        } else {
            loadingIndicator.visibility = View.GONE

            presenter.responseData?.resource?.map { data ->
                listOfItems.add(
                    BookItem.item {
                        bookData = data
                        onClickListener = View.OnClickListener { onItemClick(data.id ?: 0) }
                    }
                )
            }
        }

        mAdapter.setNewList(listOfItems)
    }

    override fun onItemClick(id: Long) {
        val intent = Intent(context, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.BOOK_ID, id)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = BookFragment()
    }
}