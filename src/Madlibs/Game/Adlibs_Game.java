package Madlibs.Game;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.ListIterator;

public class Adlibs_Game{
    private ArrayList<String> entries;      //user entries
    private Adlib_Template template;        //story template

    
    public Adlibs_Game(){

        // intitial instructions and welcome message
	    System.out.printf(
	    "                       Welcome to Adlibs!!\n\n"+
	    "The way this works is a prompt will pop up and ask you to choose"+ 
	    "different story elements.\nYou might have to name a character,"+
	    "or choose a verb, noun, or adjective.\n"+
        "When prompted,please insert an appropriate word.\n\n"+
            "                       HAVE FUN!!!\n\n"
	    );

        entries = new ArrayList<>();

    }

    public void play(){
        try{ template = pickStory(); } catch (FileNotFoundException e) { e.getMessage(); }
        collectEntries();
        template.populate(entries);
        System.out.println(template);

        if(playAgain()){ play(); }
        else{ System.out.println("Thanks for Playing!!"); }
    }
        
    //should handle the exception once tested
    private Adlib_Template pickStory() throws FileNotFoundException {
        int selection;

        System.out.printf("select your adlib template by entering the corresponding number:\n\n");
        System.out.println("1: testing");
        System.out.println("2: Turkeys");

        Scanner in = new Scanner(System.in);
        selection = in.nextInt();


        switch(selection){
            case 1:
                return new Adlib_Template("test");
            case 2:
                return new Adlib_Template("Turkeys");
            //case 3:

        }
        in.close();
        return null;
    }


    private void collectEntries(){ //TODO: must be tested
        Scanner in = new Scanner(System.in);
        ListIterator l = template.getWordTypes().listIterator();
        
        for(int i = 0; i<template.getEntryCount(); i++){
            System.out.printf("please enter a %s: ", l.next());
            entries.add(in.nextLine());
            System.out.printf("\n");
        }

        in.close();
    }

    private boolean playAgain(){
        System.out.println("would you like to play again?");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine()



        switch (choice) {
            case "yes":
            case "y":
                in.close();
                return true;
            case "no":
            case "n":
                in.close();
                return false;
            default:
                System.out.println(choice + " is not a valid answer. Please answer yes or no.");
                in.close();
                return playAgain();
        }
    }
}
