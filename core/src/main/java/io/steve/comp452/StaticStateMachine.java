package io.steve.comp452;

import com.badlogic.gdx.Gdx;

public class StaticStateMachine {

    Ant ant;
    int [][] costGraph;

    public StaticStateMachine(Ant ant){
        this.ant = ant;
    };

    public void update(){

        //check ant state
        if(ant.state == Ant.State.searchForFood){
            // go through each food placed on the map
            for(Node food : ant.food){
                // if ant is on food change state to return to nest
                if(ant.boundingRec.overlaps(food.boundingRec)){
                    ant.state = Ant.State.returnToNest;
                    Gdx.app.log("", "Found food returning to nest");
                }
            }
        }
        // if return to nest state is back on the nest search for water
        else if(ant.state == Ant.State.returnToNest){
            if(ant.boundingRec.overlaps(ant.nestBoundingRec)) {
                ant.state = Ant.State.searchForWater;
                Gdx.app.log("", "returned to nest seaching for water");
            }

        }
        // if searching for water test each node with water
        else if(ant.state == Ant.State.searchForWater){
            for(Node water : ant.water){
                if(ant.boundingRec.overlaps(water.boundingRec)){
                    ant.state = Ant.State.searchForFood;
                    Gdx.app.log("", "found water seraching for food");
                }
            }
        }


    }



}
