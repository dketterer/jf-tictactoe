import java.util.Scanner;

/**
 * Tic-Tac-Toe: Für 2 Spieler auf der Konsole
 * Alle Variablen und Funktionen sind static (gehören zur Klasse)
 * Funktionaler Stil
 */
public class Start {
    // Konstanten die, die Spieler oder eben keinen Spieler repräsentieren
    private static final int LEER = 0;
    private static final int KREUZ = 1;
    private static final int KREIS = 2;

    // Konstanten die den Zustand des Spiels repräsentieren
    private static final int IMSPIEL = 0;
    private static final int UNENTSCHIEDEN = 1;
    private static final int KREUZ_GEWINNT = 2;
    private static final int KREIS_GEWINNT = 3;

    // Das Spielbrett
    private static final int REIHEN = 3, SPALTEN = 3; // Anzahl Reihen und Spalten
    private static int[][] board = new int[REIHEN][SPALTEN]; // 2D Array das dann LEER, KREUZ, KREIS beinhaltet

    private static int jetztZustand;  // der aktuelle Zustand (IMSPIEL, UNENTSCHIEDEN, KREUZ_GEWINNT, KREIS_GEWINNT)

    private static int aktuellerSpieler; // wer ist dran? (KREUZ oder KREIS)
    private static int aktuelleReihe, aktuelleSpalte; // welches Feld wurde zuletzt gesetzt

    private static Scanner input = new Scanner(System.in); // Eingabe aus der Konsole

    /**
     * Die Eingangs-Funktion (Hier startet das Programm)
     */
    public static void main(String[] args) {
        // Vorbereitungen für ein neues Spiel
        spielStarten();
        // Schleife solange das Spiel läuft
        do {
            spielzug(aktuellerSpieler); // aktualisiert aktuelleReihe and aktuelleSpalte
            zustandAktualisieren(aktuellerSpieler, aktuelleReihe, aktuelleSpalte); // update jetztZustand
            spielfeldZeichnen();

            // Zustand prüfen
            if (jetztZustand == KREUZ_GEWINNT) {
                System.out.println("'X' hat gewonnen. Bye.");
            } else if (jetztZustand == KREIS_GEWINNT) {
                System.out.println("'0' hat gewonnen. Bye.");
            } else if (jetztZustand == UNENTSCHIEDEN) {
                System.out.println("Unentschieden. Bye.");
            }

            // TODO Aufgabe 2a: Spieler wechseln
            //Falls aktuellerSpieler KREUZ ist, soll aktuellerSpieler KREIS sein. Sonst umgekehrt.

        } while (jetztZustand == IMSPIEL); // wiederholen falls wir noch im Spiel sind
    }

    /**
     * Vorbereitungen für ein neues Spiel
     * Spielfeld auf LEER setzen, jetztZustand auf IMPSPIEL und der Startspieler aktuellerSpieler ist KREUZ
     */
    private static void spielStarten() {
        for (int reihe = 0; reihe < REIHEN; ++reihe) {
            for (int spalte = 0; spalte < SPALTEN; ++spalte) {
                board[reihe][spalte] = LEER;
            }
        }
        jetztZustand = IMSPIEL; // ready to play
        aktuellerSpieler = KREUZ;  // cross plays first
    }

    /**
     * Spieler "spieler" macht einen Zug, mit Input Validation
     * Aktualisiert die globalen Variablen "aktuelleSpalte" und "aktuelleReihe"
     */
    private static void spielzug(int spieler) {
        boolean allesOK = false;  // Schleifenbedingung

        //TODO Aufgabe 3

        if (spieler == KREUZ) {
            System.out.print("Spieler 'X', dein Zug, Zuerst die Reihe [1-3] und Enter, dann die Spalte [1-3] und Enter:");
        } else {
            System.out.print("Spieler '0', dein Zug, Zuerst die Reihe [1-3] und Enter, dann die Spalte [1-3] und Enter:");
        }
        int reihe = input.nextInt() - 1;  // Array Index fängt bei 0 und nicht 1 an
        int spalte = input.nextInt() - 1;
        if (reihe >= 0 && reihe < REIHEN && spalte >= 0 && spalte < SPALTEN && board[reihe][spalte] == LEER) {
            aktuelleReihe = reihe;
            aktuelleSpalte = spalte;

           board[aktuelleReihe][aktuelleSpalte] = aktuellerSpieler;

            allesOK = true;  // alles OK, raus aus der Schleife
        } else {

            // TODO Aufgabe 3b Fehlermeldung: Bitte nochmal Probieren

        }

        //TODO  // solange wiederholen bis Zug gemacht wurde
    }

