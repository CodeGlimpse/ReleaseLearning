package com.example.releaselearning.Entity;
import com.example.releaselearning.Entity.Class;
public class Homework {


    private String homeworkId;


    private String homeworkContent;

    private com.example.releaselearning.Entity.Class classId;

    public Homework(String homeworkId, String homeworkContent, com.example.releaselearning.Entity.Class classId) {
        this.homeworkId = homeworkId;
        this.homeworkContent = homeworkContent;
        this.classId = classId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "homeworkId='" + homeworkId + '\'' +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", classId=" + classId +
                '}';
    }
}
