package io.steve.comp452;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Ant {

    private Sprite ant;
    public float x, y;
    private float maxSpeed = 1.5f;
    private ArrayList<Node> path;
    private Vector2 result;
    private float angle;
    private StaticStateMachine staticStateMachine;
    Rectangle boundingRec;
    Rectangle nestBoundingRec;

    public State state;
    public enum State{
        searchForFood, returnToNest, searchForWater;
    }
    Random rand;
    Node randomToSearch;
    ArrayList<Node> food;
    ArrayList<Node> water;

    Ant(ArrayList<Node> food, ArrayList<Node> water){

        ant = new Sprite(new Texture("ant.png"));
        x = y = 1f;
        // initial state is searching for food
        state = State.searchForFood;
        staticStateMachine = new StaticStateMachine(this);
        rand = new Random();
        randomToSearch = new Node(rand.nextInt(16 - 1) + 1, rand.nextInt(16 - 1) + 1);
        this.food = food;
        this.water = water;
        boundingRec = new Rectangle(x, y, 15, 15);
        nestBoundingRec = new Rectangle(0,0, 30, 30);
    }

    private Vector2 seek(float goalX, float goalY){

        result = new Vector2();

        // get direction to target
        result.set(goalX - x, goalY - y);

        // the velocity in this direction at max speed
        result.nor();
        result.set(result.x * maxSpeed, result.y * maxSpeed);

        return result;

    }


    private float align(float goalX, float goalY){
        angle = (float) Math.atan2(goalY - y, goalX - x);
        angle -= Math.PI/2;
        angle = angle * MathUtils.radDeg;
        return angle;
    }

    public Vector2 separate(ArrayList<Ant> colony){
        float desiredSpearation = (ant.getWidth() - 20 + ant.getHeight() - 20);
        Vector2 sum = new Vector2();
        int count = 0;

        for(Ant ant : colony){
            float d = Vector2.dst(this.ant.getX(), this.ant.getY(), ant.x, ant.y);

            if(d > 0 && d < desiredSpearation){
                Vector2 diff = new Vector2(this.ant.getX() - ant.x, this.ant.getY() -  ant.y);
                diff.nor();
                diff.set(diff.x/d, diff.y/d);
                sum.add(diff);
                count++;
            }
        }

        if(count > 0){

            sum.set(sum.x/(float) count, sum.y/(float) count);
            sum.nor();
            sum.set(sum.x*maxSpeed, sum.y*maxSpeed);
            return sum;
        }
        else
            return new Vector2(0,0);

    }


    public void update(ArrayList<Ant> antColony, Batch batch) {

        staticStateMachine.update();
        if(state == State.searchForFood){
            //if ant has reached random node - re roll
            if(boundingRec.overlaps(randomToSearch.boundingRec)){
                randomToSearch.setX(rand.nextInt(16 ));
                randomToSearch.setY(rand.nextInt(16));
                randomToSearch.boundingRec.setPosition((randomToSearch.getX()*50)  + 10,(randomToSearch.getY()*50) + 10);
            }

            Vector2 seekResult = seek(randomToSearch.getX()*50, randomToSearch.getY()*50);
            float angle = align(randomToSearch.getX()*50, randomToSearch.getY()*50);
            Vector2 separateResult = separate(antColony);
            x += seekResult.x + separateResult.x;
            y += seekResult.y + separateResult.y;
            ant.setPosition(x, y);
            ant.setRotation(angle - 90);
            boundingRec.setPosition(x,y);
            ant.draw(batch);
        }
        else if(state == State.returnToNest){
            Vector2 seekResult = seek(0, 0);
            float angle = align(0, 0);
            Vector2 separateResult = separate(antColony);
            x += seekResult.x + separateResult.x;
            y += seekResult.y + separateResult.y;
            ant.setPosition(x, y);
            ant.setRotation(angle - 90);
            boundingRec.setPosition(x,y);
            ant.draw(batch);

        }
        else if(state == State.searchForWater){

            if(boundingRec.overlaps(randomToSearch.boundingRec)){
                randomToSearch.setX(rand.nextInt(16 ));
                randomToSearch.setY(rand.nextInt(16));
                randomToSearch.boundingRec.setPosition((randomToSearch.getX()*50)  + 10,(randomToSearch.getY()*50) + 10);
            }

            Vector2 seekResult = seek(randomToSearch.getX()*50, randomToSearch.getY()*50);
            float angle = align(randomToSearch.getX()*50, randomToSearch.getY()*50);
            Vector2 separateResult = separate(antColony);
            x += seekResult.x + separateResult.x;
            y += seekResult.y + separateResult.y;
            ant.setPosition(x, y);
            ant.setRotation(angle - 90);
            boundingRec.setPosition(x,y);
            ant.draw(batch);
        }

    }
}

