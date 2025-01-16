# COMP2042 Developing Maintainable Software

## 1.0 My GitHub
- **Name**: Tan JieXun
- **Student ID**: 20408430
- **Repository Link**: [GitHub Repository](https://github.com/SinisterD7/COMP2042-Coursework.git)
---

## 2.0 Setup and Compilation Instructions

### 2.1 Prerequisites
- **Java Development Kit (JDK)**: Version 21 or higher
- **Apache Maven**: Version 3.8.0 or higher
- **JavaFX SDK**: Version 21.0.5
- **Git**: Installed and configured

### Recommended IDEs
- **IntelliJ IDEA**: Version 2023.3 or higher
- **Eclipse**: Version 2023-12 or higher
- **VS Code**: With Java Extension Pack and JavaFX Extension installed

---

## Environment Setup

### **1. Install Java Development Kit (JDK)**
1. Download JDK 21 from [Oracle](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or [OpenJDK](https://openjdk.org/).
2. Set up the `JAVA_HOME` environment variable:
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-21", "Machine")
      [Environment]::SetEnvironmentVariable("Path", $env:Path + ";%JAVA_HOME%\bin", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home' >> ~/.bash_profile
      echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile
      source ~/.bash_profile
      ```
3. Verify installation:
   ```bash
   java --version


## **2. Install JavaFX SDK**
1. Download JavaFX SDK 21 from [OpenJFX](https://openjfx.io/).
2. Extract it to a preferred location.
3. Optionally, add it to your PATH (although Maven will handle dependencies automatically):
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("PATH_TO_FX", "C:\Path\To\javafx-sdk-21\lib", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export PATH_TO_FX=/path/to/javafx-sdk-21/lib' >> ~/.bash_profile
      source ~/.bash_profile
      ```

---

## **3. Install Git**
1. Install Git for your operating system:
    - **Windows**: Download and install from [Git for Windows](https://git-scm.com/).
    - **macOS**: Install via Homebrew:
      ```bash
      brew install git
      ```
    - **Linux (Ubuntu/Debian)**:
      ```bash
      sudo apt-get install git
      ```
    - **Linux (Fedora)**:
      ```bash
      sudo dnf install git
      ```

2. Verify Git installation:
   ```bash
   git --version


## **4. Install Apache Maven**
1. Download Apache Maven from the [Maven Official Site](https://maven.apache.org/download.cgi).
2. Add Maven to your system PATH:
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Path\To\Maven\bin", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export PATH=/path/to/maven/bin:$PATH' >> ~/.bash_profile
      source ~/.bash_profile
      ```

---

### Verify Maven Installation
- To confirm Maven is installed correctly, run the following command:
    ```bash
    mvn --version


# Project Setup Guide

Follow the steps below to set up and run the project on your local environment.

---

## **2.2 Project Setup**

### Clone the Repository
1. Clone the project repository:
   ```bash
   git clone https://github.com/QuantumMilktea1/CW2024/blob/master/git
   cd CW2024

## **IDE Setup**

### **Eclipse**
1. Open Eclipse and import the project as a Maven project:
    - Go to **File** → **Import** → **Existing Maven Projects**.
    - Select the project folder and click **Finish**.
2. Configure the Java Build Path if necessary:
    - Right-click the project in the Project Explorer → **Properties** → **Java Build Path**.
    - Add JavaFX libraries if they are missing:
        - Go to the **Libraries** tab.
        - Click **Add External JARs...** and select the JavaFX JAR files from your JavaFX SDK.

---

### **IntelliJ IDEA**
1. Open IntelliJ IDEA and import the project as a Maven project:
    - Select **Open** from the IntelliJ start screen and choose the project folder.
    - IntelliJ will detect the `pom.xml` and set up the Maven configuration.
2. Verify the Project SDK:
    - Go to **File** → **Project Structure** → **Project Settings** → **Project SDK**.
    - Ensure the SDK is set to JDK 21.
3. Refresh Maven dependencies:
    - Open the Maven tool window (usually on the right-hand side).
    - Click **Reload All Maven Projects** to load all dependencies.
4. Wait for Maven to sync and ensure there are no errors in the dependencies.

---

4. Alternatively, set up a launch configuration to run the project directly:
    - Go to the **Run and Debug** panel → **Create a Launch Configuration** → Configure it for Maven.

---

## **Compile and Run the Application**

### **Compile the Project**
- Clean and compile the project using Maven:
    ```bash
    mvn clean compile
    ```
- Run the application:
    ```bash
    mvn javafx:run
    ```
- Package the application:
    ```bash
    mvn package
    ```

# Common Build Commands

Use the following Maven commands to build, test, package, and run the project.

---
**1. Clean Build Files**
- Removes all previously generated build files:
    ```bash
    mvn clean
    ```

**2. Compile the Project**
- Compiles the source code of the project:
    ```bash
    mvn compile
    ```

**3. Run Tests**
- Executes all test cases in the project:
    ```bash
    mvn test
    ```

**4. Package the Application**
- Packages the application into a JAR file:
    ```bash
    mvn package
    ```

**5. Install to Local Repository**
- Installs the application into the local Maven repository:
    ```bash
    mvn install
    ```

**6. Run the Application**
- Runs the application using the Maven JavaFX plugin:
    ```bash
    mvn javafx:run
    ```

## 3.0 Features

### 3.1 Implemented and Working Properly
1. **User Plane Movement**: Added up and down movement controls for the user plane.
    - **Classes Involved**:
        - `UserPlane` (actor)
        - `InputManager` (manager)
    - **Key Methods**:
        - `UserPlane.stopHorizontalMovement()`
        - `InputManager.createKeyPressedHandler()`
        - `InputManager.createKeyReleasedHandler()`

2. **StartScreen**: Implemented a visually styled startup screen and game initialization logic.
    - **Classes Involved**:
        - `StartScreen` (UI logicUI logic)
        - `Controller`  (game logic)
    - **Key Methods**:
        - `A black background styled VBox.`
        - `A Button to start the game.`
        - `A Button to exit the application.`
        - `Controller.launchGame()`

3. **endGame**: Implements the logic for gracefully terminating the game by halting animations, clearing game elements, and resetting the game state.

    - **Key Methods**
        - `friendlyUnits.clear()` Resets friendly units.
        - `enemyProjectiles.clear()`Clears enemy-fired projectiles.
        - `enemyUnits.clear()` Removes enemy units.
        - `userProjectiles.clear()` Resets user-fired projectiles.

4. **ShiMmage**: Fix a custom ImageView subclass for rendering shield graphics in-game.
    - **Classes Involved**:
        - `ShieldImage`  (custom UI component for shield visualization).

    - **Key Methods**
        - `showShield()` Makes the shield visible on the screen.
        - `hideShield()`Hides the shield from the screen.
        - `setFitHeight(SHIELD_SIZE)` Adjusts the shield's height to a predefined size.s
        - `setImage(new Image(...))` Loads the shield's image resource and applies it to the ImageView.

### 3.2 Implemented but Not Working Properly

1. **Shield Overactivation**: The shield activation is too frequent due to the randomized probability, making the boss overly difficult to defeat.
- **Solution**: Reduce the frequency of shield activation by lowering the probability threshold in the randomization logic. For example, increase the random range or set a minimum time interval between

2. **HitBox**: The hitbox functionality for game elements is not accurately detecting collisions, causing inconsistencies in gameplay, such as missed hits or incorrect interactions.
   - **Solution**: Add a temporary visual representation (e.g., colored rectangles) for hitboxes during testing to validate their size and position.
   
### 3.3 Not Implemented
1. **Pause Menu**: A feature to pause the game and display options like resume, restart, or exit.
2. **Save and Load System**: Enabling players to save their progress and resume the game later.
3. **Dynamic Difficulty Adjustment:**: A system to alter the game's difficulty based on the player's performance.


### 3.4 How to Add Missing Features

1. **Pause Menu**
    - **Add `Pause Menu` Class**: Approach: Overlay the current game scene with a semi-transparent menu containing pause options. Use a VBox or StackPane to design the menu interface.

2. **Dynamic Difficulty Adjustment**
    - **Dynamic Difficulty Adjustment**:Use metrics such as player performance (e.g., score, time survived) to modify game parameters like enemy spawn rates, health, or speed.

2. **Save and Load System**
    - Serialize the game's state (e.g., player stats, level, score) to a file or database. Deserialize it when loading the game.
---

## 4.0 Refactoring Process

### 4.1 New Java Classes

# StartScreen

| **Class Name**   | **Description**                                                                                                                                                                                                                                        |
|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| StartMenu        | This class is responsible for displaying the start menu of the game. It features a black background, a title label ("Sky Battle"), and two interactive buttons: "Start Game" to launch the game and "Exit" to close the application.                      |

| **Key Methods**                                                                                                                                                                                                                                                                                              |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **display(Stage stage, Runnable startGame)** | Displays the start menu on the given stage. <br> - Sets a black background with a VBox layout. <br> - Adds a title label styled with large, bold white text. <br> - Includes a "Start Game" button that calls the `startGame` callback. <br> - Includes an "Exit" button to close the application. |

# levelview

| **Class Name**   | **Description**                                                                                                                                                                                                                                                |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| LevelView        | This class handles the visual representation of the game level, including displaying hearts (representing player lives), win screens, and game-over screens. It is responsible for dynamically updating these elements during gameplay based on the game state. |

| **Key Methods**                                                                                                                                                                                                                                                              |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **showHeartDisplay()**         | Adds the heart display (player lives) to the root group, making it visible in the game.                                                                                                                                                   |
| **showWinImage()**             | Displays the win image on the screen by adding it to the root group and triggering its visibility.                                                                                                                                        |
| **showGameOverImage()**        | Displays the game-over image by adding it to the root group, indicating the player has lost the game.                                                                                                                                     |
| **removeHearts(int heartsRemaining)** | Updates the heart display by removing hearts from the screen to reflect the remaining player lives. The method calculates how many hearts to remove based on the current number of hearts and the new value of `heartsRemaining`. |

# ShieldImage

| **Class Name**   | **Description**                                                                                                                                                                                                                                                                 |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ShieldImage      | This class represents the visual component of a shield in the game. It is responsible for displaying the shield image at a specific location on the screen, managing its visibility, and ensuring its size is appropriate for the gameplay.                                      |

| **Key Methods**                                                                                                                                                                                                                                                                                  |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ShieldImage(double xPosition, double yPosition)** | Constructor that initializes the shield's position on the screen, sets its size, and loads the shield image from the specified resource path. If the image fails to load, an exception is thrown to indicate the issue.                                      |
| **showShield()**                                    | Makes the shield visible on the screen, indicating it is active and providing protection during gameplay.                                                                                                                                               |
| **hideShield()**                                    | Hides the shield from view, signifying that it is no longer active or available to the player.                                                                                                                                                          |

### 4.2 Modified Java Classes

- ActiveActorDestructible

| **Class Name**                | **Description**                                                                                                                                                                                                                                                   |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ActiveActorDestructible       | This abstract class represents destructible active actors in the application. It extends `ActiveActor` and implements the `Destructible` interface, providing common functionality for actors that can take damage, be destroyed, and update their position or state. |

| **Key Methods**                                                                                                                                                                                                                                                                              |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos)** | Constructor initializes the actor's image, dimensions, and starting position. Sets the actor's destruction status to false by default.                                                                                                               |
| **updatePosition()**                                                                                   | Abstract method for updating the actor's position. This must be implemented by subclasses to define specific movement or positioning logic.                                                                                                           |
| **updateActor()**                                                                                      | Abstract method for updating the actor's state or behavior. Subclasses must implement this to define specific update logic.                                                                                                                           |
| **takeDamage()**                                                                                       | Abstract method for handling damage. Subclasses implement this to specify how the actor responds to taking damage.                                                                                                                                   |
| **destroy()**                                                                                          | Marks the actor as destroyed by setting the `isDestroyed` flag to `true`. This indicates the actor is no longer active in the game.                                                                                                                   |
| **setDestroyed(boolean isDestroyed)**                                                                  | Protected method to update the actor's destruction status.                                                                                                                                                                                           |
| **isDestroyed()**                                                                                      | Returns the current destruction status of the actor. A value of `true` indicates the actor is destroyed, while `false` means it is still active.                                                                                                     |

- controller

| **Class Name**            | **Description**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Controller                | This class implements the `Observer` interface and manages the core game control logic, including launching the game, switching between levels, and handling exceptions. It uses reflection to dynamically load and instantiate level classes. Additionally, the `Controller` observes level objects for state changes and reacts accordingly.                                                                                                                                                                                                                                               |

| **Key Methods**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Controller(Stage stage)**                                                                                           | Constructor that initializes the `Controller` with the primary stage of the application.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **launchGame()**                                                                                                      | Launches the game by showing the main stage and transitioning to the first level. Uses `goToLevel` to load the level dynamically. Throws exceptions for various issues, such as class not found or instantiation errors, ensuring robust error handling during game initialization.                                                                                                                                                                                                                                                                                                                                                          |
| **goToLevel(String className)**                                                                                       | Loads and transitions to a level based on its class name. Uses Java reflection to dynamically find and invoke the appropriate constructor for the level class. Initializes the level's scene, sets it on the primary stage, and starts the game. If the class or constructor is not found, or instantiation fails, it throws the corresponding exceptions.                                                                                                                                                                                                                                                                                       |
| **update(Observable arg0, Object arg1)**                                                                              | Implements the `Observer` interface method. When notified by an observable object (e.g., a level), it attempts to load the next level specified in the notification. If an error occurs (e.g., invalid class name), it displays an error dialog with the exception details to inform the user.                                                                                                                                                                                                                                                                                                                                            |

- levelview

| **Class Name** | **Description**                                                                                                                                        | **Changes**                                                                                                                                                                                                                                                              |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `LevelView`    | Represents the UI components and visual feedback for a game level, including heart displays, win/loss screens, and optional elements like boss health. | 1. **Shield Integration**: Added `ShieldImage` with `showShield` and `hideShield` methods to display and manage a shield icon for boss.<br>2. **Boss Health Display**: Added `Text` and `Pane` components to display and dynamically update the boss's health.           |
|                |                                                                                                                                                        | 3. **Kill Count Integration**: Introduced `killCountText` for tracking and displaying kill counts. Added methods `showKillCountDisplay` and `updateKillCountDisplay` to manage this feature.<br>4. **Warning Label**: Added optional warning labels for specific levels. |
|                |                                                                                                                                                        | 5. **Improved Image Management**: Ensured unique addition of `WinImage` and `GameOverImage` to avoid duplicate entries in the game root.<br>6. **Dynamic Feedback**: Updated warning labels dynamically based on progress towards kill targets.                          |

- projectile

| **Class Name**            | **Description**                                                                                                                                                                                                                                                                                                                                                             |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Projectile                | his abstract class represents a generic projectile in the game. It provides common attributes and behaviors for all projectile types, such as initialization, movement, and destruction upon taking damage. Subclasses must implement specific behaviors, such as defining movement patterns by overriding the updatePosition method..                                             |

| **Key Methods**                                                                                                                                                                                                                                     |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos)**                                                                           | Constructor that initializes a projectile with the specified image, height, and initial position. Inherits destructible behavior from `ActiveActorDestructible`.                                                                                      |
| **takeDamage()**                                                                                                                                                    | Defines the behavior of the projectile when it takes damage. By default, the projectile is destroyed by invoking the `destroy` method.                                                                                                              |
| **abstract void updatePosition()**                                                                                                                                 | An abstract method that must be implemented by subclasses to define the specific movement pattern of the projectile.                                                                                                                                |


## 5.0 Summary

### 5.1 Features

1. **StartMenu**
    - **Description**: Created a start menu for the game, including a title, "Start Game," and "Exit" buttons. The menu is styled with a black background and centered elements.
    - **Background **: IThe start menu allows the player to launch the game or exit the application. The layout adjusts automatically and is styled for visual clarity.
    - **Classes Involved**:
        - `StartMenu` handles the UI for the start menu)

    - **Key Methods**:
        - `display(Stage stage, Runnable startGame)` Sets up the start menu UI and handles the "Start Game" and "Exit" button actions.
- **Key Features**:Title Label: Displays "Sky Battle" in bold white text with a large font size.
Buttons:
"Start Game" button launches the game using the provided startGame callback.
"Exit" button closes the application.

2. **Boss Class Implementation**
    - **Description**:Represents the boss enemy in the game with unique abilities such as a shield, vertical movement patterns, and a fire rate for projectiles. The boss can move, fire projectiles, activate/deactivate shields, and take damage.
  - **Key Features**:Constants: Predefined properties like fire rate, health, movement boundaries, and shield duration.
Randomization: Movement patterns and shield activation are randomized for unpredictability.
Visual Integration: Uses ShieldImage to visually represent shield activation.
Bounds Checking: Prevents the boss from moving outside defined vertical limits.
    - **Classes Involved**:
        - `Boss` (extends FighterPlane, implements boss-specific features)
        - `BossProjectile` (projectiles fired by the boss).
    - **Key Methods**:
        - `updatePosition()`  (Updates the boss's position based on its movement pattern.)
        - `initializeMovePattern()` (Defines and randomizes the vertical movement pattern.)
        - `getNextMove()` (Retrieves the next movement from the pattern, shuffling if necessary.)
      
3. **EndGame**
    - **Description**: Cleans up and terminates the game session by stopping animations, clearing game objects, and resetting event listeners. This method ensures that all game-related activities are halted and resources are released.
    - **Classes Involved**:
        - `timeline.getKeyFrames().clear()` (Removes all scheduled animations or events from the timeline.)
        - `background.setOnKeyReleased(null)` (Removes the keyboard event listener to prevent further input processing.)
    - **Key Features**:
        - `Ensures a clean game termination to avoid memory leaks or unwanted behavior when restarting or exiting.`
        - `Prevents further user interaction by disabling event listeners.`
        - `Prepares the game environment for a potential restart or application shutdown.`


### 5.2 Refactoring

1. **Package Organization**
    - **Description**: Reorganized the project structure into specific packages (`actor`,`controller`, `level`, `levelview`, `manager`, `projectiles`,) to enhance code maintainability and readability.
    - **Benefits**:
        - Improved modularity.
        - Easier navigation and management of classes.
        - Enhanced scalability for future developments.

2. **`LevelViewLevelTwo`**
    - **Description**: The `LevelViewLevelTwo` class and integrated its functionalities into the `LevelView` class by adding `showShield()` and `hideShield()` methods.
    - **Solution**: redundant methods and unused code to improve code readability and maintainability.

3. **Fixed Shield Display in Boss Level**
    - **Description**: The shield was not displaying correctly in the boss level.
    - **Solution**: Moved the shield showing functionality to the `LevelBoss` class for better separation of concerns.

### 5.3 Achievements

1. **Development of a Comprehensive Boss Level**
    - **Description**: Created a challenging boss level featuring unique shield mechanics, custom animations, and clearly defined win/lose conditions. This feature introduced a new level of complexity and engagement, providing players with a rewarding challenge.
    - **Impact**:
        - Increased the overall difficulty and engagement of the game.
        - Highlighted advanced skills in game mechanics and level design.

2. **Creation of a Start Menu**
    - **Description**: Developed a functional start menu that allows players to begin new games, load saved progress, and adjust game settings.
    - **Impact**:
        - Improved game accessibility with an easy-to-navigate interface.
        - Streamlined the navigation process, enhancing overall user experience and game flow.

3. **Fixing the Shield Display Bug**
    - **Description**:  Resolved an issue where the shield display failed to render correctly, ensuring accurate representation during gameplay.
    - **Impact**:
        - Enhanced visual clarity and the reliability of the shield feature.
        - Improved gameplay by ensuring players can trust visual elements during key moments
---

## 6.0 Unexpected Problems

### 1. **Hitbox Not Functional**
- **Issue**: Certain characters and objects in the game experienced non-functional hitboxes, causing collision detection failures that negatively impacted gameplay.
- **Solution**:
-Investigated the hitbox calculation logic and identified errors in the definitions and updates.
-Corrected the hitbox assignment and ensured it was updated consistently during each game frame.
- Performed manual testing on key game objects to confirm accurate collision detection, enhancing gameplay stability.

### 2. **Integration Issues After Refactoring Game Levels**
- **Issue**: Refactoring the codebase to include new manager classes caused problems during game level transitions, leading to crashes or lags that disrupted gameplay.

- **Solution**:
    - Adopted an incremental refactoring approach, ensuring changes were applied gradually and tested continuously.
    - Identified and resolved state management issues in the new manager classes, ensuring smooth transitions between levels.
    - Adjusted the logic in the level manager to ensure that it properly initialized and cleaned up resources between level transitions.

### 3. **End Screen UI Conflicts**
- **Issue**:  UI conflicts on the end screen caused abnormal displays and prevented players from exiting properly, leaving them stuck in the game.
- **Solution**:
    - Redesigned the screen transition process to synchronize the loading and display of UI elements, resolving conflicts and enhancing the end screen’s functionality..
    - Implemented a visibility control flag to ensure the end screen displayed only after all game processes had completed and resources were cleared.
