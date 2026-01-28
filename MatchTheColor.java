package esd;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;



public class MatchTheColor extends JFrame implements ActionListener {

    private final JLabel titleLabel;
    private final JLabel wordLabel;
    private final JLabel infoLabel;
    private final JButton startButton;
    private final JButton[] optionButtons;

    private final String[] colorNames = {
            "RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "MAGENTA", "CYAN", "BLACK"
    };

    private final Color[] colors = {
            Color.RED, new Color(0,153,0), Color.BLUE, Color.YELLOW,
            Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.BLACK
    };

    private final Map<String,Integer> nameToIndex;
    private final Random random;

    private int correctColorIndex = -1;
    private int score = 0;

    private int timeLeft = 5;          // 5 sec per color
    private int totalTimeLeft = 60;    // 1 min overall game timer

    private Timer countdownTimer;       // round timer
    private Timer gameTimer;            // global 1-min timer

    private boolean inGame = false;

    public MatchTheColor() {
        super("Match The Colour");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        random = new Random();
        nameToIndex = new HashMap<>();
        for (int i = 0; i < colorNames.length; i++) {
            nameToIndex.put(colorNames[i], i);
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Click the COLOUR of the word (not the text)", SwingConstants.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,6,10));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        infoLabel = new JLabel("Score: 0    Time: 5    Total: 60", SwingConstants.CENTER);
        infoLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        topPanel.add(infoLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        wordLabel = new JLabel("READY?");
        wordLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        centerPanel.add(wordLabel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(8,8));
        JPanel optionsGrid = new JPanel(new GridLayout(2,2,10,10));

        optionButtons = new JButton[4];
        for (int i=0; i<4; i++) {
            JButton btn = new JButton("-");
            btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            btn.addActionListener(this);
            btn.setEnabled(false);
            optionButtons[i] = btn;
            optionsGrid.add(btn);
        }

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8,12,12,12));
        bottomPanel.add(optionsGrid, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        startButton.addActionListener(e -> onStart());
        bottomPanel.add(startButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // 5-second ROUND timer
        countdownTimer = new Timer(1000, e -> {
            if (!inGame) return;

            timeLeft--;

            if (timeLeft <= 0) {
                score = Math.max(0, score - 1);
                nextRound();
                return;
            }
            updateInfo();
        });

        // NEW: 60-second GAME timer
        gameTimer = new Timer(1000, e -> {
            if (!inGame) return;

            totalTimeLeft--;

            if (totalTimeLeft <= 0) {
                endGame();
                return;
            }
            updateInfo();
        });
    }

    private void onStart() {
        inGame = true;
        score = 0;
        timeLeft = 5;
        totalTimeLeft = 60;

        startButton.setText("Restart");
        setOptionsEnabled(true);
        updateInfo();
        nextRound();

        countdownTimer.start();
        gameTimer.start();    // start global timer
    }

    private void endGame() {
        inGame = false;
        setOptionsEnabled(false);

        countdownTimer.stop();
        gameTimer.stop();

        wordLabel.setText("TIME UP!");
        wordLabel.setForeground(Color.DARK_GRAY);

        JOptionPane.showMessageDialog(
                this,
                "Final Score: " + score,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void setOptionsEnabled(boolean enabled) {
        for (JButton b : optionButtons) b.setEnabled(enabled);
    }

    private void updateInfo() {
        infoLabel.setText(
                "Score: " + score +
                "    Time: " + timeLeft +
                "    Total: " + totalTimeLeft
        );
    }

    private void nextRound() {
        int textIndex = random.nextInt(colorNames.length);
        correctColorIndex = random.nextInt(colorNames.length);

        wordLabel.setText(colorNames[textIndex]);
        wordLabel.setForeground(colors[correctColorIndex]);

        List<Integer> indices = new ArrayList<>();
        indices.add(correctColorIndex);

        while (indices.size() < 4) {
            int idx = random.nextInt(colorNames.length);
            if (!indices.contains(idx)) indices.add(idx);
        }

        Collections.shuffle(indices);

        for (int i=0; i<4; i++) {
            optionButtons[i].setText(colorNames[indices.get(i)]);
            optionButtons[i].setForeground(Color.DARK_GRAY);
        }

        timeLeft = 5;   // reset round timer
        updateInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inGame) return;

        JButton clicked = (JButton) e.getSource();
        Integer idx = nameToIndex.get(clicked.getText());

        boolean correct = idx == correctColorIndex;

        if (correct) score++;
        else score = Math.max(0, score - 1);

        updateInfo();

        Color prev = clicked.getForeground();
        clicked.setForeground(correct ? Color.GREEN.darker() : Color.RED);

        Timer t = new Timer(220, ev -> {
            clicked.setForeground(prev);
            nextRound();
        });

        t.setRepeats(false);
        t.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) {}
            new MatchTheColor().setVisible(true);
        });
    }
}
