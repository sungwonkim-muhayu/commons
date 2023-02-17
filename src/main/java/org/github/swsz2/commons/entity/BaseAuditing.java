package org.github.swsz2.commons.entity;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/** Entity에 생성, 수정이 발생하면 자동으로 값을 넣어주는 기본 추상 클래스 */
@Getter
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseAuditing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    final BaseAuditing that = (BaseAuditing) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
