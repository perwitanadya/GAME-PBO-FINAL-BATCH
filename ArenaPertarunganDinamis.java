import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;
public class ArenaPertarunganDinamis {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<Musuh> gelombangMonster = new ArrayList<>();
        gelombangMonster.add(new Slime());
        gelombangMonster.add(new Naga());
        gelombangMonster.add(new Slime());
        gelombangMonster.add(new Zombie());

        System.out.println("=====================================");
        System.out.println(" ARENA RPG: GELOMBANG MONSTER ");
        System.out.println("=====================================\n");
        System.out.println("AWAS OI! Sekelompok monster menghadang Anda!");

        boolean isBermain = true;

        while (isBermain && !gelombangMonster.isEmpty()) {

            System.out.println("\n--- STATUS MONSTER ---");

            for (int i = 0; i < gelombangMonster.size(); i++) {
                Musuh m = gelombangMonster.get(i);

                System.out.println((i + 1) + ". " + m.namaMusuh + " (HP: " + m.healthPoint + ")");
            }
            System.out.println("-------------------------------------------");
            System.out.println("8. [SAVE GAME] Simpan progres pertarungan");
            System.out.println("9. [LOAD GAME] Muat progres sebelumnya");
            System.out.println("0. Kabur dari pertarungan");
            System.out.print("\nPilih target monster (1-"+ gelombangMonster.size() + ") atau aksi lainnya: ");

            try {

                int pilihanTarget = input.nextInt();

                if (pilihanTarget == 0) {
                    System.out.println("Anda lari terbirit-birit dari arena...");
                    isBermain = false;
                    continue;
                }

            else if (pilihanTarget == 8) {

             try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("savegame_perang"))) {

              oos.writeObject(gelombangMonster);

             System.out.println(">>> BERHASIL: GAME TELAH DISIMPAN! <<<");

            } catch (IOException e) {

             System.out.println(
                ">>> GAGAL: TERJADI KESALAHAN SAAT MENYIMPAN GAME. <<< "
                + e.getMessage());
            }
            continue;
            }

        else if (pilihanTarget == 9) {
            
            try (ObjectInputStream ois =
            new ObjectInputStream(
            new FileInputStream("savegame_perang"))) {
            gelombangMonster = (ArrayList<Musuh>) ois.readObject();

        System.out.println(">>> BERHASIL: Game berhasil dimuat! <<<");
        } catch (FileNotFoundException e) {

        System.out.println( ">>> GAGAL: File save game belum ada. Silakan Save Game terlebih dahulu! <<<");
        } catch (IOException | ClassNotFoundException e) {

        System.out.println(">>> GAGAL: Terjadi kesalahan saat membaca file save. "+ e.getMessage());
        }
        continue;
        }

                if (pilihanTarget < 1 ||
                    pilihanTarget > gelombangMonster.size()) {

                    System.out.println("Pilihannya nggak valid!");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;
                Musuh target = gelombangMonster.get(indeksMonster);

                System.out.print(
                    "Masukkin kekuatan buat nyerang (10 - 100): "
                );

                int power = input.nextInt();

                if (power < 10 || power > 100) {
                    throw new SeranganTidakValidException( "Kekuatan serangan harus di antara 10 sampai 100!" );
                }

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");

                target.terimaDamage(power);

                if (target.healthPoint <= 0) {

                    System.out.println(target.namaMusuh + " hancur menjadi debu! HUACUKK");

                    if (target instanceof BisaLoot) {

                        BisaLoot loot = (BisaLoot) target;
                        loot.jatuhkanItem();
                    }

                    gelombangMonster.remove(indeksMonster);
                }

            } catch (SeranganTidakValidException e) {

                System.out.println("ERROR: " + e.getMessage());
                continue;

            } catch (Exception e) {

                System.out.println( "ADUH! Terjadi kesalahan input, silakan coba lagi." );
                input.nextLine();
                continue;
            }

            if (gelombangMonster.isEmpty()) {

                System.out.println( "\nSELAMAT! Semua monster telah dibersihkan dari arena!"
                );

                break;
            }

            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");

            for (int i = 0; i < gelombangMonster.size(); i++) {

                Musuh monsterAktif = gelombangMonster.get(i);

                monsterAktif.suaraKhas();

                if (monsterAktif instanceof BisaTerbang) {

                    System.out.println( "[PERINGATAN! SERANGAN UDARA TERDETEKSI]" );
                    BisaTerbang monsterTerbang =
                        (BisaTerbang) monsterAktif;

                    monsterTerbang.lepasLandas();
                    monsterTerbang.seranganUdara();

                } else {

                    monsterAktif.serangPemain();
                }

                System.out.println("------------------------------");
            }
        }

        input.close();

        System.out.println("\nPermainan Berakhir!");
    }
}