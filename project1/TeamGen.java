package project1;
//Matthew Corbett, Ezra Marin, Joseph Espiritu, Madison Coffey
//Project 1 September 1st 2022
//Summary: Random team generator, needs to have a tab delimeted .txt file called class_list.txt in the same directory
// gathers user's desired number of teams and randomly assigns members of the class list to each team.
// business proposal : https://docs.google.com/document/d/1YiS8PhjvK1Ie1ypjAluB__ktEBDpWNgGM3Bf3vXeoz4/edit?usp=sharing

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


public class TeamGen {

    // read the class_list.txt file
    public static void main(String[] args) {
      //intialize the class_list ArrayList of strings
      ArrayList<String> class_list = new ArrayList<String>();

        try {
          File myObj = new File("class_list.txt");
          Scanner myReader = new Scanner(myObj);

          //read file and load into the class_list ArrayList
          while (myReader.hasNextLine()) {

            String data = myReader.nextLine();
            class_list.add(data);
            
          }
          myReader.close();

        } 
        //error handling for the file reading
        catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }

        //take user input for how many teams
        System.out.println("How many teams would you like?");
        Scanner teamNumScanner = new Scanner(System.in);
        String strTeamNum = teamNumScanner.nextLine();
        teamNumScanner.close();

        //initailize the integer number of teams desired
        int intTeamNum = 0;

        //cast input as integer
      
        try{
          intTeamNum = Integer.parseInt(strTeamNum);

        } catch (Exception e){
          System.out.println("Sorry that wasn't a valid integer. Please enter a whole number");
        }      
        

        //shuffle class memebers - RANDOM ELEMENT
        Collections.shuffle(class_list);

        //team dictionary
        HashMap<Integer, ArrayList<String>> teamDict = new HashMap<Integer, ArrayList<String>>();

        //load the team dicitonary with empty lists {int : ArrayList}
        for (int team = 1; team <= intTeamNum ; team ++){

          ArrayList<String> holder = new ArrayList<String>();
          teamDict.put(team, holder);

        }

        //Initialize the team iterater variable
        int teamIter = 1;

        //add class memebers to each team one by one from the randomized list
        for (int i = 0; i < class_list.size(); i++) {
          
          teamDict.get(teamIter).add(class_list.get(i));
          teamIter++;

          if (teamIter > intTeamNum) {
            teamIter = 1;
          }
          

        }

        //format and return the assigned teams
        for (int iter = 1; iter <= intTeamNum; iter++){
          System.out.println("Group " + iter + ": " + teamDict.get(iter));
        }
      }
}