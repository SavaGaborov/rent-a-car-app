package rentacar.mvp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by savagaborov on 8.1.20..
 */
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_timestamp")
    private ZonedDateTime createTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

    @Column(name = "deleted")
    private Boolean deleted;

    @PrePersist
    public void onPrePersist() {
        this.deleted = false;
    }
}
