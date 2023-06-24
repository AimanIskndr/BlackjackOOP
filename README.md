# Blackjack OOP
Blackjack is a popular card game that is played with a deck of 52 cards. The goal of the game is to beat the dealer by having a hand value that is 21 or as close to 21 as possible, without going over. The value of a hand is determined by the sum of the values of the individual cards. In this implementation of Blackjack, Aces are worth 1 or 11 points, face cards (King, Queen, and Jack) are worth 10 points, and all other cards are worth their face value (e.g. 2 of diamonds is worth 2 points).

This Java code simulates a game of Blackjack. The code shuffles a deck of cards, deals two cards to the player and two cards to the dealer, and allows the player to "hit" or "stand" until they decide to stand or until they bust (go over 21). The dealer will then reveal their hand and will "hit" or "stand" according to a set of rules. The game will output the result of the round (win, lose, or draw).

## Getting Started
To run this code, you will need to have the Java Development Kit (JDK) and a Java Integrated Development Environment (IDE) installed on your machine. The code was developed and tested using Eclipse and IntelliJ IDEA, but any Java IDE should work.

Additionally, since the code utilizes JavaFX for the graphical user interface (GUI) components, you will need to make sure that JavaFX is properly configured in your development environment. 

### Prerequisites
JDK: You can download the latest version of the JDK [here](https://www.oracle.com/java/technologies/downloads/).

JavaFX: JavaFX is required to run the graphical user interface (GUI) of the Blackjack game. You can download the JavaFX SDK [here](https://openjfx.io/).

IDE: Some popular Java IDEs include [Eclipse](https://www.eclipse.org/downloads/), [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows), and [Netbeans](https://netbeans.apache.org/download/index.html).

Make sure to configured the JDK, JavaFX, and an IDE before proceeding with running the code.

### Running the code
1. Open your chosen IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).
2. Create a new JavaFX project.
3. Configure the project to include the JavaFX libraries. Again, the steps may vary based on your IDE.
4. Replace the default project files with the downloaded or cloned code files for the Blackjack game. Alternatively, you can create the class and paste the code. Also, you will need to download the PNGAssets zip folder:


   4.1. Download the PNGAssets zip folder from the GitHub repository or any other source where the code is available.
   
   4.2. Extract the PNGAssets folder to a suitable location on your local machine.
   
   4.3. Locate the main class of the Blackjack game. In the main class, find the line of code where the PATH to the PNGAssets folder is defined. It might look like this:
   ![](https://media.discordapp.net/attachments/1110942392175038554/1122069505468809317/image.png?width=1440&height=129)

   4.4. Update the path/to/PNGAssets with the actual path to the PNGAssets folder on your machine.

   4.5. Save the changes to the main class.
5. Compile and run the code.

