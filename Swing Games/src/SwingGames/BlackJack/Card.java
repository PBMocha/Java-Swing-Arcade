package SwingGames.BlackJack;

public class Card {

    private int point;
    private Suite suite;
    private Rank rank;
    private String imagePath;

    public Card() {
        this(Rank.ACE, null ,null);
    }

    public Card(Rank rank, Suite suite, String imagePath) {
        this.rank = rank;
        this.suite = suite;
        this.imagePath = imagePath;
    }







}
