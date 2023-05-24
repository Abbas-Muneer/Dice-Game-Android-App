package com.example.mobilecoursework20200593
import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.color.utilities.Score.score
import com.google.android.material.snackbar.Snackbar
import java.util.Collections.list

class NewGame: AppCompatActivity(){
    lateinit var HdiceOne: ImageView //creating variables to store human dices
    lateinit var HdiceTwo: ImageView
    lateinit var HdiceThree: ImageView
    lateinit var HdiceFour: ImageView
    lateinit var HdiceFive: ImageView

    lateinit var CdiceOne: ImageView //creating variables to store computer dices
    lateinit var CdiceTwo: ImageView
    lateinit var CdiceThree: ImageView
    lateinit var CdiceFour: ImageView
    lateinit var CdiceFive: ImageView

    var h_win: Int = 0 //creating variables to store the win count for human and computer players
    var c_win: Int = 0

    lateinit var hScore: TextView //creating a variable of text view to store the total score in the top right corner
    lateinit var cScore: TextView

    var one: Int = 0
    var two: Int = 0
    var three: Int = 0
    var four: Int = 0
    var five:  Int = 0

    lateinit var throwButton: Button //creating throw and score button
    lateinit var scorebutton: Button


    var h_random_numberOne:Int = 0 //creating variables to store five random numbers to shuffle between numbers from 1-6 for human dices
    var h_random_numberTwo:Int = 0
    var h_random_numberThree:Int = 0
    var h_random_numberFour:Int = 0
    var h_random_numberFive:Int = 0

    var c_random_numberOne:Int = 0 //creating variables to store five random numbers to shuffle between numbers from 1-6 for computer dices
    var c_random_numberTwo:Int = 0
    var c_random_numberThree:Int = 0
    var c_random_numberFour:Int = 0
    var c_random_numberFive:Int = 0

    var HTAG:String = "Human Score"
    var HTotal: String = "Total"

    var CTAG:String = "Computer Score"
    var CTotal: String = "Total"

    var humanTotal: Int = 0;  //creating variables to store the total for that current round for human and computer
    var computerTotal: Int = 0;

    var humanScore: Int = 0; //creating variables to store the score of the human and computer total
    var computerScore: Int = 0;


    val rerolllist = ArrayList<Int>()//Creating an empty arraylist to store numbers that will be later used for reroll for human dices
    lateinit var cdicelist: ArrayList<ImageView>

    var Rerollcount: Int = 0 //creating a variable to check how many optional rerolls have been taken place per round
    var list = arrayListOf<Int>() /// this array list is created to store random numbers that will used to check for the computer reroll



    //creating a method to randomly roll dice when user clicks throw button
    private fun humanDiceRandom() {
        h_random_numberOne = (1..6).random() // random numbers from 1 - 6 is being created and stored in the variable
        h_random_numberTwo = (1..6).random()
        h_random_numberThree = (1..6).random()
        h_random_numberFour = (1..6).random()
        h_random_numberFive = (1..6).random()

        humanTotal = h_random_numberOne + h_random_numberTwo + h_random_numberThree + h_random_numberFour + h_random_numberFive // total for that round is being updated

        Log.e(HTAG, h_random_numberOne.toString())
        Log.e(HTAG, h_random_numberTwo.toString())
        Log.e(HTAG, h_random_numberThree.toString())
        Log.e(HTAG, h_random_numberFour.toString())
        Log.e(HTAG, h_random_numberFive.toString())

        changeHumanDice(h_random_numberOne,h_random_numberTwo,h_random_numberThree,h_random_numberFour,h_random_numberFive)//those random values are passed in as the paramater to change bthe image of the dices

        reroll()//reroll function for the human dices is being called

    }

