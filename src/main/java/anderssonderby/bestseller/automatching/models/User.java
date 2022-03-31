package anderssonderby.bestseller.automatching.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Nickname must not be empty")
    private String nickname;

    private int credits;

    @OneToMany(mappedBy = "user")
    private List<UserGame> games;

    @NotBlank(message = "Gender must not be empty")
    @Size(max = 6)
    private String gender;

    @NotBlank(message = "Region must not be empty")
    private String region;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}