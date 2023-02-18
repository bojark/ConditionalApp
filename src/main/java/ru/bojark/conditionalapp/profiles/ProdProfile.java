package ru.bojark.conditionalapp.profiles;

public class ProdProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}