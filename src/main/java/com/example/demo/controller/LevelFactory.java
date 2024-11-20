package com.example.demo.controller;

import java.lang.reflect.Constructor;

public class LevelFactory {
    public LevelParent createLevel(String className, double height, double width) {
        try {
            Class<?> levelClass = Class.forName(className);
            Constructor<?> constructor = levelClass.getConstructor(double.class, double.class);
            return (LevelParent) constructor.newInstance(height, width);
        } catch (ClassNotFoundException e) {
            throw new GameException("Failed to load level class: " + className, e);
        } catch (NoSuchMethodException e) {
            throw new GameException("Invalid level constructor: " + className, e);
        } catch (Exception e) {
            throw new GameException("Failed to create level instance: " + className, e);
        }
    }
} 