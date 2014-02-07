package me.martelli.enrico.studentdroid.sqlite.helper;

import android.graphics.Color;

import java.util.Date;
import java.util.Random;

import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

/**
 * Created by Enrico on 06/02/14.
 */
public class DebugDbSeeder {
    public static void seed() {
        Materia matematica = new Materia(
                "Matematica",
                "Ferretti",
                null,
                randomColor()
        );
        matematica.save();

        Lezione lezione = new Lezione(
                3,
                ora(4),
                ora(5),
                "B14",
                "Fava",
                (int) matematica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                4,
                ora(2),
                ora(3),
                "307",
                null,
                (int) matematica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                6,
                ora(2),
                ora(3),
                "B14",
                "Fava",
                (int) matematica.getId()
        );
        lezione.save();

        Materia inglese = new Materia(
                "Inglese",
                "Starace",
                null,
                randomColor()
        );
        inglese.save();

        lezione = new Lezione(
                2,
                ora(4),
                ora(5),
                "307",
                null,
                (int) inglese.getId()
        );
        lezione.save();

        lezione = new Lezione(
                4,
                ora(1),
                ora(2),
                "307",
                null,
                (int) inglese.getId()
        );
        lezione.save();

        lezione = new Lezione(
                6,
                ora(5),
                ora(6),
                "307",
                null,
                (int) inglese.getId()
        );
        lezione.save();

        Materia storia = new Materia(
                "Storia",
                "D'Alanno",
                null,
                randomColor()
        );
        storia.save();

        lezione = new Lezione(
                2,
                ora(5),
                ora(6),
                "307",
                null,
                (int) storia.getId()
        );
        lezione.save();

        lezione = new Lezione(
                5,
                ora(5),
                ora(6),
                "307",
                null,
                (int) storia.getId()
        );
        lezione.save();

        Materia informatica = new Materia(
                "Informatica",
                "Cenacchi",
                null,
                randomColor()
        );
        informatica.save();

        lezione = new Lezione(
                2,
                ora(1),
                ora(3),
                "B15",
                "Bertagnin",
                (int) informatica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                3,
                ora(3),
                ora(4),
                "B15",
                "Bertagnin",
                (int) informatica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                4,
                ora(3),
                ora(5),
                "111",
                null,
                (int) informatica.getId()
        );
        lezione.save();

        Materia religione = new Materia(
                "Religione",
                "Poluzzi",
                null,
                randomColor()
        );
        religione.save();

        lezione = new Lezione(
                2,
                ora(3),
                ora(4),
                "307",
                null,
                (int) religione.getId()
        );
        lezione.save();

        Materia sistemi = new Materia(
                "Sistemi",
                "Savarese",
                null,
                randomColor()
        );
        sistemi.save();

        lezione = new Lezione(
                3,
                ora(1),
                ora(3),
                "B16",
                "Ravazza",
                (int) sistemi.getId()
        );
        lezione.save();

        lezione = new Lezione(
                5,
                ora(1),
                ora(2),
                "109",
                null,
                (int) sistemi.getId()
        );
        lezione.save();

        lezione = new Lezione(
                6,
                ora(1),
                ora(2),
                "109",
                null,
                (int) sistemi.getId()
        );
        lezione.save();

        lezione = new Lezione(
                6,
                ora(6),
                ora(7),
                "B16",
                "Ravazza",
                (int) sistemi.getId()
        );
        lezione.save();

        Materia italiano = new Materia(
                "Italiano",
                "D'Alanno",
                null,
                randomColor()
        );
        italiano.save();

        lezione = new Lezione(
                3,
                ora(5),
                ora(6),
                "307",
                null,
                (int) italiano.getId()
        );
        lezione.save();

        lezione = new Lezione(
                6,
                ora(3),
                ora(5),
                "307",
                null,
                (int) italiano.getId()
        );
        lezione.save();

        Materia fisica = new Materia(
                "Ed. Fisica",
                "Tonioli",
                null,
                randomColor()
        );
        fisica.save();

        lezione = new Lezione(
                4,
                ora(5),
                ora(7),
                "BP2",
                null,
                (int) fisica.getId()
        );
        lezione.save();

        Materia elettronica = new Materia(
                "Elettronica",
                "Di Feliciantonio",
                "Moretto",
                randomColor()
        );
        elettronica.save();

        lezione = new Lezione(
                5,
                ora(2),
                ora(5),
                "B17",
                "Moretto",
                (int) elettronica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                7,
                ora(1),
                ora(3),
                "307",
                null,
                (int) elettronica.getId()
        );
        lezione.save();

        Materia statistica = new Materia(
                "Statistica",
                "Fiorenzo",
                "Fava",
                randomColor()
        );
        statistica.save();

        lezione = new Lezione(
                3,
                ora(6),
                ora(7),
                "B14",
                "Fava",
                (int) statistica.getId()
        );
        lezione.save();

        lezione = new Lezione(
                7,
                ora(3),
                ora(5),
                "307",
                null,
                (int) sistemi.getId()
        );
        lezione.save();
    }

    public static int randomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public static Date ora(int ora) {
        return new Date(1000 * 60 * 60 * (7 + ora));
    }
}
