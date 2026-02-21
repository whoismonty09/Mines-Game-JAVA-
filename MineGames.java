import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MineGames extends JFrame {

    JButton[][] buttons = new JButton[5][5];
    boolean[][] bombs = new boolean[5][5];
    int score = 0;
    JLabel scoreLabel;

    public MineGames() {
        setTitle("Mine Games developed by Monty");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        add(scoreLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));

        Random rand = new Random();
        int bombCount = 0;

        while (bombCount < 2) {
            int r = rand.nextInt(5);
            int c = rand.nextInt(5);
            if (!bombs[r][c]) {
                bombs[r][c] = true;
                bombCount++;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
                int r = i;
                int c = j;

                btn.addActionListener(e -> handleClick(r, c));

                buttons[i][j] = btn;
                panel.add(btn);
            }
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    void handleClick(int r, int c) {
        if (bombs[r][c]) {
            buttons[r][c].setText("💣");
            revealBombs();
            JOptionPane.showMessageDialog(this, "Game Over!\nFinal Score: " + score);
            System.exit(0);
        } else {
            buttons[r][c].setText("❤️");
            buttons[r][c].setEnabled(false);
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    void revealBombs() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bombs[i][j]) {
                    buttons[i][j].setText("💣");
                }
            }
        }
    }

    public static void main(String[] args) {
        new MineGames();
    }
}
