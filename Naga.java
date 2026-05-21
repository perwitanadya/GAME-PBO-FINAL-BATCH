public class Naga extends Musuh implements BisaTerbang, BisaLoot {
    public Naga() {
    super("Naga Gemoy", 500);
    }

@Override
    public void serangPemain() {
        System.out.println(this.namaMusuh + " menyemburkan nafas gemoy dari udara! Player -50 HP");
    }

    @Override
    public void suaraKhas() {
        System.out.println(this.namaMusuh + ": ROAARRR... SEMBURRR!");
    }

    @Override
    public void lepasLandas() {
        System.out.println(this.namaMusuh + " terbang tinggi! Sulit diserang.");
    }

    @Override
    public void seranganUdara() {
        System.out.println(this.namaMusuh + " menyemburkan badai api gemoy! Pemain -80 HP.");
    }

    @Override
    public void jatuhkanItem() {
System.out.println("Loot Drop: " + this.namaMusuh + " menjatuhkan 1x Sisik Naga Gemoy (Legendary)!");
    }
} 