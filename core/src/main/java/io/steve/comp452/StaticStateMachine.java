package io.steve.comp452;

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
                if(ant.x/50 == food.getX() && ant.y/50 == food.getY()){
                    ant.state = Ant.State.returnToNest;
                    return;
                }
            }
        }
        // if return to nest state is back on the nest search for water
        else if(ant.state == Ant.State.returnToNest){
            if(ant.x == 0 && ant.y == 0)
                ant.state = Ant.State.searchForWater;
        }
        // if searching for water test each node with water
        else if(ant.state == Ant.State.searchForWater){
            for(Node water : ant.water){
                if(ant.x/50 == water.getX() && ant.y/50 == water.getY()){
                    ant.state = Ant.State.searchForFood;
                    return;
                }
            }
        }


    }



}
