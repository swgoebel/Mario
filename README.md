# Mario
Super Mario Bros demo

This is a demo featuring the first level of Super Mario Bros. from NES.

It uses the framework found at http://www.kilobolt.com/unit-4-android-game-development.html

I added the following
 * State changes for the player. Commonly seen when Mario collects a mushroom.
 * A timer, that if expires, kills the player. If the player completes the level within time they are awarded points based on how     much time is left. The time can be seen throughout the [GameScreen class](https://github.com/swgoebel/Mario/blob/master/app/src/main/java/com/scottg/mariogame/GameScreen.java)
 * Sprites and maps seen in Super Mario Bros. These where edited with Paint.net using sprite sheets seen in the [assets folder](https://github.com/swgoebel/Mario/tree/master/app/src/main/assets)
 * A score is now kept for the user. This is increased by collecting coins and disposing of enemies. Again this is in the [GameScreen class](https://github.com/swgoebel/Mario/blob/master/app/src/main/java/com/scottg/mariogame/GameScreen.java)
 * Sounds and music for various actions and scenarios like jumping, dying, collecting coins.
 * New classes include the [Mario](https://github.com/swgoebel/Mario/blob/master/app/src/main/java/com/scottg/mariogame/Mario.java) and [Tube](https://github.com/swgoebel/Mario/blob/master/app/src/main/java/com/scottg/mariogame/Tube.java) classes.
 * Emulogic, a font identical to that found in the original game, was used. Since the framework is somewhat unorthadox an asset manager had to be created. This is found in the [FileIO class](https://github.com/swgoebel/Mario/blob/ca151a75c303de7bcf4a1287749a95930439c646/app/src/main/java/com/scottg/framework/FileIO.java#L19) and iplemented in the [MainMenuScreen class](https://github.com/swgoebel/Mario/blob/ca151a75c303de7bcf4a1287749a95930439c646/app/src/main/java/com/scottg/mariogame/MainMenuScreen.java#L62) and [GameScreen class](https://github.com/swgoebel/Mario/blob/ca151a75c303de7bcf4a1287749a95930439c646/app/src/main/java/com/scottg/mariogame/GameScreen.java#L197)
  
  
  
  
