package com.sbo.sentences;

public class UserSentences {
    private String Lecture_Sentences;
    private String ID_Sentences;

    public UserSentences(String lecture_Sentences, String ID_Sentences) {
        Lecture_Sentences = lecture_Sentences;
        this.ID_Sentences = ID_Sentences;
    }
    public  UserSentences(){

    }

    public String getLecture_Sentences() {
        return Lecture_Sentences;
    }

    public void setLecture_Sentences(String lecture_Sentences) {
        Lecture_Sentences = lecture_Sentences;
    }

    public String getID_Sentences() {
        return ID_Sentences;
    }

    public void setID_Sentences(String ID_Sentences) {
        this.ID_Sentences = ID_Sentences;
    }
}
