package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50; // наносит урон
    public static String bossDefenceType = ""; // тип защиты

    public static int[] heroesHealths = {250, 250, 250};
    public static int[] heroesDamages = {20, 20, 20};
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic"}; // типы аттаки

    public static int doctorsHealth = 100;
    public static int[] doctorsCure = {10, 10, 10};

    public static int tankHealth = 150;
    public static int tankDamages = 10;

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        if (doctorsHealth > 0) {
            setDoctorsCure();
        }
        if (bossHealth > 0) {
            bossHit();
        }
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_____________________________________");
        if(bossHealth > 0){
            System.out.println("Boss health: " + bossHealth);
        } else {
            System.out.println("Босс убит");
        }

        for (int i = 0; i < heroesAttackTypes.length; i++) {
            System.out.println(heroesAttackTypes[i] + " health: " + heroesHealths[i]);
            if (heroesHealths[i] < 0) {
                System.out.println("Герои убиты");
            }
        }
        if (doctorsHealth > 0){
            System.out.println("Doctor`s health: " + doctorsHealth);
        } else {
            System.out.println("Доктор убит");
        }
        if (tankHealth > 0){
            System.out.println("Tank`s health: " + tankHealth);
        } else
            System.out.println("Танкист убит");

        System.out.println("______________________________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (!heroesAttackTypes[i].equals(bossDefenceType)) {
                Random r = new Random();
                int coefficient = r.nextInt(2) + 1;
                bossHealth = bossHealth - heroesDamages[i] * coefficient;
                System.out.println(heroesAttackTypes[i] + "critical attack " + heroesDamages[i] * coefficient);
            } else {
                bossHealth = bossHealth - heroesDamages[i];
            }
        }
        bossHealth = bossHealth - tankDamages;
    }

    public static void setDoctorsCure() {
        for (int i = 0; i < heroesHealths.length; i++) {
            heroesHealths[i] = heroesHealths[i] + doctorsCure[i];
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealths.length; i++) {
            heroesHealths[i] = heroesHealths[i] - bossDamage / 2;
        }
        doctorsHealth = doctorsHealth - bossDamage;
        tankHealth = tankHealth - bossDamage/2;
    }

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        if (heroesHealths[0] <= 0 && heroesHealths[1] <= 0 && heroesHealths[2] <= 0 && doctorsHealth <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

}