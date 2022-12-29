package com.example.releaselearning;

public class Constant {

    public static final String URL = "http://192.168.43.71:8080";
    public static final String URLLogin = URL + "/android/login";
    public static final String URLAddUser = URL + "/android/register";
    public static final String URLSubmitHomework = URL +"/stu/fileUpload";
    public static final String URLHomeWork = URL + "/android/homework";
    public static final String URLExam = URL +"/android/exam";
    public static final String URLHomeworkFindByStudentIDAndHomeworkId = URL +"/android/homework/getHomeWorkByStudentIdAndHomeworkId";
    public static final String URLExamFindByStudentIDAndExamId = URL +"/android/exam/getExamWorkByStudentIdAndExamId";
    public static String StuId = "";
    public static final int SUCCEED_CODE = 1;
    public static final int FAILED_CODE = 2;
    public static final int BEGIN_EXAM = 1;
    public static final int END_EXAM = 2;
    public static final String URLEXAM = URL;
}
