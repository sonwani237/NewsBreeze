package com.tesseractapp.demo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tesseractapp.demo.R
import com.tesseractapp.demo.databinding.NewsHomeItemsBinding
import com.tesseractapp.demo.listeners.NewsListener
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.viewmodel.NewsItemViewModel

class NewsAdapter(val mListener: NewsListener) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    var mDataList: ArrayList<HeadlineModel.Articles>? = null

    init {
        mDataList = arrayListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val dataBinding: NewsHomeItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.news_home_items, parent, false)
        return MyViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return mDataList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindList(mDataList!![position], mListener)
    }

    fun setList(dataList: ArrayList<HeadlineModel.Articles>) {
        mDataList!!.addAll(dataList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: NewsHomeItemsBinding) : RecyclerView.ViewHolder(itemBinding.item) {
        private var mItemBinding: NewsHomeItemsBinding? = itemBinding

        fun bindList(mList: HeadlineModel.Articles, mListener: NewsListener) {
            if (mItemBinding!!.itemViewModel == null) {
                mItemBinding!!.itemViewModel = NewsItemViewModel(mList, mListener)
            } else {
                mItemBinding!!.itemViewModel!!.setData(mList)
            }
        }
    }

}