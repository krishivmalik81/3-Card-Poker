import game.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener, KeyListener {

    //instance variables
    private CardGame gameObj;
    private Sound sound;
    private JButton dealCard, switchPlayer, play, bet, show;
    private int gameState;

    public Screen() {
        setFocusable(true);
        setLayout(null);
        gameObj = new CardGame();
        gameState = 0;
        sound = new Sound();

        // Set up buttons
        switchPlayer = new JButton("Switch Player");
        switchPlayer.setBounds(100, 50, 130, 30);
        switchPlayer.addActionListener(this);
        add(switchPlayer);
        switchPlayer.setVisible(false);

        bet = new JButton("Bet");
        bet.setBounds(450, 50, 130, 30);
        bet.addActionListener(this);
        add(bet);
        bet.setVisible(false);

        play = new JButton("PLAY");
        play.setBounds(300, 50, 100, 30);
        play.addActionListener(this);
        add(play);
        play.setVisible(false);

        dealCard = new JButton("Deal Card");
        dealCard.setBounds(300, 50, 100, 30);
        dealCard.addActionListener(this);
        add(dealCard);
        dealCard.setVisible(false);
        
        show = new JButton("Show");
        show.setBounds(100, 100, 100, 30);
        show.addActionListener(this);
        add(show);
        show.setVisible(false);

        // addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == 0) {
            // Start screen
            gameObj.drawStartScreen(g);
        } 
        else if(gameState==1) {
            // Game screen
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, getWidth(), getHeight());
            gameObj.drawCards(g);
            gameObj.drawMoney(g);
        }
        else if(gameState==2){
            //show cards
            g.setColor(new Color(255, 10, 20));
            g.fillRect(0,0,800,600);
            gameObj.drawP1Cards(g);
            gameObj.drawP2Cards(g);
            g.setColor(Color.BLACK);
            g.drawString("Click the 'O' key to see the results", 200, 520);
        }
        else{
            //end screen
            g.setColor(Color.GREEN);
            g.fillRect(0,0,800,600);
            g.setColor(Color.black);
            if(gameObj.hasWon()){
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Player One has won $"+ gameObj.getWinnings(), 200, 270);
            g.drawString("Click the 'B' key to go back", 200, 400);
            }
            if(gameObj.hasWon()==false){
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Player Two has won $"+ gameObj.getWinnings(), 200, 270);
            g.drawString("Click the 'B' key to go back", 200, 400);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (gameState == 1) {
            if (e.getSource() == dealCard) {
                gameObj.dealCard();
                sound.shuffleSound();
            } 
            else if (e.getSource() == switchPlayer) {
                switchPlayer.setVisible(false);
                play.setVisible(true);
                bet.setVisible(false);
                show.setVisible(false);
                gameObj.switchPlayer();
            } 
            else if (e.getSource() == play) {
                play.setVisible(false);
                switchPlayer.setVisible(true);
                bet.setVisible(true);
                show.setVisible(true);
                gameObj.play();
            }
            else if(e.getSource() == bet){
                gameObj.bet();
            }
            else if(e.getSource() == show){
                gameState=2;
                switchPlayer.setVisible(false);
                dealCard.setVisible(false);
                bet.setVisible(false);
                show.setVisible(false);
                sound.drumRollSound();
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && gameState == 0) {
            gameState = 1;//to go to the game
            dealCard.setVisible(true);
            switchPlayer.setVisible(true);
            bet.setVisible(true);
            show.setVisible(true);
            repaint();
        }
       
        if(e.getKeyCode() == KeyEvent.VK_O){
            gameState++;// changes the gamestate 
            System.out.println("Switching to gameState "+ gameState);
            if(gameState == 1){
                dealCard.setVisible(true);
                switchPlayer.setVisible(true);
                bet.setVisible(true);
                show.setVisible(true);
            }
            if(gameState==3){
                sound.winSound();
            }
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_B && gameState >= 3) {
            gameState = 2;//goes back to show
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}