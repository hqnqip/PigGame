import java.util.Random;
import java.util.Scanner;

public class Scoring
{
    int roll1, roll2;
    int score = 0;
    int points = 0;

    /*
    public rollinnscorin(int score)
    {
        this.score = score;
    }
    */

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int Roll(int score, int point, String name)
    {
        Random ran = new Random();
        roll1 = ran.nextInt(7);
        roll2 = ran.nextInt(7);
        return (checkRollPointsDuoPlayer(roll1, roll2, score, point, name));
    }


    public int checkRollPointsDuoPlayer(int roll1, int roll2, int score, int point, String name)
    {
        System.out.println(name + "'s Score:" + score);//have the score prior to adding the points visible
        points = point;//if this is the second roll of a turn, then the points add onto the points of the prev turn, if not then points is set to 0 for the new turn
        System.out.println(name + " rolls: " + roll1 + " " + roll2);
        if (roll1 == 0 || roll2 == 0)//one of the dice rolls is zero, any points earned from this turn are depleted, returns score same as it was and turn goes to next player
        {
            if(roll1 == 0 && roll2 == 0)//BOTH dice rolls are zero, players score is set back to zero :(
            {
                score = 0;
                System.out.println("AW SHUCKS!!!! " + name + " lost ALLL their points >_<");
            }
            points = 0;
            return score;//returns the score without changing it
        }
        else
        {
            points += roll1 + roll2; //if neither rolls are zero, then points grows by the sum of the two dice rolls ^_^
        }

        System.out.println(name + " has earned " + points + " points so far on this turn roll again? ('y' for yes, anything else for no.)");
        //^ have the points from that turn visible as the dice continue to roll,,, ^and then also have button to roll again,
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("y"))//ough just change this to button and stuff argh
        {
            Roll(score, points, name);//calls the rolling mechansim w/ the points earned so far, so they continue to add up,
        }
        // if the player chooses to not roll again, it exits the method, and then enters it again through the other player
        //very important to not add points to the score until the very end and stuff, to account for if the player rolls a 0 and loses all the points from that turn
        score += points; //once the player decides to end their turn, THEN the score gets the points from the turn added on XP
        return score; //returns the new score, exits method and switches to next player
    }
}

