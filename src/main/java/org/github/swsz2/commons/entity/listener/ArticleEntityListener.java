package org.github.swsz2.commons.entity.listener;

import org.github.swsz2.commons.entity.Article;
import org.springframework.util.Assert;

import javax.persistence.PrePersist;

public class ArticleEntityListener {


    @PrePersist
    public void prePersist(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        if (target instanceof Article) {
            ((Article) target).setTitle("default title!");
        }
    }
}
