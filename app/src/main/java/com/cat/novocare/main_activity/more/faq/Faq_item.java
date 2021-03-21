package com.cat.novocare.main_activity.more.faq;

import java.io.Serializable;

public class Faq_item implements Serializable {
    private final String question, answer;
    private boolean expanded;

    public Faq_item(String question, String answer, boolean expanded) {
        this.question = question;
        this.answer = answer;
        this.expanded = expanded;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
