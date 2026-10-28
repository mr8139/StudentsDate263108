package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refGrades;
import static michal.rapaport.studentsdate263108.FBref.refStudents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class GradeInputActivity extends AppCompatActivity {
    EditText etId, etSub, etGradeType, etGrade;
    Button bt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);
        etId = findViewById(R.id.etId);
        etSub = findViewById(R.id.etSub);
        etGradeType = findViewById(R.id.etGradeType);
        etGrade = findViewById(R.id.etGrade);
        bt = findViewById(R.id.bt);

    }
    public void back(View view) {
        finish();
    }

    public void sumbit(View view) {
        String id = etId.getText().toString();
        String sub = etSub.getText().toString();
        String gradeType = etGradeType.getText().toString();
        String grade = etGrade.getText().toString();

        // יצירת אובייקט הציון
        Grades gr = new Grades(id, sub, gradeType, Integer.parseInt(grade));
        String key = refGrades.child(id).push().getKey();//create key for grade
        gr.setGradeID(key);//עדכון המידע
        refGrades.child(id).child(key).setValue(gr);//add grade to firebase
//                    // איפוס השדות
                    etId.setText("");
                    etSub.setText("");
                   etGradeType.setText("");
                    etGrade.setText("");


        // refGrades.child(id).setValue(gr);
//        // במידה ויש כמה ציונים לתלמיד,כדי שלא ידרס הציון הקודם כאשר נוסיף עוד ציון, נשתמש ב push
//        // בדיקה האם התלמיד קיים ב-refStudents לפני שמירה
//        refStudents.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // התלמיד קיים! נשמור את הציון ב-refGrades
//
//                    refGrades.push().setValue(gr);
//                    Toast.makeText(GradeInputActivity.this, "הציון נשמר בהצלחה!", Toast.LENGTH_SHORT).show();
//
//                    // איפוס השדות
//                    etId.setText("");
//                    etSub.setText("");
//                    etGradeType.setText("");
//                    etGrade.setText("");
//                } else {
//                    // התלמיד לא קיים במערכת
//                    Toast.makeText(GradeInputActivity.this, "שגיאה: תלמיד זה אינו קיים במערכת!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(GradeInputActivity.this, "שגיאה בקריאת הנתונים", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.student) {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.grade) {
            Intent intent = new Intent(this, GradeInputActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.display) {
            Intent intent = new Intent(this, GradeDisplay.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.exit) {
            finishAffinity(); // סוגר את האפליקציה ויוצא
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}