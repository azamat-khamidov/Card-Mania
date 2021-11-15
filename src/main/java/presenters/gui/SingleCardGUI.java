package presenters.gui;

import usecases.GameTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the Output class which presents the user the image of a single card as
 * originally specified in the constructor in its own window. The card shown can then be
 * changed using the sendOutput method.
 */
public class SingleCardGUI implements GameTemplate.Output {

    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    private JLabel currentCard = new JLabel();

    private final Map<String, String> stringToImage;

    /**
     * Creates a SingleCardGUI object which is displaying card.
     * @param card The card to be displayed to the user
     */
    public SingleCardGUI(String card) {
        final String[] CARDS_STRINGS = {"as", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "10s", "js", "qs", "ks",
                "ac", "2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "10c", "jc", "qc", "kc",
                "ad", "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "10d", "jd", "qd", "kd",
                "ah", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "jh", "qh", "kh"};
        final String[] CARD_IMAGES = {
                "1_spade.png", "2_spade.png", "3_spade.png", "4_spade.png", "5_spade.png", "6_spade.png", "7_spade.png", "8_spade.png", "9_spade.png", "10_spade.png", "jack_spade.png", "queen_spade.png", "king_spade.png",
                "1_club.png", "2_club.png", "3_club.png", "4_club.png", "5_club.png", "6_club.png", "7_club.png", "8_club.png", "9_club.png", "10_club.png", "jack_club.png", "queen_club.png", "king_club.png",
                "1_diamond.png", "2_diamond.png", "3_diamond.png", "4_diamond.png", "5_diamond.png", "6_diamond.png", "7_diamond.png", "8_diamond.png", "9_diamond.png", "10_diamond.png", "jack_diamond.png", "queen_diamond.png", "king_diamond.png",
                "1_heart.png", "2_heart.png", "3_heart.png", "4_heart.png", "5_heart.png", "6_heart.png", "7_heart.png", "8_heart.png", "9_heart.png", "10_heart.png", "jack_heart.png", "queen_heart.png", "king_heart.png"
        };

        //Creates the map from cards as strings to their images file names
        this.stringToImage = new HashMap<>();
        for (int i = 0; i < CARD_IMAGES.length; i++) {
            this.stringToImage.put(CARDS_STRINGS[i], CARD_IMAGES[i]);
        }

        //Creates a boarder for the panel
        this.panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        this.setCard(card);

        this.update();

    }

    /**
     * This method takes a card as a string and then finds the address of the image for that card and changes the
     * image on the panel to that of the new card.
     * @param card The new card that is going to be displayed in the window
     */
    private void setCard(String card) {
        card = card.toLowerCase();
        JLabel icon = new JLabel(new ImageIcon("src/main/resources/cards/" + this.stringToImage.get(card)));

        this.panel.remove(this.currentCard);
        this.currentCard = icon;
        this.panel.add(icon);

        this.update();
    }

    /**
     * This method visually changes the GUI to match the new values that have been assigned to the parts
     */
    private void update() {
        this.frame.add(this.panel, BorderLayout.CENTER);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("GUI");
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     * Displays entered card to the user on the screen in the window
     * @param s The card that is to be displayed
     */
    @Override
    public void sendOutput(Object s) {
        this.setCard(s.toString());
    }

    /**
     * This method will close the GUI when called.
     */
    public void close(){
        this.frame.dispose();
    }

    /**
     * This overload version will first display a message to the user before closing.
     * @param message The string that is the message that is shown to the user.
     */
    public void close(String message){
        JOptionPane.showMessageDialog(this.frame, message);
        this.frame.dispose();
    }
}