package alexandre.cavaleiro.pedrapapeltesoura

import alexandre.cavaleiro.pedrapapeltesoura.databinding.ActivityJogarBinding
import alexandre.cavaleiro.pedrapapeltesoura.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        val bemvinTv: TextView = amb.bemvindoTv
        val umBotBt: Button = amb.umBotBt
        val doisBotBt: Button = amb.doisBotBt

        umBotBt.setOnClickListener {
            Intent(this, JogarActivity::class.java).apply {
                putExtra("JOGAR_COM_X_BOT", 1)
                startActivity(this)
            }
        }

        doisBotBt.setOnClickListener {
            Intent(this, JogarActivity::class.java).apply {
                putExtra("JOGAR_COM_X_BOT", 2)
                startActivity(this)
            }
        }
    }

}