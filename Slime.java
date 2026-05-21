public class Slime extends Musuh implements BisaLoot { 
    public Slime() {
        super("Slime Imoet", 50);
    }

@Override
public void serangPemain() {
    System.out.println(this.namaMusuh + " melompat dan menyiram cairan imoet! -15 HP ");
} 

@Override
public void suaraKhas() {
    System.out.println(this.namaMusuh + " Plung... plung... ciprat! ");
} 

@Override
public void jatuhkanItem() {
    System.out.println("Loot Drop: " + this.namaMusuh + " menjatuhkan 1x Potion Cinta!");
}
} 