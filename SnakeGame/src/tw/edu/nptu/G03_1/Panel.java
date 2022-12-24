package tw.edu.nptu.G03_1;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Panel extends JPanel {

    boolean gamemode = true;
    boolean VisiableSnake = false;
    // boolean keyinput = true;
    boolean Stop = false;
    boolean player2 = false;
    int SCREEN_WIDTH = 700, SCREEN_HEIGHT = 700, UNIT_SIZE = 35,
        GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    // save history score
    int historyscore[] = { 0, 0, 0, 0, 0 };
    int speed = 150;
    int second, bodyParts, score, appleX, appleY, bodyParts2, score2, winner;
    final int x[] = new int[GAME_UNITS], y[] = new int[GAME_UNITS], Rank[] = { 0, 0, 0, 0, 0 };
    final int x2[] = new int[GAME_UNITS], y2[] = new int[GAME_UNITS];

    FrameScore ScorePanel = new FrameScore();

    JSlider slider = new JSlider(50, 250, speed);
    boolean sliderVisible = false;
    JLabel SliderLabel = new JLabel();
    JLabel SliderLabel2 = new JLabel();

    Random random;
    char direction;
    char direction2;
    Timer timer, clock;
    TimerTask task, task2;

    int[][] mapArray = new int[20][20];
    int[][] mapcollections = new int[20][20];
    int Ur, Ug, Ub, Pr, Pg, Pb;

    Panel() {
        Initialize();
        ScorePanel.setVisible(false);
        slider.addKeyListener(new MyKeyAdapter());
        this.addKeyListener(new MyKeyAdapter());
        this.setBackground(new Color(117, 111, 111));
        this.setFocusable(true);

        StartPanel();
        addslider();
    }

    // Key control
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (VisiableSnake == true) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R' && gamemode == true) {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L' && gamemode == true) {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D' && gamemode == true) {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U' && gamemode == true) {
                            direction = 'D';
                        }
                        break;

                    // player 2
                    case KeyEvent.VK_A:
                        if (direction2 != 'R' && gamemode == true) {
                            direction2 = 'L';
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (direction2 != 'L' && gamemode == true) {
                            direction2 = 'R';
                        }
                        break;
                    case KeyEvent.VK_W:
                        if (direction2 != 'D' && gamemode == true) {
                            direction2 = 'U';
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (direction2 != 'U' && gamemode == true) {
                            direction2 = 'D';
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        gamemode = !gamemode;
                        Stop = !Stop;
                        task.cancel();
                        sliderVisible = !sliderVisible;
                        slider.setVisible(sliderVisible);
                        SliderLabel.setVisible(sliderVisible);
                        GameTimer();
                        break;
                }
            }
        }
    }

    // Initialize
    public void Initialize() {
        speed = 150;
        bodyParts = 1;
        score = 0;
        bodyParts2 = 1;
        score2 = 0;
        appleX = 0;
        appleY = 0;
        direction = 'R';
        direction2 = 'D';
        player2 = false;
        winner = 0;

        for (int i = 0 ; i < GAME_UNITS ; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        for (int i = 0 ; i < GAME_UNITS ; i++) {
            x2[i] = 0;
            y2[i] = 0;
        }
        for (int i = 0 ; i < WIDTH / UNIT_SIZE ; i++) {
            for (int j = 0 ; j < WIDTH / UNIT_SIZE ; j++) {
                mapArray[i][j] = 0;
            }
        }
    }

    // Game running
    public void GameTimer() {
        if (VisiableSnake == true) {
            RemovePanel();
            timer = new Timer();
            clock = new Timer();
            random = new Random();
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            this.setBackground(new Color(Pr, Pg, Pb));
            this.setFocusable(true);
            // newApple();
            second = 0;
            task = new TimerTask() {
                public void run() {
                    if (gamemode == true) {
                        move();
                        checkApple();
                        checkCollisions();
                        repaint();
                    } else if (gamemode == false && Stop == true) {
                        repaint();
                    } else {
                        timer.cancel();
                    }
                }
            };
            // Add clock
            // ==========================================================================
            task2 = new TimerTask() {
                public void run() {
                    if (gamemode == true) {
                        second++;
                        // System.out.println(second);
                    } else {
                        clock.cancel();
                    }
                }
            };
            // ====================================================================================

            timer.scheduleAtFixedRate(task, 0, 300 - slider.getValue());
            clock.scheduleAtFixedRate(task2, 0, 1000);

        } else {
            RemovePanel();
        }
    }

    // Draw snake、apple
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VisiableSnake == true) {
            if (gamemode == true && Stop == false) {
                // Draw line
                for (int i = 0 ; i < SCREEN_HEIGHT / UNIT_SIZE ; i++) {
                    g.setColor(new Color(30, 30, 30));
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                    for (int j = 0 ; j < SCREEN_HEIGHT / UNIT_SIZE ; j++) {
                        if (mapArray[i][j] == 1) {
                            g.setColor(new Color(Ur, Ug, Ub));
                            g.fillRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                        }
                    }
                }
                for (int i = 0 ; i < SCREEN_HEIGHT / UNIT_SIZE ; i++) {
                    g.setColor(new Color(30, 30, 30));
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }

                // Draw apple
                g.setColor(Color.red);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                // Draw snake
                for (int i = 0 ; i < bodyParts ; i++) {
                    if (i == 0) {
                        g.setColor(Color.green);
                        g.fillRect(x[i] + 1, y[i] + 1, UNIT_SIZE - 1, UNIT_SIZE - 1);
                    } else {
                        g.setColor(new Color(45, 180, 0));
                        g.fillRect(x[i] + 1, y[i] + 1, UNIT_SIZE - 1, UNIT_SIZE - 1);
                    }
                }
                if (player2 == true) {
                    for (int i = 0 ; i < bodyParts2 ; i++) {
                        if (i == 0) {
                            g.setColor(Color.blue);
                            g.fillRect(x2[i] + 1, y2[i] + 1, UNIT_SIZE - 1, UNIT_SIZE - 1);
                        } else {
                            g.setColor(new Color(45, 45, 180));
                            g.fillRect(x2[i] + 1, y2[i] + 1, UNIT_SIZE - 1, UNIT_SIZE - 1);
                        }
                    }
                }

                // Draw score information
                if (player2 == false) {
                    g.setColor(Color.red);
                    g.setFont(new Font(null, Font.BOLD, 40));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g.drawString("Score: " + score, (SCREEN_WIDTH - metrics.stringWidth("Score: " + score)) / 2,
                            g.getFont().getSize());
                } else {
                    g.setColor(Color.red);
                    g.setFont(new Font(null, Font.BOLD, 34));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g.drawString("player1: " + score, (SCREEN_WIDTH - metrics.stringWidth("player1: " + score)) / 3,
                            g.getFont().getSize());
                    g.setColor(Color.red);
                    g.setFont(new Font(null, Font.BOLD, 34));
                    FontMetrics metrics2 = getFontMetrics(g.getFont());
                    g.drawString("player2: " + score2,
                            (SCREEN_WIDTH - metrics2.stringWidth("player2: " + score2)) / 3 * 2,
                            g.getFont().getSize());
                }
            } else if (gamemode == false && Stop == true) {
                g.setColor(Color.red);
                g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                FontMetrics metrics2 = getFontMetrics(g.getFont());
                g.drawString("Paused", (SCREEN_WIDTH - metrics2.stringWidth("Paused")) / 2, SCREEN_HEIGHT / 2);

                SliderLabel2.setText("speed:");
                this.add(SliderLabel2);
                this.add(slider);
                SliderLabel.setText("" + slider.getValue());
                this.add(SliderLabel);

            } else {
                Pr = 0; Pg = 0; Pb = 0;
                for (int i = 0 ; i < 20 ; i++) {
                    for (int j = 0 ; j < 20 ; j++)
                        mapArray[i][j] = 0;
                }
                gameOver(g);
            }
        }
    }

    // Check snake eat apple
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            score++;
            newApple();
        } else if ((x2[0] == appleX) && (y2[0] == appleY)) {
            bodyParts2++;
            score2++;
            newApple();
        }

    }

    // Update apple position
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        System.out.println(appleX % UNIT_SIZE + appleY % UNIT_SIZE);
        System.out.println(mapArray[y[0] / UNIT_SIZE][x[0] / UNIT_SIZE]);
        if ((appleX % UNIT_SIZE + appleY % UNIT_SIZE) != mapArray[appleY / UNIT_SIZE][appleX / UNIT_SIZE]) {
            newApple();
        }
    }

    // Snake's move and direction
    public void move() {
        for (int i = bodyParts ; i > 0 ; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        for (int i = bodyParts2 ; i > 0 ; i--) {
            x2[i] = x2[i - 1];
            y2[i] = y2[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
        switch (direction2) {
            case 'U':
                y2[0] = y2[0] - UNIT_SIZE;
                break;
            case 'D':
                y2[0] = y2[0] + UNIT_SIZE;
                break;
            case 'L':
                x2[0] = x2[0] - UNIT_SIZE;
                break;
            case 'R':
                x2[0] = x2[0] + UNIT_SIZE;
                break;
        }
    }

    // Checks if head collides with body
    public void checkCollisions() {
        if (player2 == false) {
            for (int i = bodyParts ; i > 0 ; i--) {
                if ((x[0] == x[i]) && (y[0] == y[i])) {
                    gamemode = false;
                }
            }
            if ((x[0] < 0) || (x[0] > SCREEN_WIDTH - 1) || (y[0] < 0) || (y[0] > SCREEN_HEIGHT - 1)) {  // 撞牆
                gamemode = false;
            }
            try {
                boolean tf = (x[0] % UNIT_SIZE + y[0] % UNIT_SIZE) != mapArray[y[0] / UNIT_SIZE][x[0] / UNIT_SIZE];
                if (tf == true) {
                    gamemode = false;
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                gamemode = false;
            }
        } else {
            winner = 0;
            for (int i = bodyParts ; i > 0 ; i--) {
                if ((x[0] == x[i]) && (y[0] == y[i])) {
                    gamemode = false;
                    winner = 2;
                    ScorePanel.ShowUp2(winner);
                    ScorePanel.setVisible(true);

                }
            }
            for (int i = bodyParts2 ; i > 0 ; i--) {
                if ((x2[0] == x2[i]) && (y2[0] == y2[i])) {
                    gamemode = false;
                    winner = 1;
                    ScorePanel.ShowUp2(winner);
                    ScorePanel.setVisible(true);
                }
            }
            if ((x[0] < 0) || (x[0] > SCREEN_WIDTH - 1) || (y[0] < 0) || (y[0] > SCREEN_HEIGHT - 1)) {
                gamemode = false;
                winner = 2;
                ScorePanel.ShowUp2(winner);
                ScorePanel.setVisible(true);
            }
            try {
                boolean tf = (x[0] % UNIT_SIZE + y[0] % UNIT_SIZE) != mapArray[y[0] / UNIT_SIZE][x[0] / UNIT_SIZE];
                if (tf == true) {
                    gamemode = false;
                    winner = 2;
                    ScorePanel.ShowUp2(winner);
                    ScorePanel.setVisible(true);
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                gamemode = false;
                winner = 2;
                ScorePanel.ShowUp2(winner);
                ScorePanel.setVisible(true);
            }

            if ((x2[0] < 0) || (x2[0] > SCREEN_WIDTH - 1) || (y2[0] < 0) || (y2[0] > SCREEN_HEIGHT - 1)) {
                gamemode = false;
                winner = 1;
                ScorePanel.ShowUp2(winner);
                ScorePanel.setVisible(true);
            }
            try {
                boolean tf = (x2[0] % UNIT_SIZE + y2[0] % UNIT_SIZE) != mapArray[y2[0] / UNIT_SIZE][x2[0] / UNIT_SIZE];
                if (tf == true) {
                    gamemode = false;
                    winner = 1;
                    ScorePanel.ShowUp2(winner);
                    ScorePanel.setVisible(true);
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                gamemode = false;
                winner = 1;
                ScorePanel.ShowUp2(winner);
                ScorePanel.setVisible(true);
            }
        }
    }

    // Restart information show
    public void gameOver(Graphics g) {
        // Score
        if (player2 == false) {
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.BOLD, 40));
            g.drawString("Score: " + score, (SCREEN_WIDTH / 2) - (160 / 2), g.getFont().getSize());
        }
        // Add
        // second==========================================================================
        g.setColor(Color.red);
        g.setFont(new Font(null, Font.BOLD, 30));
        g.drawString("Second: " + second + "s", (SCREEN_WIDTH / 2) - (160 / 2), SCREEN_HEIGHT / 2 + 80);

        // Record score
        // =======================================================================
        int flag = 0;
        for (int j = 0 ; j < 5 ; j++) {
            if (historyscore[j] == 0) {
                historyscore[j] = score;
                historyscore = sort(historyscore);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            for (int i = 0 ; i < 5 ; i++) {
                if (score > historyscore[i]) {
                    for (int j = 4 ; j > i ; j--) {
                        historyscore[j] = historyscore[j - 1];
                    }
                    historyscore[i] = score;
                    break;
                }
            }
        }

        if (player2 == false) {
            ScorePanel.ShowUp(historyscore);
            ScorePanel.setVisible(true);
        }
        // ====================================================================================

        // Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        // setting
        gamemode = false;
        VisiableSnake = false;
        Initialize();
        // add button
        JButton restart = new JButton("返回開始");
        restart.setBackground(Color.black);
        restart.setForeground(Color.RED);
        restart.setFont(new Font("標楷體", Font.CENTER_BASELINE, 30));
        restart.setBorder(null);
        restart.setBounds((SCREEN_WIDTH / 2) - (160 / 2), (SCREEN_HEIGHT / 3 * 2) + 50, 160, 50);
        restart.setContentAreaFilled(false);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RemovePanel();
                StartPanel();
            }
        });
        this.add(restart);
    }

    // Start information show
    public void StartPanel() {
        JButton start1 = new JButton("單人遊玩");
        JButton start2 = new JButton("雙人遊玩");
        JButton start3 = new JButton("載入地圖");

        this.setLayout(null);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setBackground(new Color(117, 111, 111));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        start1.setBackground(new Color(176, 164, 164));
        start1.setFont(new Font("標楷體", Font.CENTER_BASELINE, 30));
        start1.setBorder(null);
        start1.setBounds((SCREEN_WIDTH / 2) - (160 / 2), (SCREEN_HEIGHT / 3 * 2) - 120, 160, 40);
        start1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisiableSnake = true;
                player2 = false;
                gamemode = true;
                GameTimer();
                newApple();
                ScorePanel.setVisible(false);
            }
        });

        start2.setBackground(new Color(176, 164, 164));
        start2.setFont(new Font("標楷體", Font.CENTER_BASELINE, 30));
        start2.setBorder(null);
        start2.setBounds((SCREEN_WIDTH / 2) - (160 / 2), (SCREEN_HEIGHT / 3 * 2) - 60, 160, 40);
        start2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisiableSnake = true;
                player2 = true;
                gamemode = true;
                GameTimer();
                newApple();
                ScorePanel.setVisible(false);
            }
        });

        start3.setBackground(new Color(176, 164, 164));
        start3.setFont(new Font("標楷體", Font.CENTER_BASELINE, 30));
        start3.setBorder(null);
        start3.setBounds((SCREEN_WIDTH / 2) - (160 / 2), (SCREEN_HEIGHT / 3 * 2), 160, 40);
        start3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser filechooser = new JFileChooser("../");
                int response = filechooser.showOpenDialog(null);
                if (response == filechooser.APPROVE_OPTION) {
                    try {
                        FileReader reader = new FileReader(filechooser.getSelectedFile());
                        int dataa = reader.read();
                        int i = 0;
                        int j = 0;
                        while (dataa != -1) {
                            if ((char) dataa != '\n' && i <= 19) {
                                mapArray[i][j] = (char) dataa - 48;
                                j++;
                            } else {
                                i++;
                                j = 0;
                            }
                            dataa = reader.read();
                        }
                        reader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    BufferedReader reader;

                    try {
                        reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                        // String line = reader.readLine();
                        int count = 0;
                        while (true) {
                            int dataa = reader.read();
                            if ((char) dataa == '\n') {
                                count += 1;
                            }
                            if (count == 20) {
                                break;
                            }
                        }
                        // int count=0;
                        String line = reader.readLine();
                        if (line != null) {
                            // line.split(line)
                            String a[] = line.split(" ");
                            Ur = Integer.valueOf(a[0]);
                            Ug = Integer.valueOf(a[1]);
                            Ub = Integer.valueOf(a[2]);
                            Pr = Integer.valueOf(a[3]);
                            Pg = Integer.valueOf(a[4]);
                            Pb = Integer.valueOf(a[5]);
                        }

                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("============================================================");
                    for (int a = 0 ; a <= 19 ; a++) {
                        for (int b = 0 ; b <= 19 ; b++) {
                            System.out.print(mapArray[a][b]);
                        }
                        System.out.println();
                    }

                }
            }
        });
        this.add(start1);
        this.add(start2);
        this.add(start3);
    }

    // Remove Panel
    public void RemovePanel() {
        this.removeAll();
        this.validate();
        this.repaint();
    }

    public int[] sort(int[] input) {
        for (int i = 0 ; i < input.length - 1 ; i++) {
            for (int j = i + 1 ; j < input.length ; j++) {
                if (input[i] < input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    public void addslider() {
        slider.setPreferredSize(new Dimension(200, 100));
        slider.setBounds(SCREEN_WIDTH / 11 * 4, SCREEN_HEIGHT / 11 * 6, 200, 100);
        slider.setBackground(Color.black);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);

        // SliderLabel.setBackground(Color.red);
        SliderLabel.setForeground(Color.red);
        SliderLabel.setFont(new Font("Ink Free", Font.BOLD, 34));
        SliderLabel.setBounds(SCREEN_WIDTH / 14 * 9, SCREEN_HEIGHT / 11 * 6, 200, 100);

        SliderLabel2.setForeground(Color.red);
        SliderLabel2.setFont(new Font("Ink Free", Font.BOLD, 34));
        SliderLabel2.setBounds(SCREEN_WIDTH / 14 * 3, SCREEN_HEIGHT / 11 * 6, 200, 100);
    }
}