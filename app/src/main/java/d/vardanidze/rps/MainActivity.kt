package d.vardanidze.rps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rock: Button
    private lateinit var paper: Button
    private lateinit var scissor: Button
    private lateinit var computerScore: TextView
    private lateinit var playerScore: TextView
    private lateinit var welcomeLabel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        rock = findViewById(R.id.rockButton)
        paper = findViewById(R.id.paperButton)
        scissor = findViewById(R.id.scissorButton)
        computerScore = findViewById(R.id.computerScore)
        playerScore = findViewById(R.id.playerScore)
        welcomeLabel = findViewById(R.id.welcomeLabel)

        rock.setOnClickListener(this)
        paper.setOnClickListener(this)
        scissor.setOnClickListener(this)
    }

    // 1->rock, 2->scissor, 3-> paper
    private fun winner(player: Int, computer: Int):Int{
        if(player == computer)
            return 0
        if(player == 1 && computer == 2)
            return 1
        if(player == 2 && computer == 3)
            return 1
        if(player == 3 && computer == 1)
            return 1
        return -1
    }

    private fun setUrlForUser(image: Int){
        when (image) {
            1 -> {
                findViewById<ImageView>(R.id.playerImage).setImageResource(R.drawable.rock)
            }
            2 -> {
                findViewById<ImageView>(R.id.playerImage).setImageResource(R.drawable.scissor)
            }
            3 -> {
                findViewById<ImageView>(R.id.playerImage).setImageResource(R.drawable.paper)
            }
        }
        findViewById<ImageView>(R.id.playerImage).isVisible = true
    }

    private fun setUrlForComputer(image: Int){
        when (image) {
            1 -> {
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.rock)
            }
            2 -> {
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.scissor)
            }
            3 -> {
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.paper)
            }
        }
        findViewById<ImageView>(R.id.computerImage).isVisible = true
    }

    override fun onClick(clickedView: View?) {
        if (clickedView is Button) {
            welcomeLabel.isVisible = false
            var playerNum = 0
            when(clickedView.id){
                R.id.rockButton->playerNum=1
                R.id.scissorButton->playerNum=2
                R.id.paperButton->playerNum=3
            }
            val computerNum = Random.nextInt(1,4)
            setUrlForComputer(computerNum)
            setUrlForUser(playerNum)
            val score = winner(playerNum, computerNum)

            var pScore = playerScore.text.toString().toInt()
            var cScore = computerScore.text.toString().toInt()
            if(score == 1){
                pScore++
                playerScore.text = pScore.toString()
                playerScore.setTextColor(resources.getColor(R.color.green))
                computerScore.setTextColor(resources.getColor(R.color.black))
            }else if (score == -1){
                cScore++
                computerScore.text = cScore.toString()
                computerScore.setTextColor(resources.getColor(R.color.green))
                playerScore.setTextColor(resources.getColor(R.color.black))
            }else{
                playerScore.setTextColor(resources.getColor(R.color.yellow))
                computerScore.setTextColor(resources.getColor(R.color.yellow))
            }
        }

    }
}