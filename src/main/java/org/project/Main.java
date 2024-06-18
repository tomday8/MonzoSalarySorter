package org.project;

public class Main {
    public static void main(String[] args) {
        String monzoApiUrl = "https://api.monzo.com";
        String accessToken = System.getenv("JAVA_MONZO_ACCESS_TOKEN");

        GetAccounts.getAccounts(monzoApiUrl, accessToken);
    }
}
