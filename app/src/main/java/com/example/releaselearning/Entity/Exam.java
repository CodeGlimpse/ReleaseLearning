package com.example.releaselearning.Entity;


public class Exam {


    private String ExamId;

    private String examContent;


    private com.example.releaselearning.Entity.Class classId;


    private int status;

    public Exam(String examId, String examContent, Class classId, int status) {
        ExamId = examId;
        this.examContent = examContent;
        this.classId = classId;
        this.status = status;
    }

    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String examId) {
        ExamId = examId;
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "ExamId='" + ExamId + '\'' +
                ", examContent='" + examContent + '\'' +
                ", classId=" + classId +
                ", status=" + status +
                '}';
    }
}
