package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refStudents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilterStudentActivity extends AppCompatActivity {

    EditText etGradeFilter;
    ListView lvFiltered;
    ArrayList<Students> filteredStudents;
    ArrayList<String> display;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_student);

        etGradeFilter = findViewById(R.id.etGradeFilter);
        lvFiltered = findViewById(R.id.lvFiltered);

        filteredStudents = new ArrayList<>();
        display = new ArrayList<>();
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, display);
        lvFiltered.setAdapter(adp);
    }

    public void filterStudents(View view) {
        String inputGrade = etGradeFilter.getText().toString();

        if (inputGrade.isEmpty()) {
            Toast.makeText(this, "נא להכניס מספר שכבה", Toast.LENGTH_SHORT).show();
            return;
        }

        int targetGrade = Integer.parseInt(inputGrade);

        // 1. יצירת ה-Query שמסנן ישירות ב-Firebase לפי השדה gradeClass
        Query query = refStudents.orderByChild("gradeClass").equalTo(targetGrade);

        // 2. הפעלת המאזין על ה-query
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                filteredStudents.clear();
                display.clear();

                // כעת ה-ds מכיל אך ורק את התלמידים שמתאימים לשכבה!
                for (DataSnapshot data : ds.getChildren()) {
                    Students st = data.getValue(Students.class);
                    if (st != null) {
                        filteredStudents.add(st);
                        display.add("שם: " + st.getStuName() + " | שכבה: " + st.getGradeClass() + " כיתה: " + st.getStuClass());
                    }
                }

                if (display.isEmpty()) {
                    Toast.makeText(FilterStudentActivity.this, "לא נמצאו תלמידים בשכבה זו", Toast.LENGTH_SHORT).show();
                }

                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FilterStudentActivity.this, "שגיאה בטעינה", Toast.LENGTH_SHORT).show();
            }
        });
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
            startActivity(new Intent(this, InputActivity.class));
            return true;
        } else if (id == R.id.grade) {
            startActivity(new Intent(this, GradeInputActivity.class));
            return true;
        } else if (id == R.id.display) {
            startActivity(new Intent(this, GradeDisplay.class));
            return true;
        } else if (id == R.id.filter_students) {
            return true;
        } else if (id == R.id.exit) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}