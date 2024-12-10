package com.idcamp.mysubmissionapps

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataGame = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Game>("key_game", Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Game>("key_game")
        }

        val tvGameTitle: TextView = findViewById(R.id.game_title)
        val tvGameDescription: TextView = findViewById(R.id.description)
        val ivGamePicture: ImageView = findViewById(R.id.game_picture)
        val tvGameReleaseDate: TextView = findViewById(R.id.release_date)
        val tvGameDeveloper: TextView = findViewById(R.id.developer)

        tvGameTitle.text = dataGame!!.name
        tvGameDescription.text = dataGame.description
        ivGamePicture.setImageResource(dataGame.photo)
        tvGameReleaseDate.text = dataGame.releaseDate
        tvGameDeveloper.text = dataGame.developer

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val tvGameTitle: TextView = findViewById(R.id.game_title)
        val tvGameDescription: TextView = findViewById(R.id.description)

        val gameTitle = tvGameTitle.text.toString()
        val gameDescription = tvGameDescription.text.toString()
        val shareText = "$gameTitle\n\n$gameDescription"

        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Hack and Slash Game")
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                startActivity(Intent.createChooser(shareIntent, "Share with"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}