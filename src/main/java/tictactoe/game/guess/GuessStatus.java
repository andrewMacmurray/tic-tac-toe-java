package tictactoe.game.guess;

public enum GuessStatus {
   Valid,
   OutOfBounds,
   Unrecognized;

   private boolean isValid;

   static {
      Valid.isValid = true;
      OutOfBounds.isValid = false;
      Unrecognized.isValid = false;
   }

   public boolean isValid() {
      return isValid;
   }
}
