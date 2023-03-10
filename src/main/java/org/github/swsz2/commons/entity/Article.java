package org.github.swsz2.commons.entity;

import lombok.Setter;
import org.github.swsz2.commons.entity.listener.ArticleEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(ArticleEntityListener.class)
public class Article extends BaseAuditing {

    @Setter
    @Column
    private String title;
}
