package hello.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 10
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "member_seq_generator"
    )
    private Long id;

    private String name;

    private Integer age;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
