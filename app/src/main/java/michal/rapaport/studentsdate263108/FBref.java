package michal.rapaport.studentsdate263108;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    //יצירת מופע של בסיס הנתונים
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    //יצירת מצביעים לשורשים לטבלאות שיצרתי בfireBase
    public static DatabaseReference refStudents = FBDB.getReference("Students");
    public static DatabaseReference refGrades = FBDB.getReference("Grades");




}
