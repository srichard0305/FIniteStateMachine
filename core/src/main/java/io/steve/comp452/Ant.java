package io.steve.comp452;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Ant {

    private Sprite ant;
    private float x, y;
    private float maxSpeed = 1.5f;
    private ArrayList<Node> path;
    private Vector2 result;
    private float angle;
    private StaticStateMachine staticStateMachine;
    int [][] costGraph; 

    public enum state{
        searchForFood, returnToNest, searchForWater;
    }


    Ant(int [][] costGraph){
        ant = new Sprite(new Texture("ant.png"));
        x = y = 0f;
        this.costGraph = costGraph;
    }

    public void draw(Batch batch){
        if(path.size() > 1) {
            Node targetNode = followPath();
            Vector2 seekResult = seek(convertCoordinates(targetNode.getX()), convertCoordinates(targetNode.getY()));
            x += seekResult.x;
            y += seekResult.y;
            ant.setPosition(x, y);
            ant.setRotation(align(convertCoordinates(targetNode.getX()), convertCoordinates(targetNode.getY())) - 90);
        }
        ant.draw(batch);
    }

    private Node followPath(){
        if(x/50 == path.get(0).getX() && y/50 == path.get(0).getY()){
            Node temp = path.get(1);
            path.remove(0);
            path.trimToSize();
            return temp;
        }

        return path.get(0);
    }

    private float convertCoordinates(float coordinate){
        return coordinate * 50;
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
        float desiredSpearation = (ant.getWidth() + ant.getHeight());
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


    }
}

