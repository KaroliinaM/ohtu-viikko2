package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private final int maxScore = 4;
    private final int winScore = 2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        String score = "";
        //     int tempScore = 0;
        if (player1Score == player2Score) {
            score = tie(score);

        } else if (player1Score >= maxScore || player2Score >= maxScore) {
            score = win(score);

        } else {
            score = gameScore(score);

        }
        return score;
    }

    private String tie(String score) {
        if (player1Score < this.maxScore) {
            score = scoretoString(player1Score) + "-All";
        } else {
            score = "Deuce";
        }
        return score;
    }

    private String win(String score) {
        int minusResult = player1Score - player2Score;
        if (minusResult < this.winScore && minusResult > -this.winScore) {
            score = "Advantage "+ this.winningPlayer();
        } else {
           score = "Win for "+ this.winningPlayer(); 
        }
        return score;
    }

    private String gameScore(String score) {
        score = scoretoString(player1Score) + "-" + scoretoString(player2Score);
        return score;
    }

    private String scoretoString(int score) {
        String ret = "";
        switch (score) {
            case 0:
                ret = "Love";
                break;
            case 1:
                ret = "Fifteen";
                break;
            case 2:
                ret = "Thirty";
                break;
            case 3:
                ret = "Forty";
                break;
        }
        return ret;
    }
    
    private String winningPlayer() {
        if (this.player1Score > player2Score) {
            return "player1";
        } else if (this.player2Score > player1Score) {
            return "player2";
        } else {
            return "tie";
        }
    }
}
