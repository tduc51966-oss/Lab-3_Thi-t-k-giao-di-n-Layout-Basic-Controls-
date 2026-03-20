package com.example.layoutbasiccontrols

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ánh xạ các view từ layout
        val edtA = findViewById<EditText>(R.id.edtA)
        val edtB = findViewById<EditText>(R.id.edtB)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Thiết lập sự kiện onClick cho các nút
        btnAdd.setOnClickListener { calculate('+', edtA, edtB, tvResult) }
        btnSub.setOnClickListener { calculate('-', edtA, edtB, tvResult) }
        btnMul.setOnClickListener { calculate('*', edtA, edtB, tvResult) }
        btnDiv.setOnClickListener { calculate('/', edtA, edtB, tvResult) }
    }

    private fun calculate(operator: Char, edtA: EditText, edtB: EditText, tvResult: TextView) {
        val sA = edtA.text.toString()
        val sB = edtB.text.toString()

        // Kiểm tra dữ liệu đầu vào
        if (sA.isEmpty() || sB.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ số A và B", Toast.LENGTH_SHORT).show()
            return
        }

        val a = sA.toDouble()
        val b = sB.toDouble()
        var res = 0.0

        when (operator) {
            '+' -> res = a + b
            '-' -> res = a - b
            '*' -> res = a * b
            '/' -> {
                if (b == 0.0) {
                    tvResult.text = "Kết quả: Lỗi chia cho 0"
                    return
                }
                res = a / b
            }
        }

        tvResult.text = "Kết quả: $res"
    }
}