package revesion_recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_topics_2.databinding.ItemStudentBinding;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    ArrayList<Student> students;
    StudentListener listener;

    public StudentAdapter(ArrayList<Student> students , StudentListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull // تعليق: يضمن أن الدالة يجب أن تعيد كائنًا ولا يمكن أن ترجع قيمة فارغة (Null)
    @Override // تعليق: يخبر النظام أننا نعيد كتابة دالة أساسية تابعة لكلاس الـ Adapter

    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // تعليق: بداية الدالة وتستقبل القائمة الأب لتجهيز السطر بناءً عليها

        // تعليق: تحويل تصميم الـ XML الخاص بالسطر إلى واجهة برمجية (Inflate) باستخدام الـ View Binding
        ItemStudentBinding binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        // تعليق: إنشاء وإرجاع نسخة من الـ ViewHolder لحفظ عناصر الواجهة التي تم إنشاؤها في الذاكرة
        return new StudentViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // جلب الطالب بناءً على الموقع الحالي الممرر للدالة
        Student s = students.get(position);

        holder.StudentName.setText(s.getName());
        holder.StudentId.setText(String.valueOf(s.getId()));
        holder.StudentGBA.setText(String.valueOf(s.getGba()));

        // معالجة زر الحذف بطريقة ديناميكية وصحيحة
        holder.deleteIcon.setOnClickListener(view -> {
            // جلب الموقع الفعلي للعنصر لحظة الضغط عليه وسط الشاشة
            int actualPosition = holder.getAdapterPosition();

            // التأكد من أن العنصر لا يزال موجوداً في الـ RecyclerView ولم يُحذف بعد
            if (actualPosition != RecyclerView.NO_POSITION) {
                // 1. حذف العنصر من قائمة البيانات
                students.remove(actualPosition);

                // 2. إعلام الـ Adapter ليقوم بعمل تأثير الحذف البصري واختفاء العنصر
                notifyItemRemoved(actualPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    // كلاس لحفظ عناصر الواجهة في الذاكرة لتجنب إعادة البحث عنها في كل مرة
    public class StudentViewHolder extends RecyclerView.ViewHolder {

        // تعريف متغيرات لعناصر النص والصور الموجودة في تصميم السطر (Item)
        TextView StudentName, StudentId, StudentGBA;
        ImageView deleteIcon;

        // مشيد الكلاس (Constructor) يستقبل ملف الـ Binding الخاص بتصميم السطر
        public StudentViewHolder(ItemStudentBinding binding) {
            // تمرير الواجهة الأساسية للسطر إلى الكلاس الأب (ViewHolder)
            super(binding.getRoot());

            // ربط المتغيرات بالعناصر الحقيقية الموجودة في ملف الـ XML
            StudentName = binding.studentNameTv; // ربط نص اسم الطالب
            StudentId = binding.studentIdTv;     // ربط نص رقم الطالب
            StudentGBA = binding.studentGbaTv;   // ربط نص معدل الطالب

            // ربط أيقونة الحذف لتفعيل الضغط عليها لاحقاً
            deleteIcon = binding.blockIcon;
        }
    }

}

