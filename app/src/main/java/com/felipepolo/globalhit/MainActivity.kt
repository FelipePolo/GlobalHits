package com.felipepolo.globalhit

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepolo.globalhit.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity(), RvAdapter.OnCellClickInterface {

    private val matrix = SortColors().getArrayOfColors()
    private var adapter: RvAdapter? = null

    private lateinit var binding: ActivityMainBinding
    private var handler: Handler = Handler(Looper.getMainLooper())

    private var currentColor = ""
    private var hits = 0
    private var errors = 0
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setupGame()
    }

    private fun setupGame() {
        setupScore()
        setUpMatrix()
        setUpSelectedColor()
        setupTimer()
    }

    private fun setupScore() {
        hits = 0
        errors = 0
    }

    private fun setupTimer() {
        time = 10
        handler.post(
            object : Runnable {
                override fun run() {
                    if (time <= 0) {
                        handler.removeCallbacksAndMessages(null)
                        AlertDialog.Builder(this@MainActivity)
                            .setPositiveButton("Volver a empezar", object: DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    setupGame()
                                }
                            })
                            .setMessage(
                                    "Tiempo Agotado" +
                                    "Puntaje ${hits} acertados!")
                            .create()
                            .show()

                    }else{
                        time--
                        binding.tvTime.text = time.toString()
                        handler.postDelayed(this, 1000)
                    }
                }
            }
        )
    }

    private fun setUpSelectedColor() {
        currentColor = DefaultColors.COLORS.random()
        binding.llcolorInicial.setBackgroundColor(Color.parseColor(currentColor))
    }

    private fun setUpMatrix() {
        adapter = RvAdapter(this, matrix, this)
        binding.rvMatrix.layoutManager = LinearLayoutManager(this)
        binding.rvMatrix.adapter = adapter
    }

    override fun onCellClick(color: String) {
        if (color == currentColor) {
            hits++
            binding.tvHits.text = "Aciertos ${hits}"
        } else {
            errors++
            binding.tvErrors.text = "Errores ${errors}"
        }
        setUpSelectedColor()
    }


}