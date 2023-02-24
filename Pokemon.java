public class Pokemon {
    int hp;

/** 
* Pokemon class constructor setting a pokemon's HP
*/
    public Pokemon (int hp) {
        this.hp = hp;
    }
    
/** 
* @param amount of damage taken by the Pokemon
* Used to take away health, but can also be used to heal if a negative "damage" value is inputted
*/
    public void damage(int damage) {
        hp -= damage;
    }

/** 
* @return Pokemon HP
*/
    public int getHP() {
        return hp;
    }
}