package michal.rapaport.studentsdate263108;

public class Grades {
       private String uID;
       private String gradeID;//ישמש לטובת האחסון לא מופיע בבנאי
        private String ClassSubject;
        private String gradeType;
        private int finalGrade;

        public Grades(String uID, String classSubject, String gradeType, int finalGrade) {
            this.uID = uID;
            ClassSubject = classSubject;
            this.gradeType = gradeType;
            this.finalGrade = finalGrade;
        }
        public Grades() {
        }

        public String getuID() {
            return uID;
        }

        public void setuID(String uID) {
            this.uID = uID;
        }

        public String getClassSubject() {
            return ClassSubject;
        }

        public void setClassSubject(String classSubject) {
            ClassSubject = classSubject;
        }

        public String getGradeType() {
            return gradeType;
        }

        public void setGradeType(String gradeType) {
            this.gradeType = gradeType;
        }

        public int getFinalGrade() {
            return finalGrade;
        }

        public void setFinalGrade(int finalGrade) {
            this.finalGrade = finalGrade;
        }
        public String getGradeID() {
            return gradeID;
        }
        public void setGradeID(String gradeID) {
            this.gradeID = gradeID;
        }
    }


