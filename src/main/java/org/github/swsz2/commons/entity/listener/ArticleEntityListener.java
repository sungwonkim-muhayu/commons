package org.github.swsz2.commons.entity.listener;

import lombok.SneakyThrows;
import org.github.swsz2.commons.entity.Article;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.persistence.PrePersist;
import java.lang.reflect.Field;

public class ArticleEntityListener {


    @SneakyThrows
    @PrePersist
    public void prePersist(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        if (target instanceof Article) {
            // 객체 매핑해서 하거나 (setter 있을 때)
            ((Article) target).setTitle("default title!");

            // 리플렉션으로 처리하거나 (setter 없을 때)
            final Field field = ReflectionUtils.findField(target.getClass(), "title");
            Assert.notNull(field, "Entity field not be null!");

            field.setAccessible(true);
            field.set(target, "default title!");
        }
    }
}
