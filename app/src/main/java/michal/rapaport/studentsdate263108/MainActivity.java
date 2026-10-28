package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refStudents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView lv;
    ArrayAdapter<String> adp;
    //יצירת רשימה של כל הסטודנים שנקרא, לא יודעת מראש כמה
    ArrayList<Students> students;
    //יצירת רשימה להצגת שם ושם משפחה של הסטודנטים בלבד
    ArrayList<String> display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);
        //מצגת 2114 שקופית 6 יצירת העצם
        Students st1=new Students(12,1,"michal","123456");
        Students st2=new Students(12,2,"albert","112233");
        //קביעת מזהה לרשומה- אחסון מידע שקופית 8
        refStudents.child(st1.getuID()).setValue(st1);
        refStudents.child(st2.getuID()).setValue(st2);
        //קריאת מידע
        students=new ArrayList<>();
        display=new ArrayList<>();
        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,display);
         lv.setAdapter(adp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        students.clear();
        display.clear();

        //read data from firebase סיכום במצגת 36 :מצגת 2114 שקופית 22
        refStudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                //שקופית  36 מצגת N2114 - פענוח dataSnapshot
                for (DataSnapshot data : ds.getChildren()) {
                    Students tmp = data.getValue(Students.class);
                    students.add(tmp);
                    display.add(tmp.getStuName());
                }
                //לאחר עדכון display בלולאה עדכן את תצוגת הlistView
                adp.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase", error.getMessage());//יביא את ההערת השגיאה
            }
        });
    }
    public void enter(View view) {
        Intent intent=new Intent(this,InputActivity.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("enter grade")) {
            Intent intent = new Intent(this, GradeInputActivity.class);
            startActivity(intent);
        }
        if (st.equals("display grade")) {
            Intent intent = new Intent(this, GradeDisplay.class);
            startActivity(intent);
        }
        if (st.equals("filter students")) {
            Intent intent = new Intent(this, FilterStudentActivity.class);
            startActivity(intent);
        }
        if (st.equals("order by name")) {
            Intent intent = new Intent(this, OrderByNameStudent.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
        }
}