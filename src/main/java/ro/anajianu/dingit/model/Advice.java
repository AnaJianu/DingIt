package ro.anajianu.dingit.model;

import javax.persistence.*;

/**
 * Created by ana on 23/05/2017.
 */
@Entity
public class Advice {

    @Id
    @GeneratedValue
    private Long id;

    private String adviceText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;


    public Advice() {
    }

    public Advice(String adviceText, User user, Question question) {
        this.adviceText = adviceText;
        this.user = user;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id='" + id + '\'' +
                ", adviceText='" + adviceText + '\'' +
                ", user=" + user +
                ", question=" + question +
                '}';
    }
}