    /**
     * Aktualisiert den "jetztZustand" nachdem der spieler einen Zug gemacht hat mit
     * (aktuelleReihe, aktuelleSpalte).
     */
    private static void zustandAktualisieren(int spieler, int reihe, int spalte) {
        if (hatGewonnen(spieler, reihe, spalte)) {  // prüfe ob der Spieler mit diesem Zu gewinnt
            if (spieler == KREUZ) {
                jetztZustand = KREUZ_GEWINNT;
            } else {
                jetztZustand = KREIS_GEWINNT;
            }
        } else if (istUnentschieden()) {  // prüfe auf unentschieden
            jetztZustand = UNENTSCHIEDEN;
        }
        // Sonst keine Änderung (immernoch IMSPIEL).
    }

    /**
     * gibt true zurück falls keine leeren Zellen mehr da sind
     */
    // TODO: Unentscheiden falls keiner mehr gewinnen kann.
    private static boolean istUnentschieden() {
        for (int reihe = 0; reihe < REIHEN; reihe++) {
            for (int spalte = 0; spalte < SPALTEN; spalte++) {
                // TODO Aufgabe 4
                // Falls eine leere Zelle gefunden wird, kein Unentschieden, gehe zurück

            }
        }
        return true;  // keine leere Zelle gefunden, Unentschieden
    }

    /**
     * Gibt "true" zurück falls der spieler mit diesem Zug (reihe, spalte) gewinnt.
     */
    private static boolean hatGewonnen(int spieler, int reihe, int spalte) {
        return (board[reihe][0] == spieler          // 3 in der aktuellen reihe
                && board[reihe][1] == spieler
                && board[reihe][2] == spieler
                || board[0][spalte] == spieler      // 3 in der aktuellen spalte
                && board[1][spalte] == spieler
                && board[2][spalte] == spieler
                || reihe == spalte                  // 3 diagonal nach rechts unten
                && board[0][0] == spieler
                && board[1][1] == spieler
                && board[2][2] == spieler
                || reihe + spalte == 2              // 3 diagonal nach links unten
                && board[0][2] == spieler
                && board[1][1] == spieler
                && board[2][0] == spieler);
    }

    /**
     * Das Spielfeld zeichnen
     */
    private static void spielfeldZeichnen() {
        for (int reihe = 0; reihe < REIHEN; reihe++) {
            for (int spalte = 0; spalte < SPALTEN; spalte++) {
                zelleZeichnen(board[reihe][spalte]); // den Inhalt der Zelle ausgeben
                if (spalte != SPALTEN - 1) {
                    System.out.print("|"); //nur zwischen den Feldern
                }
            }
            System.out.println();
            if (reihe != REIHEN - 1) {
                System.out.println("-----------"); // nur zwischen den Feldern
            }
        }
        System.out.println(); //Am Ende eine Leerzeile
    }

    /**
     * Zelleninhalt mit "spieler" füllen, spieler ist eines von KREUZ, KREIS oder LEER
     */
    private static void zelleZeichnen(int spieler) {
        // Aufgabe 2b

        // Wenn spieler == KREUZ dann soll " X " ausgegeben werden, bei KREIS " 0 ", sonst nur 3 Leerzeichen "   "

        //Verwendet hier System.out.print() im gegegensatz zu println() erzeugt es danach keine neue Zeile

    }
}