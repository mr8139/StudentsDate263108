package michal.rapaport.studentsdate263108;

import static michal.rapaport.studentsdate263108.FBref.refStudents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class InputActivity extends AppCompatActivity {
     EditText etName,etGradeClass,etClass,etid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
         etName=findViewById(R.id.etName);
         etGradeClass=findViewById(R.id.etGradeClass);
         etClass=findViewById(R.id.etClass);
         etid=findViewById(R.id.etid);
    }

    public void back(View view) {
        finish();
    }

    public void enter(View view) {
        String name=etName.getText().toString();
        String gradeClass=etGradeClass.getText().toString();
        String Class=etClass.getText().toString();
        String id=etid.getText().toString();
        Students st=new Students(Integer.parseInt(gradeClass),Integer.parseInt(Class),name,id);
        refStudents.child(id).setValue(st);
        etName.setText("");
        etGradeClass.setText("");
        etClass.setText("");
        etid.setText("");

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

