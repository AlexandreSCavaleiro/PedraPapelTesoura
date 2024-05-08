package alexandre.cavaleiro.pedrapapeltesoura

import alexandre.cavaleiro.pedrapapeltesoura.databinding.ActivityJogarBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class JogarActivity : AppCompatActivity() {
    private val ajb: ActivityJogarBinding by lazy {
        ActivityJogarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ajb.root)

        val winnerTv: TextView = ajb.winnerTv
        val pedraBt: Button = ajb.pedraBt
        val papelBt: Button = ajb.papelBt
        val tesouraBt: Button = ajb.tesouraBt

        val qntdBots = intent.getIntExtra("JOGAR_COM_X_BOT", 1)

        if (qntdBots == 1) {
            pedraBt.setOnClickListener { jogarComUm("Pedra", winnerTv) }
            papelBt.setOnClickListener { jogarComUm("Papel", winnerTv) }
            tesouraBt.setOnClickListener { jogarComUm("Tesoura", winnerTv) }
        }
        if (qntdBots == 2){
            pedraBt.setOnClickListener { jogarComDois("Pedra", winnerTv) }
            papelBt.setOnClickListener { jogarComDois("Papel", winnerTv) }
            tesouraBt.setOnClickListener { jogarComDois("Tesoura", winnerTv) }
        }
    }

    private fun jogarComUm(escolhaJogador: String, saidaResultado: TextView) {
        val opcoes = arrayOf("Pedra", "Papel", "Tesoura")

        val escolhaBot = opcoes[Random.nextInt(opcoes.size)]

        val result = when {
            escolhaJogador == escolhaBot -> "Empate!"
            (escolhaJogador == "Pedra" && escolhaBot == "Tesoura") ||
                    (escolhaJogador == "Papel" && escolhaBot == "Pedra") ||
                    (escolhaJogador == "Tesoura" && escolhaBot == "Papel") ->
                "Você ganhou!"
            else -> "Você perdeu!"
        }

        val resultado = "Jogador $escolhaJogador | Bot $escolhaBot \n" +
                " |$result|"
        saidaResultado.text = resultado
    }

    private fun jogarComDois(escolhaJogador: String, saidaResultado: TextView) {
        val opcoes = arrayOf("Pedra", "Papel", "Tesoura")

        val escolhaBotUm = opcoes[Random.nextInt(opcoes.size)]
        val escolhaBotDois = opcoes[Random.nextInt(opcoes.size)]


        var pontoBotum = quemGanha(escolhaBotUm,escolhaBotDois) + quemGanha(escolhaBotUm,escolhaJogador)
        var pontoBotdois = quemGanha(escolhaBotDois, escolhaBotUm) + quemGanha(escolhaBotDois,escolhaJogador)
        var pontoJogador = quemGanha(escolhaJogador,escolhaBotUm) + quemGanha(escolhaJogador,escolhaBotDois)

        var result = if (pontoJogador == pontoBotum && pontoJogador == pontoBotdois) "Empate entre os 3"
        else if (pontoJogador > pontoBotum && pontoJogador > pontoBotdois) "Você Venceu"
        else if (pontoBotum > pontoJogador && pontoBotum > pontoBotdois) "Bot 1 Venceu"
        else if (pontoBotdois > pontoJogador && pontoBotdois > pontoBotum) "Bot 2 Venceu"
        else if (pontoBotum == pontoBotdois) "Bot 1 e 2 empataram"
        else if (pontoBotum == pontoJogador) "Você empatou com o Bot 1"
        else "Voce empatou com bot 2"

        val resultado = "Jogador $escolhaJogador | Bot 1 $escolhaBotUm | Bot 2 $escolhaBotDois \n |$result|"
        saidaResultado.text = resultado
    }

    private fun quemGanha(escolhaUm: String, escolhaDois: String): Int {
        if (escolhaUm == escolhaDois) return 0
        if (escolhaUm == "Tesoura"){
            if (escolhaDois == "Papel") return 1
            if (escolhaDois == "Pedra") return -1
        }

        if (escolhaUm == "Papel"){
            if (escolhaDois == "Pedra") return 1
            if (escolhaDois == "Tesoura") return -1
        }

        if (escolhaUm == "Pedra"){
            if (escolhaDois == "Tesoura") return 1
            if (escolhaDois == "Papel") return -1
        }
        return -9
    }
}