package com.example.releaselearning.Entity;

import java.io.File;

public class HwDetail {

    private String homeworkId;
    private String studentId;
    private File homeworkDetail;


    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public File getHomeworkFile() {
        return homeworkDetail;
    }

    public void setHomeworkFile(File homeworkDetail) {
        this.homeworkDetail = homeworkDetail;
    }


}
