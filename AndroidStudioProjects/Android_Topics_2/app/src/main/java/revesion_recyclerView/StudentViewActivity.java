package revesion_recyclerView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_topics_2.databinding.ActivityStudentViewBinding;

import java.util.ArrayList;

public class StudentViewActivity extends AppCompatActivity implements StudentListener {

    ActivityStudentViewBinding binding;
    ArrayList<Student> students;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        students = new ArrayList<>();
        students.add(new Student("ahmed" , 1 , 70.4));
        students.add(new Student("mohammed" , 2 , 75.4));
        students.add(new Student("yazzen" , 3 , 80.4));

        adapter = new StudentAdapter(students,this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));





    }

    @Override
    public void onDelete(int pos) { //  بداية دالة الحذف وتستقبل موقع (Index) العنصر المراد حذفه

        // تعليق: حذف كائن الطالب نهائياً من مصفوفة البيانات (ArrayList) بناءً على موقعه
        students.remove(pos);

        // تعليق: إعلام الـ Adapter بحذف العنصر ليقوم بعمل تأثير بصري (Animation) لاختفاء السطر
        adapter.notifyItemRemoved(pos);

        // تعليق: تحديث مواقع العناصر التي تأتي بعد العنصر المحذوف لضمان صحة الـ Position وتجنب الأخطاء
        adapter.notifyItemRangeChanged(pos, students.size());
    }
}