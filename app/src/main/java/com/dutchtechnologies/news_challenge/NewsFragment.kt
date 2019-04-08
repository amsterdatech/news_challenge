package com.dutchtechnologies.news_challenge

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*

class NewsFragment : BaseFragment(), View.OnClickListener {
    lateinit var newsAdapter: NewsAdapter

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun layoutResource(): Int = R.layout.fragment_news

    override fun setupView(view: View) {
        (activity as HomeActivity).setSupportActionBar(view.fragment_articles_toolbar)
        view.fragment_articles_toolbar.setNavigationOnClickListener(this)

        (activity as HomeActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        view.fragment_articles_toolbar.title = "CNN News"

        val linearLayoutManager = LinearLayoutManager(activity)
        view.fragment_articles_recycler_view.layoutManager = linearLayoutManager
        view.fragment_articles_recycler_view.addItemDecoration(
            DividerItemDecoration(
                view.context,
                R.drawable.list_divider
            )
        )

        newsAdapter = NewsAdapter()
        view.fragment_articles_recycler_view.adapter = newsAdapter
        newsAdapter.click = this

        Handler().postDelayed({
            fragment_articles_custom_view_loading.visibility = View.GONE
            fragment_articles_recycler_view.visibility = View.VISIBLE

            newsAdapter.items = getArticles()

        }, 1000)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.view_holder_regular_parent, R.id.view_holder_headline_parent -> {
                //Call Article Detail

            }

            else -> {
                activity?.popBackStack()
            }
        }
    }

    override fun screenName(): String? = ""


    fun getArticles(): List<Article> {
        val itineraries = mutableListOf<Article>()
        for (i in 1..30) {

            val title = listOf(
                "Square will offer its new crypto employees payment in Bitcoin",
                "This Montana County Wants to Crimp Bitcoin to Save the Earth",
                "Ring of Thieves Swipes \$150,000 From Buggy Bitcoin ATMs, and Police Can't Figure Out Who They Are"
            ).shuffled().take(1)[0]

            val source =
                listOf(
                    "www.bild.de",
                    "br.blastingnews.com",
                    "www.bbc.co.uk/sport",
                    "us.cnn.com"
                ).shuffled().take(1)[0]

            val thumbnail = listOf(
                "https://i.kinja-img.com/gawker-media/image/upload/s--JoeFeY8w--/c_fill,fl_progressive,g_center,h_900,q_80,w_1600/tjqry1u2zphw0deh9ko1.jpg",
                "https://o.aolcdn.com/images/dims?thumbnail=1200%2C630&quality=80&image_uri=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fcrop%3D4876%252C3584%252C0%252C0%26quality%3D85%26format%3Djpg%26resize%3D1600%252C1176%26image_uri%3Dhttps%253A%252F%252Fs.yimg.com%252Fos%252Fcreatr-images%252F2019-03%252Fd9c84040-4bc9-11e9-b3ff-31a53efd811d%26client%3Da1acac3e1b3290917d92%26signature%3D21e1a6ad3af11722ad08cb944c023fdd3c61f07f&client=amp-blogside-v2&signature=72000cc11529cea385ed8ecbdec6c9ceb02ca782",
                "https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/11/16/105577722-1542324088307gettyimages-1058971598.1910x1000.jpeg",
                "https://akns-images.eonline.com/eol_images/Entire_Site/201937/rs_600x600-190407174714-600-blake-shelton-gwen-stefani-acm-awards-me-04719.jpg?fit=around|600:467&crop=600:467;center,top&output-quality=90"
            ).shuffled().take(1)[0]

            itineraries.add(
                Article(
                    title,
                    "",
                    thumbnail,
                    "H.P Lovecraft",
                    "Apr 19, 2019"
                )
            )
        }

        return itineraries
    }

}