import java.util.Scanner;
import java.util.Random;

public class PokemonBattle {
    public static void main(String[] args) {
        //intro conversation, for fun
        Scanner name = new Scanner(System.in);
        System.out.println("???: Welcome to the world of Pokemon! What is your name, little Pokemon?");
        String pokemon = name.nextLine();
        System.out.println("???: Nice name you got there! Do you see that tall grass over there?");
        System.out.println("???: ...Oh, you're blind. Well, the tall grass is where you find fellow pokemon to battle!");
        System.out.println("???: I'll just throw you into the tall grass then, good luck!");
        System.out.println();
        System.out.println(pokemon + ": wait what? hold up-!");
        System.out.println();
        System.out.println("*??? shoves you. You are now surrounded by large, slightly damp blades of grass. Run away?*");
        System.out.print(pokemon + ": ");
        Scanner speak = new Scanner(System.in);
        speak.nextLine();
        System.out.println("g\n  L\n    i\n      T\n        c\n          H");
        System.out.println("M i s s i n g n o   h a s   a p p e a r e d\n");
        System.out.println(pokemon + ": But I don't have any moves?!\n");
        System.out.println("*Tornado: 20 Damage*\n*Scratch:10 (f0|^Ty?) Damage*\n*Heal: +20 HP*\n*Wind Shield: shield for 2 turns (80% Accuracy)*\n");
        System.out.println(pokemon + ": Oh cool, thanks robot voice-\n");
        System.out.println("s t a t i c   c o n s u m e s   y o u r   v o i c e");
        
        //creating player & enemy objects with HP that can be manipulated in battle
        Pokemon player = new Pokemon(100);
        Pokemon enemy = new Pokemon(100);
        
        int shield = 0;
        /*while(player.getHP() != 0 && enemy.getHP() != 0) causes a fun negative health glitch
        the glitch is still playable since both the player and MissingNo can heal
        maybe I should keep it but display a different, more ominous win message?
         "MissingNo fades away. YOU WIN a realm of static beyond life and death. Is this what it means to be a glitch?"
        ooooh now I'm really tempted, should I do it? */
        
        while(player.getHP() > 0 && enemy.getHP() > 0) {
            
            //display player & enemy HP
            System.out.println();
            System.out.println(pokemon + " HP = " + player.getHP());
            System.out.println("MissingNo HP = " + enemy.getHP());
            
            //let player choose a move using the moveset method
            System.out.println(pokemon + ": *Tornado, Scratch, Heal, or Wind Shield*");
            Scanner myMove = new Scanner(System.in);
            String move = (myMove.nextLine()).toLowerCase();
            System.out.println();
            moveset(move, player, enemy);
            
            //makes it so that the shield lasts for 2 moves, but has a 20% chance of not working
            Random rand = new Random();
            int chance = rand.nextInt(10);
            if(move.equals("wind shield") && chance > 2) {
                shield += 2;
            }
            
            if (shield > 0 && chance < 2) {
                shield -= 1;
                if (chance < 2) {
                    System.out.println("MissingNo pokes your shield and breaks through it!");
                }
                else {
                System.out.println("*MissingNo stares pokes your shield, but it doesn't break!*");
                }
            }
            
            //makes MissingNo do a move when there is no wind shield
            else {
                glitchMoves(player, enemy);
            }
        }
        
        //lose message
        if (player.getHP() == 0) {
            System.out.println(pokemon + " HP = 0");
            System.out.println("MissingNo HP = " + enemy.getHP());
            System.out.println("*YOU LOSE yourself in the static.*");
        }
        
        //win message
        if(enemy.getHP() == 0) {
            System.out.println();
            System.out.println(pokemon + " HP = " + player.getHP());
            System.out.println("MissingNo HP: 000?????*!&@`^~/|#.^-$_'%#?");
            System.out.println("MissingNo: \\/\\/ ||--|| ((  )) -- \n*MissingNo fades away, and your soul is cleared of static. YOU WIN a moment of peace.");
        }
    }
    
/** 
* @param Pokemon object representing the player
* @param Pokemon object representing the enemy (MissingNo)
* chooses what move MissingNo uses each turn
*/
    public static void glitchMoves(Pokemon player, Pokemon enemy) {
        Random rand = new Random();
        int num = rand.nextInt(11);
        
        //gives a 10% chance of MissingNo healing itself
        if (num == 0) {
            enemy.damage(-30);
            System.out.println("MissingNo: ||-|| 3 //-\\ ||_   //\\//\\3");
            System.out.println("*MissingNo heals?*");
        }
        
        //the move that MissingNo uses 90% of the time, it randomly does 10, 20, 30, or 40 damage
        else {
            player.damage((rand.nextInt(4) + 1) * 10);
            System.out.println("*MissingNo stares into your soul. Static tries to consume you.*");
        }
    }
    
/** 
* @param String the move the player chooses
* @param Pokemon object representing the player
* @param Pokemon object representing the enemy (MissingNo)
* chooses what move MissingNo uses each turn
*/    
    public static void moveset(String move, Pokemon player, Pokemon enemy) {
         Random rand = new Random();
         //move Tornado does 20 damage 
         if (move.equals("tornado")) {
            enemy.damage(20); 
            System.out.println("*A tornado rips through MissingNo!*");
            
         }
         
         //move Scratch usually does 10 damage but has a 30% chance of doing 40 damage
         else if(move.equals("scratch")) {
             enemy.damage(10);
             System.out.println("*You reach out and scratch MissingNo with all your might!");
             if((rand.nextInt(10)+1) > 7) {
                 enemy.damage(30);
                 System.out.println("@  p 1 >< e ||_  f //-\\ ||_ ||_ z ?");
             }
         }
             
        //move Heal gives you 20 health     
         else if(move.equals("heal")) {
             player.damage(-20); //heals instead of damaging
             System.out.println("*A warm light soothes your pain*");
         }
         
         //move Wind Shield protects the player from damage for 2 turns
         else if(move.equals("wind shield")) {
             System.out.println("*A fierce wind hardens into a bubble around you.*");
         }
         
         //if player inputs something other than usable moves (or mispells)
         else {
             System.out.println("*Move failed! Tip: Don't try moves you can't use.*");
         }
    }
}




//2nd draft idea:
//let player pick movesets from one of the types
//gives MissingNo a random type
//type advantages & disadvantages apply
//(pick normal type to play w/o caring abt types)



//"???: Are you fire, water, grass, electric, or normal type? You look too boring so I can't tell"
//Scanner in = new Scanner(System.in);
//String type = in.nextLine();
//"???: Well, at least you look strong enough to fight!"

//Pokemon player = new Pokemon(100, type);
//Random rand = new Random();
//int num = rand.nextInt(100);
//String type2;
//if (num < 25) { type2 = "fire";}
//else if(num < 50) { type2 = "water"}
//else if (num < 75) { type2 = "grass"}
//else { type2 = "electric"}
//Pokemon enemy = new Pokemon(100, type2);

//include Pokemon player & Pokemon enemy as parameters for the damage() method
//public void damage(int damage, Pokemon player, Pokemon enemy)
//put in if statements dictating type advantages & disadvantages in damage function 
//this would probably fill in the need for advanced conditionals too