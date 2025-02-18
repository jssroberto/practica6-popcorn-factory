package garcia.roberto.popcornfactory244913

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    private lateinit var radioButtons: List<RadioButton> // Declare it here
    private lateinit var title: TextView
    private var movieId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("SeatSelection", "onCreate")

        title = findViewById(R.id.txt_title_seats)
        movieId = intent.extras?.getInt("id", -1) ?: -1
        title.text = intent.extras?.getString("title", "")

        initRadioButtons()
        initBtnConfirm()


    }

    private fun initBtnConfirm() {
        val btnConfirm = findViewById<Button>(R.id.btn_confirm)

        btnConfirm.setOnClickListener {
            val selectedSeat = getSelectedSeat()
            if (selectedSeat == null) {
                Toast.makeText(this, "Please select a seat", Toast.LENGTH_SHORT).show()
                Log.d("SeatSelection", "No seat selected")
                return@setOnClickListener
            }
            Log.d("SeatSelection", "Selected seat: $selectedSeat")

            val intent = Intent(this, TicketSummary::class.java)
            intent.putExtra("id", movieId)
            intent.putExtra("title", title.text.toString())
            intent.putExtra("seat", selectedSeat)
            startActivity(intent)
            finish()
        }
    }

    private fun initRadioButtons() {
        radioButtons = listOf(
            findViewById(R.id.seat1),
            findViewById(R.id.seat2),
            findViewById(R.id.seat3),
            findViewById(R.id.seat4),
            findViewById(R.id.seat5),
            findViewById(R.id.seat6),
            findViewById(R.id.seat7),
            findViewById(R.id.seat8),
            findViewById(R.id.seat9),
            findViewById(R.id.seat10),
            findViewById(R.id.seat11),
            findViewById(R.id.seat12),
            findViewById(R.id.seat13),
            findViewById(R.id.seat14),
            findViewById(R.id.seat15),
            findViewById(R.id.seat16),
            findViewById(R.id.seat17),
            findViewById(R.id.seat18),
            findViewById(R.id.seat19),
            findViewById(R.id.seat20)
        )

        val unavailableSeats = radioButtons.shuffled().take(8)
        unavailableSeats.forEach { it.isEnabled = false }

        radioButtons.forEach { radioButton ->
            radioButton.setOnClickListener {
                if (radioButton.isEnabled) {
                    radioButtons.forEach { it.isChecked = false }
                    radioButton.isChecked = true
                }
            }
        }
    }

    private fun getSelectedSeat(): Int? {
        return radioButtons.find { it.isChecked }?.text?.toString()?.toIntOrNull()
    }


}