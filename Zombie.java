public class Zombie extends Musuh implements BisaLoot {

    public Zombie() {
    super("Zombie Cimol", 50);
    }

@Override
public void serangPemain() {
    System.out.println(this.namaMusuh + " merangkak dan mengamuk! -15 HP");
} 

@Override
public void suaraKhas() {
    System.out.println(this.namaMusuh + ": Ughhh... brainss...");
} 

@Override
public void jatuhkanItem() {
    System.out.println("Loot Drop: " + this.namaMusuh + " menjatuhkan tangan zombie!");
    }
} 