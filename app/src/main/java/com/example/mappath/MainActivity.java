package com.example.mappath;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.mappath.MapPathAlgorithm.max;

public class MainActivity extends AppCompatActivity {


    private final int[][] ege = new int[][]{
            {max, 2, max, max, 10},
            {max, max, 3, max, 7},
            {4, max, max, 4, max},
            {max, max, max, max, 5},
            {max, max, 3, max, max}
    };


    EditText et_from, et_to;
    TextView tv_result;
    RadioButton rb_dj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_from = findViewById(R.id.et_from);
        et_to = findViewById(R.id.et_to);
        tv_result = findViewById(R.id.tv_result);
        rb_dj = findViewById(R.id.rb_dj);
        findViewById(R.id.btn_star).setOnClickListener(v -> {
            start();
        });
    }


    private void start() {
        MapPathAlgorithm apa = rb_dj.isChecked() ? new DijkstraAlgorithm(ege) : new DFSAlgorithm(ege);
        int from = 0;
        int to = 0;
        try {
            from = Integer.parseInt(et_from.getText().toString());
            to = Integer.parseInt(et_to.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "请输入正确的数字", Toast.LENGTH_SHORT).show();
            return;
        }
        MapPathAlgorithm.PathStack pathStack = apa.getMinPath(from, to);
//        Log.i("zzz", "找到的路径为:" + pathStack);
        tv_result.setText("找到路径：" + pathStack);
    }
}