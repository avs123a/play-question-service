package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Response entity that contains user id
 */

@Entity
public class Response {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id",  insertable = false, updatable = false)
    private User user;

    // answers for every field in this response

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    private List<ResponseField> responseFields;

    //getters and setters

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ResponseField> getResponseFields() {
        return responseFields;
    }

    public void setResponseFields(List<ResponseField> responseFields) {
        this.responseFields = responseFields;
    }

}