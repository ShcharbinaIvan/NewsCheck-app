package com.newscheck.utill

import com.newscheck.model.News
import com.newscheck.model.entity.NewsEntity

fun entityToNews(newsEntity: NewsEntity): News {
    return News(
        newsEntity.id,
        newsEntity.email,
        newsEntity.category,
        newsEntity.description,
        newsEntity.image,
        newsEntity.publishedAt,
        newsEntity.source,
        newsEntity.title,
        newsEntity.url
    )
}

fun newsToEntity(news: News): NewsEntity {
    return NewsEntity(
        news.id,
        news.email,
        news.category,
        news.description,
        news.image,
        news.publishedAt,
        news.source,
        news.title,
        news.url
    )

}