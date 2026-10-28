package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refGrades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradeDisplay extends AppCompatActivity {
    ArrayList<Grades> gradesList;
    ArrayList<String> display;
    ArrayAdapter<String> adp;
    ListView lv;
    Button btBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_display);
        lv = findViewById(R.id.lv);
        gradesList = new ArrayList<>();
        display = new ArrayList<>();
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, display);
        lv.setAdapter(adp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gradesList.clear();
        display.clear();
        refGrades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                for (DataSnapshot student : ds.getChildren()) {
                    for (DataSnapshot grade : student.getChildren()) {
                        Grades tmp = grade.getValue(Grades.class);
                        gradesList.add(tmp);
                        display.add(tmp.getFinalGrade() + " " + tmp.getClassSubject());
                    }
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase", error.getMessage());
            }
        });


        //reading data for single student
//        gradesList.clear();
//        display.clear();
//        refGrades.child("112233").addListenerForSingleValueEvent(new ValueEventListener() {
//             @Override
//            public void onDataChange(@NonNull DataSnapshot ds) {
//
//                    for (DataSnapshot grade : ds.getChildren()) {
//                        Grades tmp = grade.getValue(Grades.class);
//                        gradesList.add(tmp);
//                        display.add(tmp.getFinalGrade() + " " + tmp.getClassSubject());
//                    }
//
//                adp.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("firebase", error.getMessage());
//            }
//        });

    }

    public void display(View view) {
        Intent intent = new Intent(this, GradeInputActivity.class);
        startActivity(intent);
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

    public void back(View view) {
        finish();
    }
}