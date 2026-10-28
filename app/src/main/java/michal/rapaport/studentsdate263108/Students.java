package michal.rapaport.studentsdate263108;

public class Students {
    private int gradeClass;
    private int StuClass;
    private String StuName;
    private String uID;

    public Students(int gradeClass, int stuClass, String stuName, String uID) {
        this.gradeClass = gradeClass;
        StuClass = stuClass;
        StuName = stuName;
        this.uID = uID;
    }

    public Students() {
    }

    public int getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(int gradeClass) {
        this.gradeClass = gradeClass;
    }

    public int getStuClass() {
        return StuClass;
    }

    public void setStuClass(int stuClass) {
        StuClass = stuClass;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}

