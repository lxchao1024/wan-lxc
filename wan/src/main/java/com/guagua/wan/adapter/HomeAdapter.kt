package com.guagua.wan.adapter

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.guagua.wan.R
import com.guagua.wan.model.bean.Article
import com.guagua.wan.utils.ImageLoader

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/17 14:01
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomeAdapter(private val context: Context?, datas: MutableList<Article>): BaseQuickAdapter<Article, BaseViewHolder>(
    R.layout.item_home_list, datas) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {
        item ?: return
        helper ?: return
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(R.id.iv_like, if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not)
            .addOnClickListener(R.id.iv_like)
        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
            context?.let {
                ImageLoader.load(it, item.envelopePic, helper.getView(R.id.iv_article_thumbnail))
            }
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.GONE
        }
        val tvFresh = helper.getView<TextView>(R.id.tv_article_fresh)
        if (item.fresh) {
            tvFresh.visibility = View.VISIBLE
        } else {
            tvFresh.visibility = View.GONE
        }
        val tvTop = helper.getView<TextView>(R.id.tv_article_top)
        if (item.top == "1") {
            tvTop.visibility = View.VISIBLE
        } else {
            tvTop.visibility = View.GONE
        }
        val tvArticleTag = helper.getView<TextView>(R.id.tv_article_tag)
        if (item.tags.size > 0) {
            tvArticleTag.visibility = View.VISIBLE
            tvArticleTag.text = item.tags[0].name
        } else {
            tvArticleTag.visibility = View.GONE
        }
    }
}