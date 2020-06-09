package tankgame.map;

import tankgame.GameWorld;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.breakable.bWall;
import tankgame.gameobjects.collidable.powerup.HealthPowerup;
import tankgame.gameobjects.collidable.unbreakable.ubWall;

import java.io.*;
import java.util.Scanner;

public class MapLoader {
    private String file = "resources/map.txt";

    private GameObject unbreakableWall,
                        breakableWall,
                        health,
                        healthPack;
    private Scanner scanner;

    public void loadMap(){
        try {//Read in the map.txt file
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;        //Holds each line of txt as it's being read from the file
        int w = 0, h = 0;   //Marks the position (w,h) of the map object to be painted on the screen

        //Start loading file contents
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            System.out.println(line); //test line

            char c;

            for(int x = 0; x < line.length(); x++){
                c = line.charAt(x);
                // 0 : Unbreakable Wall
                if(c == '0'){

                    unbreakableWall = new ubWall(w, h);
                    GameWorld.mapObjects.add(unbreakableWall);
                }
                // 1 : Breakable Wall
                if(c == '1'){
                    breakableWall = new bWall(w, h);
                    GameWorld.mapObjects.add(breakableWall);
                }
                // 2 : Health powerup
                if(c == '2'){
                    health = new HealthPowerup(w, h);
                    GameWorld.mapObjects.add(health);
                    GameWorld.healthPacks.add(health);
                }
                w += 32;
            }
            w = 0;
            h+=32;
        }

    }
}
