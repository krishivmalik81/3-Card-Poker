package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CardGame {
    // instance variables
    private ArrayList<Card> deck;
    private ArrayList<Card> playerOneCards, playerTwoCards, currentPlayer;

    boolean displayCards;
    


    // Start location for the cards
    private int x;
    private int y;
    // money with P1, P2, winnings, and bet
    private int moneyP1, moneyP2, winnings, bet, currentP;
    private boolean hasBet;


   public CardGame(){
        // instantiate deck and player hand
        deck = new ArrayList<Card>();
        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        currentPlayer = playerOneCards;
        displayCards = true;
        hasBet = false;


        // set up drawing location for player cards
        x = 300;
        y = 300;

        // assigning values to money and winnings;
        moneyP1 = 50;
        moneyP2 = 50;
        winnings = 0;
        bet = 0;
        currentP = 1;
        
        try {
            // Set up the deck
            createDeck();
        } catch (IOException ex) {
            System.out.println("Error Reading cards: " + ex);
            System.exit(0);
        }    
	}


    private void createDeck() throws IOException{
        // instantiate the File and Scanner objects
        File fObj = new File("cards.txt");
        Scanner sc = new Scanner(fObj);
        // Reads the cards from a file and instantiates the deck Cards
        while (sc.hasNext()) {
            deck.add(new Card(sc.nextInt(), sc.next(), sc.next(), sc.next()));
        }   
        sc.close();	
        // shuffle the deck
        shuffle();
    }




	public void drawCards(Graphics g){

        if(displayCards){
            int drawX = x;
            if(currentPlayer.size()>0){
                for (int i=0; i < currentPlayer.size(); i++) {
                    Card card = currentPlayer.get(i);
                    if(card.getSelected()){
                        g.setColor(Color.yellow);
                    }
                    else{g.setColor(Color.white);}
       
                    //fill the card with color
                    g.fillRect(drawX,y,110,150);
                    g.setColor(Color.black);
                    //outline the card
                    g.drawRect(drawX,y,110,150);
        
                    //draw suit
                    g.drawImage(card.getImage(), drawX+2, y, null);
        
                
                
                    drawX = drawX + 120;
                }
            }
        }
    }

    public void drawMoney(Graphics g){
        String money;

        if(currentP==1) money = "$ "+moneyP1;
        else money = "$ "+moneyP2;

        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(money, 625, 100);    
    }



    private void shuffle(){
           //write code to shuffle your deck
           for (int i = deck.size() - 1; i > 0; i--) {
           int j = (int)(Math.random() * deck.size()-1);
           Card temp = deck.get(i);
           deck.set(i, deck.get(j));
           deck.set(j, temp);
       }           
    }
    
    //deal a card to player one
    public void dealCard(){
        if(deck.size()>0){
            if(currentPlayer.size()<3){
            currentPlayer.add(deck.remove(0));
            }
        }
    }

    
    
    //returns winnings
    public int getWinnings(){
        return winnings;
    }

    //draw playerOneCards
    public void drawP1Cards(Graphics g){
        int x = 300;
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Player One Cards : ", 70, 190);
        for(int i = 0; i<playerOneCards.size(); i++){
            g.drawImage(playerOneCards.get(i).getImage(), x, 100, null);
            x+=120;
        }
    }

    //draw playerTwoCards
    public void drawP2Cards(Graphics g){
        int x = 300;
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Player Two Cards : ", 70, 380);
        for(int i = 0; i<playerTwoCards.size(); i++){
            g.drawImage(playerTwoCards.get(i).getImage(), x, 300, null);
            x+=120;
        }
    }

    //draw Start Screen
    public void drawStartScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Welcome to the Three Card Brag!", 100, 75);

        //instructions
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Each player will deal 3 cards and click the bet button before switching", 50, 150);
        g.drawString("There are 4 hand rankings :", 50, 175);
        g.drawString("The first hand ranking is trail which means three of a kind", 50, 200);
        g.drawString("Then it's color which means the same suit", 50, 225);
        g.drawString("Then it's pair which means two of a kind", 50, 250);
        g.drawString("If a player doesn't qualify for any of these hand rankings,", 50, 275);
        g.drawString("Player with the highest card wins", 50, 300);
            
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Press ENTER to start", 300, 400);
    }

    public void switchPlayer(){
        if(currentPlayer == playerOneCards){
            currentPlayer = playerTwoCards;
            currentP = 2;
        }
        else{
            currentPlayer = playerOneCards;
            currentP = 1;
        }
        hasBet = false;
        displayCards = false;
        System.out.println(winnings);
    }

    //set DisplayCards to true
    public void play(){
        displayCards = true;
    }

    //hand rankings :
    //pure color
    public boolean isP1SameColor(){
        if(playerOneCards.size()>=3){
            if(playerOneCards.get(0).getSuit().equals(playerOneCards.get(1).getSuit()) && 
            playerOneCards.get(0).getSuit().equals(playerOneCards.get(2).getSuit())){
                return true;
            }
        }
        return false;
    }

    public boolean isP2SameColor(){
        if(playerTwoCards.size()>=3){
            if(playerTwoCards.get(0).getSuit().equals(playerTwoCards.get(1).getSuit()) && 
            playerTwoCards.get(0).getSuit().equals(playerTwoCards.get(2).getSuit())){
                return true;
            }
        }
        return false;
    }

    //trail
    public boolean isP1Trail(){
        if(playerOneCards.size()>=3){
            if(playerOneCards.get(0).getName().equals(playerOneCards.get(1).getName()) && 
            playerOneCards.get(0).getName().equals(playerOneCards.get(2).getName())){
                return true;
            }
        }
        return false;
    }

    public boolean isP2Trail(){
        if(playerTwoCards.size()>=3){
            if(playerTwoCards.get(0).getName().equals(playerTwoCards.get(1).getName()) && 
            playerTwoCards.get(0).getName().equals(playerTwoCards.get(2).getName())){
                return true;
            }
        }
        return false;
    }

    //pair
    public boolean isP1Pair(){
        if(playerOneCards.size()>=3){
            if(playerOneCards.get(0).getName().equals(playerOneCards.get(1).getName()) || 
            playerOneCards.get(0).getName().equals(playerOneCards.get(2).getName()) ||
            playerOneCards.get(1).getName().equals(playerOneCards.get(2).getName()) ){
                return true;
            }
        }
        return false;
    }
    public boolean isP2Pair(){
        if(playerTwoCards.size()>=3){
            if(playerTwoCards.get(0).getName().equals(playerTwoCards.get(1).getName()) || 
            playerTwoCards.get(0).getName().equals(playerTwoCards.get(2).getName()) ||
            playerTwoCards.get(1).getName().equals(playerTwoCards.get(2).getName()) ){
                return true;
            }
        }
        return false;
    }

    //largest 
    private int p1Largest(){
        int largest = 0;
        if(playerOneCards.size()>=3){
            for(int i = 0; i<1; i++){
                if(playerOneCards.get(i).getValue()>largest){
                    largest = playerOneCards.get(i).getValue();
                }
            }
        }
        return largest;
    }
    
    private int p2Largest(){
        int largest = 0;
        if(playerTwoCards.size()>=3){
            for(int i = 0; i<1; i++){
                if(playerTwoCards.get(i).getValue()>largest){
                    largest = playerTwoCards.get(i).getValue();
                }
            }
        }
        return largest;
    }

    public boolean hasLargest(){
        if(p1Largest()>p2Largest()){
            return true;
        }
        else{
            return false;
        }
    }

    //betting method
    public void bet(){
        
        if (!hasBet) {  // Check if the player has not already bet
            if (currentP == 1) {
                moneyP1 -= 5;
            } 
            else {
                moneyP2 -= 5;
            }
            bet += 5;
            winnings += 5;
            hasBet = true;  // Mark that the current player has bet
        }
    }

    //which player wins
    public boolean hasWon(){
        // if trail
        if(isP1Trail()){
            return true;
        }
        else if(isP2Trail()){
            return false;
        }
        //if same colour
        else if(isP1SameColor()){
            return true;
        }
        else if(isP2SameColor()){
            return false;
        }
        //if pair
        else if(isP1Pair()){
            return true;
        }
        else if(isP2Pair()){
            return false;
        }
        //else largest wins
        else if(hasLargest()){
            return true;
        }
        return false;
    }
 

    


}
