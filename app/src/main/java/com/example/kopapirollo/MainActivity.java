package com.example.kopapirollo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView machineChoice;
    private ImageView userChoice;
    private ImageView userChoiceRock;
    private ImageView userChoicePaper;
    private ImageView userChoiceScissors;
    private TextView userWonText;
    private TextView machineWonText;
    private TextView draftText;
    private int userWon;
    private int machineWon;
    private int draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.machineChoice = findViewById(R.id.choice_machine);
        this.userChoice = findViewById(R.id.choice_user);
        this.userChoiceRock = findViewById(R.id.choice_rock);
        this.userChoicePaper = findViewById(R.id.choice_paper);
        this.userChoiceScissors = findViewById(R.id.choice_scissors);
        this.userWonText = findViewById(R.id.user_won);
        this.machineWonText = findViewById(R.id.machine_won);
        this.draftText = findViewById(R.id.draft);
        this.userWon = 0;
        this.machineWon = 0;
        this.draft = 0;
        this.userChoiceRock.setOnClickListener(view -> game(0));
        this.userChoicePaper.setOnClickListener(view -> game(1));
        this.userChoiceScissors.setOnClickListener(view -> game(2));
    }

    private void game(int userChoice)
    {
        Random rand = new Random();
        int machineChoice = rand.nextInt(3);
        switch (userChoice)
        {
            case 0:
                this.userChoice.setImageResource(R.drawable.rock);
                break;
            case 1:
                this.userChoice.setImageResource(R.drawable.paper);
                break;
            case 2:
                this.userChoice.setImageResource(R.drawable.scissors);
                break;
            default:
                break;
        }
        switch (machineChoice)
        {
            case 0:
                this.machineChoice.setImageResource(R.drawable.rock);
                break;
            case 1:
                this.machineChoice.setImageResource(R.drawable.paper);
                break;
            case 2:
                this.machineChoice.setImageResource(R.drawable.scissors);
                break;
            default:
                break;
        }

        if (machineChoice == userChoice)
        {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
            this.draft += 1;
        }
        else if ((userChoice == 1 && machineChoice == 0) || (userChoice == 2 && machineChoice == 1) || (userChoice == 0 && machineChoice == 2))
        {
            Toast.makeText(this, "Nyert: Ember", Toast.LENGTH_SHORT).show();
            this.userWon += 1;
        }
        else
        {
            Toast.makeText(this, "Nyert: Gép", Toast.LENGTH_SHORT).show();
            this.machineWon += 1;
        }
        this.userWonText.setText(getString(R.string.user) + userWon);
        this.machineWonText.setText(getString(R.string.machine) + machineWon);
        this.draftText.setText(getString(R.string.draft) + draft);
        if (userWon == 3 || machineWon == 3)
        {
            createDialog((userWon == 3) ? "Gyözelem" : "Vereség");
        }
    }

    private void createDialog(String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage("Szeretnél új játékot játszani?");
        builder.setPositiveButton("Igen", (dialogInterface, i) -> Reset());
        builder.setNegativeButton("Nem", (dialogInterface, i) -> finish());
        builder.show();
    }

    private void Reset()
    {
        this.machineChoice.setImageResource(R.drawable.rock);
        this.userChoice.setImageResource(R.drawable.rock);
        this.userWonText.setText(R.string.user_0);
        this.machineWonText.setText(R.string.machine_0);
        this.draftText.setText(R.string.draft_0);
        this.userWon = 0;
        this.machineWon = 0;
        this.draft = 0;
    }
}