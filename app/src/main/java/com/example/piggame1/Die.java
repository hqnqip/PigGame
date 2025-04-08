package com.example.piggame1;

import java.util.Random;

public class Die
{
    private int side;
    Random random = new Random();

    public Die() {
        side = 6;
    }

    public void roll()
    {
        side = random.nextInt(6)+1;
    }

    public int getSide() {
        return side;
    }
}
