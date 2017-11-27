package demo.myapp.com.crictracker.model;

/**
 * Created by eghambaram on 14/11/17.
 */

public class Player {
    private String playerId;
    private String playerName;
    private String playerGenre;
    private String playerImage;

    private String p_batMatches;
    private String p_batInnings;
    private String p_batRuns;
    private String p_Fours;
    private String p_Sixes;
    private String p_0s;
    private String p_batHigest;

    private String p_bowMatches;
    private String p_bowInnings;
    private String p_bowOvers;
    private String p_wkts;
    private String p_maidens;
    private String p_bowBest;

    public Player(){
        //this constructor is required
    }

    public Player(String playerId, String playerName, String playerGenre, String playerImage, String p_batMatches,
                  String p_batInnings, String p_batRuns, String p_Fours, String p_Sixes, String p_0s, String p_batHigest,
                  String p_bowMatches, String p_bowInnings, String p_bowOvers, String p_wkts, String p_maidens, String p_bowBest) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerGenre = playerGenre;
        this.playerImage = playerImage;
        this.p_batMatches = p_batMatches;
        this.p_batInnings = p_batInnings;
        this.p_batRuns = p_batRuns;
        this.p_Fours = p_Fours;
        this.p_Sixes = p_Sixes;
        this.p_0s = p_0s;
        this.p_batHigest = p_batHigest;
        this.p_bowMatches = p_bowMatches;
        this.p_bowInnings = p_bowInnings;
        this.p_bowOvers = p_bowOvers;
        this.p_wkts = p_wkts;
        this.p_maidens = p_maidens;
        this.p_bowBest = p_bowBest;
    }



    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerGenre() {
        return playerGenre;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public String getP_batMatches() {
        return p_batMatches;
    }

    public String getP_batInnings() {
        return p_batInnings;
    }

    public String getP_batRuns() {
        return p_batRuns;
    }

    public String getP_Fours() {
        return p_Fours;
    }

    public String getP_Sixes() {
        return p_Sixes;
    }

    public String getP_0s() {
        return p_0s;
    }

    public String getP_batHigest() {
        return p_batHigest;
    }

    public String getP_bowMatches() {
        return p_bowMatches;
    }

    public String getP_bowInnings() {
        return p_bowInnings;
    }

    public String getP_bowOvers() {
        return p_bowOvers;
    }

    public String getP_wkts() {
        return p_wkts;
    }

    public String getP_maidens() {
        return p_maidens;
    }

    public String getP_bowBest() {
        return p_bowBest;
    }


}


