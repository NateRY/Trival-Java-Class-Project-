package org.trivia.model;


import jakarta.persistence.*;

@Entity
@Table(name = "entries")
public class Entries{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "hint")
    private String hint;

    @Column(name = "category")
    private String category;

    @Column(name = "level")
    private String level;

    // Default constructor is required
    public Entries() {}

    public Entries(String question, String answer, String option2, String option3, String option4, String hint, String category, String level) {
        this.question = question;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.hint = hint;
        this.category = category;
        this.level = level;
    }
}