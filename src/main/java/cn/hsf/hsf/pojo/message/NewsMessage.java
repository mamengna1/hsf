package cn.hsf.hsf.pojo.message;

import cn.hsf.hsf.pojo.message.inner.Article;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 回复图文消息
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {

    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();

    public NewsMessage(Map<String, String> requestMap, List<Article> articles) {
        super(requestMap);
        setMsgType("news");
        this.articles = articles;
        this.articleCount = articles.size() + "";
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
