package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refStudents;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderByNameStudent extends AppCompatActivity {
     Button bt;
     ListView lv;
     ArrayList<Students> students;
     ArrayList<String> display;
    ArrayAdapter<String> adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_by_name_student);

        lv = findViewById(R.id.lv);

        students = new ArrayList<>();
        display = new ArrayList<>();
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, display);
        lv.setAdapter(adp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Query query = refStudents.orderByChild("stuName");

        // 2. הפעלת המאזין לשליפת הנתונים המסודרים מ-Firebase
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                students.clear();
                display.clear();

                for (DataSnapshot data : ds.getChildren()) {
                    Students st = data.getValue(Students.class);
                    if (st != null) {
                        students.add(st);
                        display.add("שם: " + st.getStuName() + " | שכבה: " + st.getGradeClass() + "'" + st.getStuClass());
                    }
                }

                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderByNameStudent.this, "שגיאה במיון הנתונים", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void back(View view) {
        finish();
    }
}
