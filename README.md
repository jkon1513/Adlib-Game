# Madlib-Game
A fun Madlib game that allows users to create funny entertaining stories

The Way this game will work is it will prompt users for a type of word (noun, adjective, verb, name, object.....etc). 
It will then populate a premade story template with the choices they make.

version 1 goals:
- succesfully parse story templates to get word insertion types by scanning for entry tags
- succesfully replace entry tags with user entry and print the story
- support any number of entry tag types

template format: 
templates will be text files of stories with entry tags where insertions are to go. the program will scan the template and generate a list of word types to prompt the user for. the template must follow the following format convention:

<name> enetered a <noun>. this would prompt user for a name, then a noun. 
any punctuation is fine and ignored by the parser. 
tenses may be included outside of the tag as well. ex: Jason <verb>ed his job. 
if input in the case was "love" it would result in Jason loved his job. 
  
Version 2 goals: 
- create system for user story creation and submission
- implement gui elements