    private fun reroll() { // function for reroll is being created here
        HdiceOne.setOnClickListener {//onclicklistener for all the five dices is being initialized here
            rerolllist.add(1)//when that dice is clicked a specific integer value will be stored in the array list called "rerolllist"
        }
        HdiceTwo.setOnClickListener {
            rerolllist.add(2)
        }
        HdiceThree.setOnClickListener {
            rerolllist.add(3)
        }
        HdiceFour.setOnClickListener {
            rerolllist.add(4)
        }
        HdiceFive.setOnClickListener {
            rerolllist.add(5)
        }


        throwButton.setOnClickListener {//after the dices have been chosen to rerol the throw button is clicked
            if(Rerollcount == 0){//checks whether the optional reroll has passed more than 3 counts
                humanDiceRandom() //if it's 0, then normal throw portion will take place
                computerDiceRandom()
                Rerollcount += 1 //rerollcoubnt is being updated here
            }
            else if (Rerollcount >= 1 && Rerollcount <= 2 ) { //if the reroll count is 1 or 2 this part is being executed
                var h_reroll_one = (1..6).random() //new random values are being created and stored in new variables
                var h_reroll_two = (1..6).random()
                var h_reroll_three = (1..6).random()
                var h_reroll_four = (1..6).random()
                var h_reroll_five = (1..6).random()

                val length = rerolllist.size // length of the reroll lisdt is being stored in the variable called length
                Log.e("Length of the array list " + length, "Shit Shit Shit")
                val sum = rerolllist.sum() //the total of the numbers in the reroll list is being calculated and stored in a variable called sum so it can be checked and code can be executed
                Log.e("Total of the array list " + sum, "Shit Shit Shit")

                for (i in rerolllist) {//it iterates through the reroll arraylist
                    if (length == 1) { //checks whether the length of the array list is 1 and if it is one this part is being excecuted
                        if (i.equals(1)) { //if the length of the list is 1 and the value is equal to the value added when the dice clicked then,
                            one = h_random_numberOne //except that value, the rest of the values are updated with new randomly generated values
                            two = h_reroll_two
                            three = h_reroll_three
                            four = h_reroll_four
                            five = h_reroll_five
                            break
                        } else if (i.equals(2)) {
                            one = h_reroll_one
                            two = h_random_numberTwo
                            three = h_reroll_three
                            four = h_reroll_four
                            five = h_reroll_five
                            break
                        } else if (i.equals(3)) {
                            one = h_reroll_one
                            two = h_reroll_two
                            three = h_random_numberThree
                            four = h_reroll_four
                            five = h_reroll_five
                            break
                        } else if (i.equals(4)) {
                            one = h_reroll_one
                            two = h_reroll_two
                            three = h_reroll_three
                            four = h_random_numberFour
                            five = h_reroll_five
                            break
                        } else if (i.equals(5)) {
                            one = h_reroll_one
                            two = h_reroll_two
                            three = h_reroll_three
                            four = h_reroll_four
                            five = h_random_numberFive
                            break
                        } else {
                            Log.e("Dai Punda mavane", "One does not exist ra ambi")
                            break
                        }
                    } else if (length == 2) { //checks whther the lenght of the reroll list arraylist is equal to 2
                        if (!(!rerolllist[0].equals(1))) { //checks whther the first index of the list is equal to a specific indfex that gets stored based on the dice click
                            if (sum == 3) { //if the sum of the total numbers in the list is equal to a certain value
                                one = h_random_numberOne //dices that were selected will be same as before and the rest will be shuffled with new values
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 4) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 5) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 6) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else {
                                Log.e("Dai Punda mavane", "One and Two does not exist ra ambi")
                                break
                            }
                            rerolllist.clear()
                        } else if (!(!rerolllist[0].equals(2))) {
                            if (sum == 3) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 5) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 6) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 7) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else {
                                Log.e("Dai Punda mavane", "One and Two does not exist ra ambi")
                                break
                            }
                        } else if (!(!rerolllist[0].equals(3))) {
                            if (sum == 4) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 5) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 7) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 8) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else {
                                Log.e("Dai Punda mavane", "One and Two does not exist ra ambi")
                                break
                            }
                        } else if (!(!rerolllist[0].equals(4))) {
                            if (sum == 9) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (sum == 5) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 6) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 7) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else {
                                Log.e("Dai Punda mavane", "One and Two does not exist ra ambi")
                                break
                            }
                        }

                    } else if (length == 3) {//checks whether the length of the reroll list is 3
                        if (!(!rerolllist[0].equals(1))) { //checks for the first index of the reroll list
                            if (sum == 6) { //checks for the total sum of the reroll list and based on that dices will be shuffled
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 7) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five

                                break
                            } else if (sum == 8) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (!(!rerolllist[1].equals(3))) {
                                if (sum == 8) {
                                    one = h_random_numberOne
                                    two = h_reroll_two
                                    three = h_random_numberThree
                                    four = h_random_numberFour
                                    five = h_reroll_five
                                    break
                                } else if (sum == 6) {
                                    one = h_random_numberOne
                                    two = h_random_numberTwo
                                    three = h_random_numberThree
                                    four = h_reroll_four
                                    five = h_reroll_five
                                    break
                                } else if (sum == 9) {
                                    one = h_random_numberOne
                                    two = h_reroll_two
                                    three = h_random_numberThree
                                    four = h_random_numberFour
                                    five = h_reroll_five
                                    break
                                }
                            } else if (!(!rerolllist[1].equals(4))) {
                                if (sum == 10) {
                                    one = h_random_numberOne
                                    two = h_reroll_two
                                    three = h_reroll_three
                                    four = h_random_numberFour
                                    five = h_random_numberFive
                                    break
                                } else if (sum == 7) {
                                    one = h_random_numberOne
                                    two = h_random_numberTwo
                                    three = h_reroll_three
                                    four = h_random_numberFour
                                    five = h_reroll_five
                                    break
                                } else if (sum == 8) {
                                    one = h_random_numberOne
                                    two = h_reroll_two
                                    three = h_random_numberThree
                                    four = h_random_numberFour
                                    five = h_reroll_five
                                    break
                                }
                            }
                        } else if (!(!rerolllist[0].equals(2))) {
                            if (sum == 6) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 7) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 8) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 9) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 10) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 11) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            }
                        } else if (!(!rerolllist[0].equals(3))) {
                            if (sum == 6) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_reroll_five
                                break
                            } else if (sum == 8) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 9) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 10) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 12) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (!(!rerolllist[1].equals(2))) {
                                if (sum == 9) {
                                    one = h_reroll_one
                                    two = h_random_numberTwo
                                    three = h_random_numberThree
                                    four = h_random_numberFour
                                    five = h_reroll_five
                                    break
                                }
                            }
                        } else if (!(!rerolllist[0].equals(4))) {
                            if (sum == 7) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 8) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberTwo
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 10) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (sum == 9) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_random_numberTwo
                                four = h_random_numberFour
                                five = h_reroll_five
                                break
                            } else if (sum == 11) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (sum == 12) {

                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            }
                        } else if (!(!rerolllist[0].equals(5))) {
                            if (sum == 8) {
                                one = h_random_numberOne
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 9) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_reroll_four
                                five = h_random_numberFive
                                break
                            } else if (sum == 10) {
                                one = h_random_numberOne
                                two = h_reroll_two
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (sum == 11) {
                                one = h_reroll_one
                                two = h_random_numberTwo
                                three = h_reroll_three
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (sum == 12) {
                                one = h_reroll_one
                                two = h_reroll_two
                                three = h_random_numberThree
                                four = h_random_numberFour
                                five = h_random_numberFive
                                break
                            } else if (!(!rerolllist[1].equals(2))) {
                                if (sum == 10) {
                                    one = h_reroll_one
                                    two = h_random_numberTwo
                                    three = h_random_numberThree
                                    four = h_reroll_four
                                    five = h_random_numberFive
                                    break
                                }
                            }
                        }
                    } else if (length == 4) { //checks whther the length of the reroll list is equal to 4
                        if (sum == 10) { //based on the sum of the total values in the list the dices will be shuffled
                            one = h_random_numberOne
                            two = h_random_numberTwo
                            three = h_random_numberThree
                            four = h_random_numberFour
                            five = h_reroll_five
                            break
                        } else if (sum == 11) {
                            one = h_random_numberOne
                            two = h_random_numberTwo
                            three = h_random_numberThree
                            four = h_reroll_four
                            five = h_random_numberFive
                            break
                        } else if (sum == 12) {
                            one = h_random_numberOne
                            two = h_random_numberTwo
                            three = h_reroll_three
                            four = h_random_numberFour
                            five = h_random_numberFive
                            break
                        } else if (sum == 13) {
                            one = h_random_numberOne
                            two = h_reroll_two
                            three = h_random_numberThree
                            four = h_random_numberFour
                            five = h_random_numberFive
                            break
                        } else if (sum == 14) {
                            one = h_reroll_one
                            two = h_random_numberTwo
                            three = h_random_numberThree
                            four = h_random_numberFour
                            five = h_random_numberFive

                            break
                        } else {
                            Log.e("I'm sorry", "Four dices part haven't been triggered properly")
                        }
                    }
                    else if (length == 5) { //checks whther the lenght of the values in the list are equal to 5
                        one = h_reroll_one //all the dices are shuffled..
                        two = h_reroll_two
                        three = h_reroll_three
                        four = h_reroll_four
                        five = h_reroll_five
                        break
                    } else {
                        Log.e("Dai Punda mavane", "Array list is not getting connected properly")
                    }
                }
                humanTotal = one + two + three + four + five // the human total gets updated for that particular round
                changeHumanDice(one, two, three, four, five) //the dices gets changed based on the new values
                Rerollcount += 1 //the reroll count gets updated
                rerolllist.clear() //the reroll list gets emptied before the next round so the sum and the length will be correctly calculated
                Log.e("Poda Punde","Varhsika is the best ")
                h_random_numberOne = one //the innitial random values are updated with the new values so next time the totoal will be correct
                h_random_numberTwo = two
                h_random_numberThree = three
                h_random_numberFour = four
                h_random_numberFive = five
            }
            else{ //if the reroll count is greatrer that 3 this code gets executed
                val toast = Toast.makeText(applicationContext, "Exceeded the maximmum number of optional reroll, Score will be updated", Toast.LENGTH_SHORT)
                toast.show()//a toast messsage is being displayed
                score(1) //if the reroll count is greater than 3 then the score will be updated automatically
                rerolllist.clear()//reroll list being cleared
                Rerollcount = 0 //reroll count is set back 0 again
                var random: Int = (0..2).random() //new random values are created and this code is to execute optional reroll for computer dices
                if(random == 1){//if the random value is equal to 1 the optional reroll will take place only once
                    val toast = Toast.makeText(applicationContext, "Reroll For Computer will take place", Toast.LENGTH_SHORT)
                    toast.show()
                    ComputerReroll()
                }
                else if(random == 2){//if the random value is equal to 2 the optional reroll will take place twice
                    val toast = Toast.makeText(applicationContext, "Reroll For Computer will take place second time", Toast.LENGTH_SHORT)
                    toast.show()
                    ComputerReroll()
                    ComputerReroll()
                }
                else if(random == 0){//if it's equal to 0 it won't take place
                    Log.e("Sorry ra ambi","Reroll won't happen for the computer")
                }
            }
        }
    }



    private fun changeHumanDice(h_random_numberOne:Int,h_random_numberTwo:Int,h_random_numberThree:Int,h_random_numberFour:Int,h_random_numberFive:Int){// this function is to change the dices based on the random values
        when(h_random_numberOne){//passed in parameteres from random values created arew being checked here
            1 -> HdiceOne.setImageResource(R.drawable.dice1) //if the first random number is equal to a specific number then the specific alloocated dice will be changed
            2 -> HdiceOne.setImageResource(R.drawable.dice2)
            3 -> HdiceOne.setImageResource(R.drawable.dice3)
            4 -> HdiceOne.setImageResource(R.drawable.dice4)
            5 -> HdiceOne.setImageResource(R.drawable.dice5)
            6 ->   HdiceOne.setImageResource(R.drawable.dice6)
        }

        when(h_random_numberTwo){
            1 -> HdiceTwo.setImageResource(R.drawable.dice1)
            2 -> HdiceTwo.setImageResource(R.drawable.dice2)
            3 -> HdiceTwo.setImageResource(R.drawable.dice3)
            4 -> HdiceTwo.setImageResource(R.drawable.dice4)
            5 -> HdiceTwo.setImageResource(R.drawable.dice5)
            6 ->   HdiceTwo.setImageResource(R.drawable.dice6)
        }

        when(h_random_numberThree){
            1 -> HdiceThree.setImageResource(R.drawable.dice1)
            2 -> HdiceThree.setImageResource(R.drawable.dice2)
            3 -> HdiceThree.setImageResource(R.drawable.dice3)
            4 -> HdiceThree.setImageResource(R.drawable.dice4)
            5 -> HdiceThree.setImageResource(R.drawable.dice5)
            6 ->   HdiceThree.setImageResource(R.drawable.dice6)
        }

        when(h_random_numberFour){
            1 -> HdiceFour.setImageResource(R.drawable.dice1)
            2 -> HdiceFour.setImageResource(R.drawable.dice2)
            3 -> HdiceFour.setImageResource(R.drawable.dice3)
            4 -> HdiceFour.setImageResource(R.drawable.dice4)
            5 -> HdiceFour.setImageResource(R.drawable.dice5)
            6 ->   HdiceFour.setImageResource(R.drawable.dice6)
        }

        when(h_random_numberFive){
            1 -> HdiceFive.setImageResource(R.drawable.dice1)
            2 -> HdiceFive.setImageResource(R.drawable.dice2)
            3 -> HdiceFive.setImageResource(R.drawable.dice3)
            4 -> HdiceFive.setImageResource(R.drawable.dice4)
            5 -> HdiceFive.setImageResource(R.drawable.dice5)
            6 ->   HdiceFive.setImageResource(R.drawable.dice6)
        }

        Log.e("Total for first round before re roll",humanTotal.toString())
        Log.e("Score",humanScore.toString())

    }


    private fun computerDiceRandom() { //this function is to create random values for computer dices.
        c_random_numberOne = (1..6).random() //new random values from 1 - 6 are being generated and stored in a variable
        c_random_numberTwo = (1..6).random()
        c_random_numberThree = (1..6).random()
        c_random_numberFour = (1..6).random()
        c_random_numberFive = (1..6).random()

        computerTotal = c_random_numberOne + c_random_numberTwo + c_random_numberThree + c_random_numberFour + c_random_numberFive //total for that round gets updated

        Log.e(CTAG, c_random_numberOne.toString())
        Log.e(CTAG, c_random_numberTwo.toString())
        Log.e(CTAG, c_random_numberThree.toString())
        Log.e(CTAG, c_random_numberFour.toString())
        Log.e(CTAG, c_random_numberFive.toString())

        changeComputerDice(c_random_numberOne,c_random_numberTwo,c_random_numberThree,c_random_numberFour,c_random_numberFive)//dices gets changed according to that random value
        Log.e("Total for that round before ReRoll.",computerTotal.toString())
        Log.e("Score Computer",computerScore.toString())
    }

    private fun changeComputerDice(crandom_numberOne:Int,crandom_numberTwo:Int,crandom_numberThree:Int,crandom_numberFour:Int,crandom_numberFive:Int){// this function is to change the dices based on the random values
        CdiceOne = findViewById(R.id.cDiceOne) //computer dices images are called and stored in a variable
        CdiceTwo = findViewById(R.id.cDiceTwo)
        CdiceThree = findViewById(R.id.cDiceThree)
        CdiceFour = findViewById(R.id.cDiceFour)
        CdiceFive = findViewById(R.id.cDiceFive)


        when(crandom_numberOne){//based on the random value that was created in the computer random function the image changes
            1 -> CdiceOne.setImageResource(R.drawable.dice1)
            2 -> CdiceOne.setImageResource(R.drawable.dice2)
            3 -> CdiceOne.setImageResource(R.drawable.dice3)
            4 -> CdiceOne.setImageResource(R.drawable.dice4)
            5 -> CdiceOne.setImageResource(R.drawable.dice5)
            6 -> CdiceOne.setImageResource(R.drawable.dice6)
        }

        when(crandom_numberTwo){//based on the random value that was created in the computer random function the image changes
            1 -> CdiceTwo.setImageResource(R.drawable.dice1)
            2 -> CdiceTwo.setImageResource(R.drawable.dice2)
            3 -> CdiceTwo.setImageResource(R.drawable.dice3)
            4 -> CdiceTwo.setImageResource(R.drawable.dice4)
            5 -> CdiceTwo.setImageResource(R.drawable.dice5)
            6 -> CdiceTwo.setImageResource(R.drawable.dice6)
        }

        when(crandom_numberThree){//based on the random value that was created in the computer random function the image changes
            1 -> CdiceThree.setImageResource(R.drawable.dice1)
            2 -> CdiceThree.setImageResource(R.drawable.dice2)
            3 -> CdiceThree.setImageResource(R.drawable.dice3)
            4 -> CdiceThree.setImageResource(R.drawable.dice4)
            5 -> CdiceThree.setImageResource(R.drawable.dice5)
            6 -> CdiceThree.setImageResource(R.drawable.dice6)
        }

        when(crandom_numberFour){//based on the random value that was created in the computer random function the image changes
            1 -> CdiceFour.setImageResource(R.drawable.dice1)
            2 -> CdiceFour.setImageResource(R.drawable.dice2)
            3 -> CdiceFour.setImageResource(R.drawable.dice3)
            4 -> CdiceFour.setImageResource(R.drawable.dice4)
            5 -> CdiceFour.setImageResource(R.drawable.dice5)
            6 -> CdiceFour.setImageResource(R.drawable.dice6)
        }

        when(crandom_numberFive){//based on the random value that was created in the computer random function the image changes
            1 -> CdiceFive.setImageResource(R.drawable.dice1)
            2 -> CdiceFive.setImageResource(R.drawable.dice2)
            3 -> CdiceFive.setImageResource(R.drawable.dice3)
            4 -> CdiceFive.setImageResource(R.drawable.dice4)
            5 -> CdiceFive.setImageResource(R.drawable.dice5)
            6 -> CdiceFive.setImageResource(R.drawable.dice6)
        }

        cdicelist = arrayListOf(CdiceOne,CdiceTwo,CdiceThree,CdiceFour,CdiceFive)



    }

    private fun ComputerReroll() {//this function is to execute the optional reroll part for computer dices
        //explanation of the reroll part
        //in this method 6 random numbers of 0 or 1 will be generated
        //those 0 or 1 values will be stored in the list
        //now the iteration of the list will happen and based on the specific if the value in the list is 1 then, a new radom value of 1 - 6 will be generated and updated
        //if the vlaue is  0 then nothing will happen
        //this is a good method because it will randomly generate and randomly check whether its there.
        var rOne: Int = (1..6).random() //new random values from 1 to 6 are being generated and stored in vairable
        var rTwo: Int = (1..6).random()
        var rThree: Int = (1..6).random()
        var rFour: Int = (1..6).random()
        var rFive: Int = (1..6).random()

        var checkOne: Int = (0..1).random() //new random values from 0 -1 in created stored in a variable to checking purposes that will be triggered if it is 1
        var checkTwo: Int = (0..1).random()
        var checkThree: Int = (0..1).random()
        var checkFour: Int = (0..1).random()
        var checkFive: Int = (0..1).random()

        var c_rerollOne = c_random_numberOne //in a new variable the previous randomly generated values are stored in it
        var c_rerollTwo = c_random_numberTwo
        var c_rerollThree = c_random_numberThree
        var c_rerollFour = c_random_numberFour
        var c_rerollFive = c_random_numberFive

        list.add(checkOne) //adding the trigeering random numbers to a list called "list"
        list.add(checkTwo)
        list.add(checkThree)
        list.add(checkFour)
        list.add(checkFive)

        for(i in list.indices) { //iterating through the list
            if (list[0].equals(1)) { //if the index is equal to 1 then it has to get triggered and the new random value has to be stored i the variable
                c_rerollOne = rOne
            } else if (list[0].equals(0)) { //if its 0 it won't get triggeredf and the sameold random value will reamin same in that variable
                c_rerollOne = c_random_numberOne
            }
        }
        for(i in list.indices) {
            if(list[1].equals(1)){
                c_rerollTwo = rTwo
            }
            else if(list[1].equals(0)){
                c_rerollTwo = c_random_numberTwo
            }
        }
        for(i in list.indices) {
            if(list[2].equals(1)){
                c_rerollThree = rThree
            }
            else if(list[2].equals(0)){
                c_rerollThree = c_random_numberThree
            }
        }
        for(i in list.indices) {
            if(list[3].equals(1)){
                c_rerollFour = rFour
            }
            else if(list[3].equals(0)){
                c_rerollFour = c_random_numberFour
            }
        }
        for(i in list.indices) {
            if(list[4].equals(1)){
                c_rerollFive = rFive
            }
            else if(list[4].equals(0)){
                c_rerollFive = c_random_numberFive
            }
        }
        computerTotal = c_rerollOne + c_rerollTwo + c_rerollThree + c_rerollFour + c_rerollFive // new total is being updated
        changeComputerDice(c_rerollOne , c_rerollTwo , c_rerollThree , c_rerollFour, c_rerollFive) //the images of the computer dices will be changed here
        list.clear() //the list gets emptied.
        Log.e("Total for that round after ReRoll.",computerTotal.toString())

    }

    @SuppressLint("MissingInflatedId")
    private fun score(randomNumber: Int){//this is the function to calculate the score of the human and computer
        var randomNumber: Int = (0..2).random()// a random value of 0 - 2 is created
        if(randomNumber == 1){// if the random value is 1, then the optional reroll will take place only once
            val toast = Toast.makeText(applicationContext, "Computer will perform Reroll Once", Toast.LENGTH_LONG)
            toast.show()
            ComputerReroll()
        }
        else if(randomNumber == 2){// if the random value is 2, then the optional reroll will take place onlytwice
            val toast = Toast.makeText(applicationContext, "Computer will perform Reroll Twice", Toast.LENGTH_LONG)
            toast.show()
            ComputerReroll()
            ComputerReroll()
        }
        else if(randomNumber == 0){// if the random value is 0, then the optional reroll will not take place
            Log.e("Sorry","Reroll won't happen for the computer")
        }

        computerScore += computerTotal //the total score of the game is updated
        humanScore += humanTotal

        val targetScore:Int =  intent.getIntExtra("targetScore",101) //the score point set by the user is retrieved here

        hScore = findViewById(R.id.human_score) //the text view of the score placement is stored in a variable
        cScore = findViewById(R.id.computer_score)

        if(computerScore >= targetScore && computerScore >= humanScore){//if the computer score is greater than the set score and is greater than the human score
            c_win = c_win + 1 //the win count of the computer is updated
            losePage()//the function for the lose page pop up window is called


            val dialogBinding = layoutInflater.inflate(R.layout.activity_losepage,null)
            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
            val yesbtn = dialogBinding.findViewById<Button>(R.id.lose_ok)
            yesbtn.setOnClickListener{
                myDialog.dismiss()
                var cWin = findViewById<TextView>(R.id.computerWin)
                cWin.text = c_win.toString()
                val dialogBinding = layoutInflater.inflate(R.layout.activity_targetscore,null)
                val myDialog = Dialog(this)
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
                val yesbtn = dialogBinding.findViewById<Button>(R.id.play)
                yesbtn.setOnClickListener{
                    myDialog.dismiss()
                    reset()//reset function is called
                }
            }

            throwButton.setClickable(false)//the buttons are made non clickable
            scorebutton.setClickable(false)
        }
        else if(humanScore >= targetScore && humanScore >= computerScore){//if the human score is greater than the set score and is greater than the computer score
            h_win = h_win + 1//the win count of the human is updated
            winPage()//the function for the win page pop up window is called
            val dialogBinding = layoutInflater.inflate(R.layout.activity_winpage,null) // this calls the resouce XMl file for the win page
            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show() //it gets displayed
            val yesbtn = dialogBinding.findViewById<Button>(R.id.win_ok) //a button is created to check for restart
            yesbtn.setOnClickListener{ //if that button is clicked
                myDialog.dismiss()// the win page pop up window is dismissed
                var hWin = findViewById<TextView>(R.id.humanWin) //the human win is stored and updated
                hWin.text = h_win.toString()
                val dialogBinding = layoutInflater.inflate(R.layout.activity_targetscore,null)//the target point by user is called here and displayed in a pop window
                val myDialog = Dialog(this)
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
                val yesbtn = dialogBinding.findViewById<Button>(R.id.play)
                yesbtn.setOnClickListener{
                    myDialog.dismiss()
                    reset()//reset function is called
                }
            }


            throwButton.setClickable(false)//the buttons are made non clickable
            scorebutton.setClickable(false)
        }

        hScore.text = humanScore.toString()//the score is updated in XML file
        cScore.text = computerScore.toString()
//        humanScore = 0 //the score value is set to 0 so if the user presses the score button without throwing it again , it must not falsely add the same previous score
//        computerScore = 0

    }


    private fun winPage(){// this the pop window for win page

    }
    private fun losePage(){

    }

    private fun reset(){// this is to reset the values to the initial values
        computerTotal = 0
        humanTotal = 0
        computerScore = 0
        humanScore = 0
        hScore.text = 0.toString()
        cScore.text = 0.toString()
        changeComputerDice(1,1,1,1,1)
        changeHumanDice(1,1,1,1,1)
        throwButton.setClickable(true)
        scorebutton.setClickable(true)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newgame)

        throwButton = findViewById<Button>(R.id.throwButton)

        HdiceOne = findViewById<ImageView>(R.id.diceOne)
        HdiceTwo = findViewById<ImageView>(R.id.diceTwo)
        HdiceThree = findViewById<ImageView>(R.id.diceThree)
        HdiceFour = findViewById<ImageView>(R.id.diceFour)
        HdiceFive = findViewById<ImageView>(R.id.diceFive)

        throwButton.setOnClickListener {
            humanDiceRandom()
            computerDiceRandom()
            Rerollcount += 1
        }

        scorebutton = findViewById(R.id.scoreButton)

        scorebutton.setOnClickListener{
            score(1)
            Rerollcount = 0
        }

//        if(savedInstanceState != null){
//            computerScore = savedInstanceState.getInt("count")
//            humanScore = savedInstanceState.getInt("HCount")
//            hScore.setText(humanScore.toString())
//            cScore.setText(computerScore.toString())
//
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putInt("Count",computerScore)
//        outState.putInt("HCount",humanScore)
//    }
}


